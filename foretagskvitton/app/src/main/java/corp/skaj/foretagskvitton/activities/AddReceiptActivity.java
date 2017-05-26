package corp.skaj.foretagskvitton.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.ybq.android.spinkit.style.CubeGrid;
import com.github.ybq.android.spinkit.style.FadingCircle;

import java.io.File;
import java.io.IOException;
import java.util.List;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.controllers.ArchiveListFABController;
import corp.skaj.foretagskvitton.model.IData;
import corp.skaj.foretagskvitton.services.FileHandler;
import corp.skaj.foretagskvitton.services.IFileHandler;
import corp.skaj.foretagskvitton.services.ReceiptScanner;

public class AddReceiptActivity extends AbstractActivity implements IFileHandler {
    private static final int REQUEST_IMAGE_CAPTURE = 31415;
    private static final int REQUEST_IMAGE_CHOOSEN = 1313;
    private static final int REQUEST_WIZARD = 1337;

    private FileHandler mFileHandler;
    private String mImageAdress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.copy_image_layout);
        mImageAdress = "";
        String action = getIntent().getAction();
        mFileHandler = new FileHandler(this, this);
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
                onReadResult(null);
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
            mFileHandler.readGalleryImage(uri);
        }
    }

    // This method catches taken image by camera.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            if (mImageAdress.length() > 0) {
                Uri URI = Uri.fromFile(new File(mImageAdress));
                mImageAdress = "";
                onReadResult(URI);
            }
        } else if (requestCode == REQUEST_IMAGE_CHOOSEN && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            mFileHandler.readGalleryImage(uri);
        } else if (requestCode == REQUEST_WIZARD) {
            setResult(RESULT_OK);
            finish(); // result does not matter from wizard at this point
        } else {
            setResult(RESULT_CANCELED);
            finish();
        }
    }

    // This method starts wizard guide for adding new receipt by taking an image with camera.
    @Override
    public void onReadResult(Uri URI) {
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.spin_kit);
        FadingCircle circle = new FadingCircle();
        progressBar.setIndeterminateDrawable(circle);
        TextView text = (TextView) findViewById(R.id.loading_layout_text);
        text.setText(R.string.reading_image);

        Uri uri = URI;
        if (uri == null) {
            startWizardActivity();
            return;
        }
        collectStrings(URI, this).start();
    }

    private void startWizardActivity() {
        Intent intent = new Intent(this, WizardActivity.class);
        startActivityForResult(intent, REQUEST_WIZARD);
    }

    private Thread collectStrings(final Uri URI, final Context context) {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    List<String> strings = ReceiptScanner.collectStringsFromURI(context, URI);
                    String uriAsString = URI.toString();
                    String oldURI = getDataHandler().readData(IData.IMAGE_URI_KEY, String.class);
                    if (oldURI != null) {
                        mFileHandler.removeOldFile(oldURI);
                    }
                    getDataHandler().writeData(IData.IMAGE_URI_KEY, uriAsString);
                    getDataHandler().writeData(IData.COLLECTED_STRINGS_KEY, strings);
                    endLoadingBar();
                } catch (IOException io) {
                    System.out.println("TextCollector is not operational");
                    endLoadingBar();
                }
            }
        });
    }

    private void endLoadingBar() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                startWizardActivity();
            }
        });
    }

    //This method starts Camera.
    private void dispatchOpenCamera() {
        Intent openCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure there is a camera
        if (openCamera.resolveActivity(getPackageManager()) != null) {
            Uri imageURI = mFileHandler.setupImageFolder();
            openCamera.putExtra(MediaStore.EXTRA_OUTPUT, imageURI);
            startActivityForResult(openCamera, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void dispatchChoosePictureIntent() {
        Intent intent = new Intent(Intent.ACTION_PICK)
                .setType("image/*");
        startActivityForResult(intent, REQUEST_IMAGE_CHOOSEN);
    }

    @Override
    public void updateImageAddress(String newAddress) {
        mImageAdress = newAddress;
    }

    @Override
    public void initProgressBar() {
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.spin_kit);
        CubeGrid cubeGrid = new CubeGrid();
        progressBar.setIndeterminateDrawable(cubeGrid);
    }

    @Override
    public File getExternalFileDir() {
        return getExternalFilesDir(Environment.DIRECTORY_PICTURES);
    }
}
