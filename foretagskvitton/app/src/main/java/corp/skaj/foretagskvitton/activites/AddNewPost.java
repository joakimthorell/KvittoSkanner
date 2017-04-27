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
    private String pictureAdress;
    private static final int REQUEST_IMAGE_CAPTURE = 31415;
    public static final String BUILD_NEW_RECEIPT = "corp.skaj.foretagskvitton.BUILD_RECEIPT";
    public static final String KEY_FOR_IMAGE = "corp.skaj.foretagskvitton.KEY_FOR_IMAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_new_post);
        pictureAdress = "";
        // Hides the actionbar and gives fullscreen feature
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        // Setup bottom navigation
        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        setupBottomNavigationBar(bottomBar);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            if (pictureAdress.length() > 0) {
                Uri URI = Uri.fromFile(new File(pictureAdress));
                pictureAdress = "";
                startWizard(URI);
            }
        } else {
            // TODO fix a popup showing "no pic was captured"
            System.out.println("No picture were found");
        }
    }

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

    private void dispatchOpenCamera() {
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure there is a camera
        if (takePicture.resolveActivity(getPackageManager()) != null) {
            // Creates a file to save the photo in
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException e) {
                System.out.println("Not able to create photoFile");
                //TODO fix a popup here
                return;
            }
            Uri photoURI = FileProvider.getUriForFile(getApplicationContext(),
                    "corp.skaj.foretagskvitton.fileprovider",
                    photoFile);
            takePicture.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            startActivityForResult(takePicture, REQUEST_IMAGE_CAPTURE);
        }
    }

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
        pictureAdress = image.getAbsolutePath();
        return image;
    }

    public void takePicturePressed(View view) {
        dispatchOpenCamera();
    }

}
