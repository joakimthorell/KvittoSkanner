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
import corp.skaj.foretagskvitton.controllers.AddReceiptController;
import corp.skaj.foretagskvitton.services.ReceiptScanner;

public class AddReceiptActivity extends AbstractActivity {
    public static final String BUILD_NEW_RECEIPT = "corp.skaj.foretagskvitton.BUILD_RECEIPT";
    public static final String KEY_FOR_IMAGE = "corp.skaj.foretagskvitton.KEY_FOR_IMAGE";
    private static final int REQUEST_IMAGE_CAPTURE = 31415;
    private String mImageAdress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mImageAdress = "";
        String action = getIntent().getAction();
        onActionPerformed(action);
    }

    private void onActionPerformed(String action) {
        switch (action) {
            case AddReceiptController.CAMERA_ACTION:
                dispatchOpenCamera();
                break;
            case AddReceiptController.GALLERY_ACTION:
                // TODO
                break;
            case AddReceiptController.NO_IMAGE_ACTION:
                // TODO
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
        setContentView(R.layout.activity_archive);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.spin_kit_copy);
        CubeGrid cubeGrid = new CubeGrid();
        progressBar.setIndeterminateDrawable(cubeGrid);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            if (mImageAdress.length() > 0) {
                Uri URI = Uri.fromFile(new File(mImageAdress));
                mImageAdress = "";
                startWizard(URI);
            }
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

    // This method arranges a folder where an image taken by camera is saved.
    private Uri setupImageFolder() {
        File imageFile = null;
        try {
            imageFile = createImageFile();
        } catch (IOException e) {
            System.out.println("Not able to create imageFile " + this.toString());
            //TODO fix a popup here
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
        copyImage(newFile, bmp);
        Uri addressToNewFile = Uri.fromFile(newFile);
        mImageAdress = "";
        startWizard(addressToNewFile);
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
}
