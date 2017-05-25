package corp.skaj.foretagskvitton.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.widget.ProgressBar;

import com.github.ybq.android.spinkit.style.CubeGrid;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.controllers.ArchiveListFABController;
import corp.skaj.foretagskvitton.services.ReceiptScanner;

public class AddReceiptActivity extends AbstractActivity {
    public static final String BUILD_NEW_RECEIPT = "corp.skaj.foretagskvitton.BUILD_RECEIPT";
    public static final String KEY_FOR_IMAGE = "corp.skaj.foretagskvitton.KEY_FOR_IMAGE";
    private static final int REQUEST_IMAGE_CAPTURE = 31415;
    private static final int REQUEST_IMAGE_CHOOSEN = 1313;
    private String mImageAdress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.copy_image_layout);
        mImageAdress = "";
        String action = getIntent().getAction();
        onActionPerformed(action);
    }

    private void onActionPerformed(String action) {
        switch (action) {
            case ArchiveListFABController.CAMERA_ACTION:
                dispatchOpenCamera();
                break;
            case ArchiveListFABController.GALLERY_ACTION:
                dispatchChoosePictureIntent();
                break;
            case ArchiveListFABController.NO_IMAGE_ACTION:
                startWizard(null);
                break;
            case Intent.ACTION_SEND:
                onActionSend();
                break;
            default:
                break;
        }
    }

    private void onActionSend() {
        if (getIntent().getType().startsWith("image/")) {
            Uri uri = getIntent().getParcelableExtra(Intent.EXTRA_STREAM);
            readGallerImage(uri);
        }
    }

    // This method catches taken image by camera.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            if (mImageAdress.length() > 0) {
                Uri URI = Uri.fromFile(new File(mImageAdress));
                mImageAdress = "";
                startWizard(URI);
                return;
            }
        } else if (requestCode == REQUEST_IMAGE_CHOOSEN && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            readGallerImage(uri);
            return;
        } else {
            System.out.println("No picture was found");
        }
    }

    // This method starts wizard guide for adding new receipt by taking an image with camera.
    private void startWizard(Uri URI) {
        Intent intent = new Intent(this, InitWizardActivity.class);
        intent.putExtra(KEY_FOR_IMAGE, URI);
        intent.setAction(BUILD_NEW_RECEIPT);
        startActivity(intent);

    }

    //This method starts Camera.
    private void dispatchOpenCamera() {
        Intent openCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure there is a camera
        if (openCamera.resolveActivity(getPackageManager()) != null) {
            Uri imageURI = setupImageFolder();
            openCamera.putExtra(MediaStore.EXTRA_OUTPUT, imageURI);
            startActivityForResult(openCamera, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void dispatchChoosePictureIntent() {
        Intent intent = new Intent(Intent.ACTION_PICK)
                .setType("image/*");
        startActivityForResult(intent, REQUEST_IMAGE_CHOOSEN);
    }

    // This method arranges a folder where an image taken by camera is saved.
    private Uri setupImageFolder() {
        File imageFile = null;
        try {
            imageFile = createImageFile();
        } catch (IOException e) {
            System.out.println("Not able to create imageFile " + this.toString());
        }
        Uri imageURI = FileProvider.getUriForFile(getApplicationContext()
                , "corp.skaj.foretagskvitton.fileprovider"
                , imageFile);
        return imageURI;
    }

    // This method creates a file in which image taken by camera is saved.
    private File createImageFile() throws IOException {
        // Create image file
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        // Save a file: path for use with ACTION_VIEW intents
        mImageAdress = image.getAbsolutePath();
        return image;
    }

    private void readGallerImage(Uri addressToGallery) {
        setupImageFolder();
        File newFile = new File(mImageAdress);
        Bitmap bmp;
        try {
            bmp = ReceiptScanner.createImageFromURI(this, addressToGallery);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            startWizard(null);
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
            ProgressBar progressBar = (ProgressBar) findViewById(R.id.spin_kit);
            CubeGrid cubeGrid = new CubeGrid();
            progressBar.setIndeterminateDrawable(cubeGrid);

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Uri addressToNewFile = Uri.fromFile(copyTo);
            mImageAdress = "";
            startWizard(addressToNewFile);
        }

        @Override
        protected Void doInBackground(Void... params) {
            copyImage(copyTo, copyFrom);
            return null;
        }
    }
}
