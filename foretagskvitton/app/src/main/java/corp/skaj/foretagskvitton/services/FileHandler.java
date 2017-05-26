package corp.skaj.foretagskvitton.services;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileHandler {
    private IWizard mWizard;
    private Context mContext;
    private String mImageAddress;

    public FileHandler(IWizard fileHandler, Context context) {
        mWizard = fileHandler;
        mContext = context;
    }

    // This method arranges a folder where an image taken by camera is saved.
    public Uri setupImageFolder() {
        File imageFile = null;
        try {
            imageFile = createImageFile();
        } catch (IOException e) {
            System.out.println("Not able to create imageFile " + this.toString());
        }
        Uri imageURI = FileProvider.getUriForFile(mContext.getApplicationContext()
                , "corp.skaj.foretagskvitton.fileprovider"
                , imageFile);
        return imageURI;
    }

    // This method creates a file in which image taken by camera is saved.
    private File createImageFile() throws IOException {
        // Create image file
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = mWizard.getExternalFileDir();
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        // Save a file: path for use with ACTION_VIEW intents
        mImageAddress = image.getAbsolutePath();
        mWizard.updateImageAddress(mImageAddress);
        return image;
    }

    public void readGallerImage(Uri addressToGallery) {
        setupImageFolder();
        File newFile = new File(mImageAddress);
        Bitmap bmp;
        try {
            bmp = ReceiptScanner.createImageFromURI(mContext, addressToGallery);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            mWizard.startWizard(null);
            return;
        }
        CopyImageTask task = new CopyImageTask(newFile, bmp);
        task.execute();
    }

    private void copyImage(File dest, Bitmap bmp) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(dest);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is Bitmap instance
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeOldFile(String oldUri) {
        File fileToDelete = new File(oldUri);
        if (fileToDelete.delete()) {
            System.out.println("Removed old unused image");
        } else {
            System.out.println("File not deleted, path: " + oldUri);
        }
    }

    private class CopyImageTask extends AsyncTask<Void, Void, Void> {
        private File copyTo;
        private Bitmap copyFrom;

        private CopyImageTask(File copyTo, Bitmap copyFrom) {
            this.copyTo = copyTo;
            this.copyFrom = copyFrom;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mWizard.initProgressBar();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Uri addressToNewFile = Uri.fromFile(copyTo);
            mWizard.updateImageAddress("");
            mWizard.startWizard(addressToNewFile);
        }

        @Override
        protected Void doInBackground(Void... params) {
            copyImage(copyTo, copyFrom);
            return null;
        }
    }

}
