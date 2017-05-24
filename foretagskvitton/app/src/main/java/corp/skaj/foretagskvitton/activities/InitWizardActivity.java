package corp.skaj.foretagskvitton.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.ybq.android.spinkit.style.FadingCircle;

import java.io.File;
import java.io.IOException;
import java.util.List;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.IData;
import corp.skaj.foretagskvitton.services.ReceiptScanner;

public class InitWizardActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.copy_image_layout);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.spin_kit);
        FadingCircle circle = new FadingCircle();
        progressBar.setIndeterminateDrawable(circle);
        TextView text = (TextView) findViewById(R.id.loading_layout_text);
        text.setText(R.string.reading_image);

        Uri URI = catchIntent(getIntent());
        if (URI == null) {
            startWizard();
            return;
        }
        collectStrings(URI, this).start();

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
                        removeOldFile(oldURI);
                    }
                    getDataHandler().writeData(IData.IMAGE_URI_KEY, uriAsString);
                    getDataHandler().writeData(IData.COLLECTED_STRINGS_KEY, strings);
                    endLoadingBar();
                } catch (IOException io) {
                    System.out.println("TextCollector is not operational");
                    //endLoadingBar();
                }
            }
        });
    }

    private void endLoadingBar() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                startWizard();
            }
        });
    }

    // If more then addNewPost will send images here, add them here.
    private Uri catchIntent(Intent intent) {
        if (intent != null) {
            if (intent.getAction().equals(AddReceiptActivity.BUILD_NEW_RECEIPT)) {
                return (Uri) intent.getExtras().get(AddReceiptActivity.KEY_FOR_IMAGE);
            } // add more here maybe
        }
        return null;
    }

    private void removeOldFile(String oldUri) {
        File fileToDelete = new File(oldUri);
        if (fileToDelete.delete()) {
            System.out.println("Removed old unused image");
        } else {
            System.out.println("File not deleted, path: " + oldUri);
        }
    }

    public void startWizard() {
        Intent intent = new Intent(this, WizardActivity.class);
        startActivity(intent);
    }
}
