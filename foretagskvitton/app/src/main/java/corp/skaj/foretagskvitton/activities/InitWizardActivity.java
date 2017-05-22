package corp.skaj.foretagskvitton.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import java.io.File;
import java.io.IOException;
import java.util.List;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.services.ReceiptScanner;

public class InitWizardActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_init_wizard);
        Uri URI = catchIntent(getIntent());
        if (URI == null) {
            startWizard();
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
                    String oldURI = getDataHandler().readData("mURI", String.class);
                    if (oldURI != null) {
                        removeOldFile(oldURI);
                    }
                    getDataHandler().writeData("mURI", uriAsString);
                    getDataHandler().writeData("mStrings", strings);
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
