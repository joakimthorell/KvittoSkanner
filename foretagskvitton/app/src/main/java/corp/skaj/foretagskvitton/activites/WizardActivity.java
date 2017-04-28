package corp.skaj.foretagskvitton.activites;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.services.ReceiptScanner;
import corp.skaj.foretagskvitton.services.TextCollector;

/**
 *
 */
public class WizardActivity extends AppCompatActivity {
    private List<String> listOfStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_wizard);
        final Uri URI = catchIntent(getIntent());
        collectStrings(URI).start();
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
    }

    /**
     * This method starts a thread to allow application to collect all available Strings from image.
     * @param URI
     * @return Thread
     */
    private Thread collectStrings(final Uri URI) {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    listOfStrings = TextCollector.collectStringsFromImage(getApplicationContext(), URI);
                    endLoadingBar();
                } catch (IOException io) {
                    System.out.println("TextCollector is not operational");
                }
            }
        });
    }

    /**
     * This method ends loadingbar on screen when all Strings are collected.
     */
    private void endLoadingBar() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
                progressBar.setVisibility(View.GONE);
                ReceiptScanner receiptScanner = new ReceiptScanner();
                String toPrint = "";
                /*
                for (String s : listOfStrings) {
                    toPrint += s + "\n";
                }
                */
                toPrint = receiptScanner.getTotalCost(listOfStrings);
                TextView textView = (TextView) findViewById(R.id.textContainer);
                textView.setText(toPrint);
            }
        });
    }

    /**
     * This method catches Intents (information) sent from other classes.
     * @param intent
     * @return Uri
     */
    // If more then addNewPost will send images here, add them here
    private Uri catchIntent(Intent intent) {
        Uri URI = null;
        if (intent != null) {
            if (intent.getAction().equals(AddNewPost.BUILD_NEW_RECEIPT)) {
                URI = (Uri) intent.getExtras().get(AddNewPost.KEY_FOR_IMAGE);
            }
        }
        return URI;
    }
}
