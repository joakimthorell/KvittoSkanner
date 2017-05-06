package corp.skaj.foretagskvitton.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.controllers.DataHolder;
import corp.skaj.foretagskvitton.services.TextCollector;

public class InitWizardActivity extends AbstractActivity {
    public static final String KEY_FOR_WIZARD_CONTROLLER = "corp.skaj.foretagskvitton.wizard.KEY_FOR_CONTROLLER";
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

        Button b = (Button) findViewById(R.id.save_button);
        b.setVisibility(View.GONE);
    }

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

    private void endLoadingBar() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                toggleProgressBar();
                toggleNextButton();
                Button b = (Button) findViewById(R.id.save_button);
                b.setVisibility(View.VISIBLE);
                String toPrint = "";

                for (String s : listOfStrings) {
                    toPrint += s + "\n";
                }
                TextView t = (TextView) findViewById(R.id.textContainer);
                t.setText(toPrint);
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
        Button button = (Button) findViewById(R.id.wizardNextButton1);
        button.setVisibility(set);
        nextButtonShowing = !nextButtonShowing;
    }

    // If more then addNewPost will send images here, add them here.
    private Uri catchIntent(Intent intent) {
        Uri URI = null;
        if (intent != null) {
            if (intent.getAction().equals(AddNewPostActivity.BUILD_NEW_RECEIPT)) {
                URI = (Uri) intent.getExtras().get(AddNewPostActivity.KEY_FOR_IMAGE);
            }
        }
        return URI;
    }

    //TODO Below is temporary. Remove later.

    public void nextPressed(View view) {
        Intent intent = new Intent(this, WizardActivity.class);
        DataHolder dataHolder = (DataHolder)getApplicationContext();
        dataHolder.setStrings(listOfStrings);
        startActivity(intent);
    }

    public void saveButtonPressed(View view) {

    }
}
