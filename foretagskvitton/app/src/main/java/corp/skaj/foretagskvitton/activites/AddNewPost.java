package corp.skaj.foretagskvitton.activites;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.v4.content.FileProvider;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import corp.skaj.foretagskvitton.R;

/**
 *
 */
public class AddNewPost extends AppCompatActivity {
    private String imageAdress;
    private static final int REQUEST_IMAGE_CAPTURE = 31415;
    public static final String BUILD_NEW_RECEIPT = "corp.skaj.foretagskvitton.BUILD_RECEIPT";
    public static final String KEY_FOR_IMAGE = "corp.skaj.foretagskvitton.KEY_FOR_IMAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_new_post);
        imageAdress = "";
        // Hides the actionbar and gives fullscreen feature
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        // Setup bottom navigation
        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        setupBottomNavigationBar(bottomBar);
    }

    /**
     * This method catches taken image by camera.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            if (imageAdress.length() > 0) {
                Uri URI = Uri.fromFile(new File(imageAdress));
                imageAdress = "";
                startWizard(URI);
            }
        } else {
            // TODO fix a popup showing "no pic was captured"
            System.out.println("No picture was found");
        }
    }

    /**
     * This method starts wizard guide for adding new receipt by taking an image with camera.
     * @param URI
     */
    private void startWizard(Uri URI) {
        Intent intent = new Intent(this, WizardActivity.class);
        intent.putExtra(KEY_FOR_IMAGE, URI);
        intent.setAction(BUILD_NEW_RECEIPT);
        startActivity(intent);
    }

    private void setupBottomNavigationBar(BottomBar bottomBar) {
        bottomBar.setDefaultTab(R.id.action_add);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.action_add:
                        //TODO what happens here?
                        return;
                    case R.id.action_archive:
                        //TODO what happens here=
                        return;
                    case R.id.action_business:
                        //TODO what happens here?
                        return;
                    case R.id.action_charts:
                        //TODO what happens here?
                        return;
                    case R.id.action_user:
                        //TODO what happens here?
                        return;
                    default:
                        return;
                }
            }
        });
    }

    /**
     * This method starts Camera
     */
    private void dispatchOpenCamera() {
        Intent openCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure there is a camera
        if (openCamera.resolveActivity(getPackageManager()) != null) {
            Uri imageURI = setupImageFolder();
            openCamera.putExtra(MediaStore.EXTRA_OUTPUT, imageURI);
            startActivityForResult(openCamera, REQUEST_IMAGE_CAPTURE);
        }
    }

    /**
     * This method arranges a folder where an image taken by camera is saved.
     * @return
     */
    private Uri setupImageFolder() {
        File imageFile = null;
        try {
            imageFile = createImageFile();
        } catch (IOException e) {
            System.out.println("Not able to create imageFile");
            //TODO fix a popup here
        }
        Uri imageURI = FileProvider.getUriForFile(getApplicationContext(),
                "corp.skaj.foretagskvitton.fileprovider",
                imageFile);
        return imageURI;
    }

    /**
     * This method creates a file in which image taken by camera is saved.
     * @return
     * @throws IOException
     */
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
        imageAdress = image.getAbsolutePath();
        return image;
    }

    /**
     * This method runs when camera button is pressed.
     * @param view
     */
    public void cameraButtonActionPerformed(View view) {
        dispatchOpenCamera();
    }

}
