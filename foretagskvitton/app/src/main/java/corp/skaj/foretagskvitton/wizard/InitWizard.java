package corp.skaj.foretagskvitton.wizard;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.activites.AddNewPost;
import corp.skaj.foretagskvitton.services.ReceiptScanner;
import corp.skaj.foretagskvitton.services.TextCollector;

/**
 *
 */
public class InitWizard extends AppCompatActivity {
    private boolean progressBarShowing;
    private boolean nextButtonShowing;
    private List<String> listOfStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_init_wizard);
        final Uri URI = catchIntent(getIntent());
        collectStrings(URI).start();
        
        progressBarShowing = false;
        toggleProgressBar();

        nextButtonShowing = true;
        toggleNextButton();

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
     * This method ends loadingbar on screen when all Strings are collected
     */
    private void endLoadingBar() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                toggleProgressBar();
                ReceiptScanner receiptScanner = new ReceiptScanner();
                //String toPrint = "";
                /*
                for (String s : listOfStrings) {
                    toPrint += s + "\n";
                }
                */
                String toPrint = receiptScanner.getTotalCost(listOfStrings);
                TextView textView = (TextView) findViewById(R.id.textContainer);
                textView.setText(toPrint);
                toggleNextButton();
            }
        });
    }

    private void toggleProgressBar() {
        int set = progressBarShowing ? View.GONE : View.VISIBLE;
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(set);
        progressBarShowing = !progressBarShowing;
    }

    private void toggleNextButton() {
        int set = nextButtonShowing ? View.GONE : View.VISIBLE;
        Button button = (Button) findViewById(R.id.nextButton);
        button.setVisibility(set);
        nextButtonShowing = !nextButtonShowing;
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
