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
import corp.skaj.foretagskvitton.model.IData;
import corp.skaj.foretagskvitton.services.TextCollector;

public class InitWizardActivity extends AbstractActivity {
    private boolean mProgressBar;
    private boolean mNextButton;
    private List<String> mStrings;
    private Uri mURI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_init_wizard);
        final Uri mURI = catchIntent(getIntent());
        this.mURI = mURI;
        collectStrings(mURI).start();

        mProgressBar = false;
        toggleProgressBar();

        mNextButton = true;
        toggleNextButton();

        Button b = (Button) findViewById(R.id.save_button);
        b.setVisibility(View.GONE);
    }

    private Thread collectStrings(final Uri mURI) {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mStrings = TextCollector.collectStringsFromURI(getApplicationContext(), mURI);
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

                for (String s : mStrings) {
                    toPrint += s + "\n";
                }
                TextView t = (TextView) findViewById(R.id.textContainer);
                t.setText(toPrint);
            }
        });
    }

    private void toggleProgressBar() {
        int set = mProgressBar ? View.GONE : View.VISIBLE;
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(set);
        mProgressBar = !mProgressBar;
    }

    private void toggleNextButton() {
        int set = mNextButton ? View.GONE : View.VISIBLE;
        Button button = (Button) findViewById(R.id.wizardNextButton1);
        button.setVisibility(set);
        mNextButton = !mNextButton;
    }

    // If more then addNewPost will send images here, add them here.
    private Uri catchIntent(Intent intent) {
        Uri mURI = null;
        if (intent != null) {
            if (intent.getAction().equals(AddReceiptActivity.BUILD_NEW_RECEIPT)) {
                mURI = (Uri) intent.getExtras().get(AddReceiptActivity.KEY_FOR_IMAGE);
            } else if (intent.getAction().equals(InitApplicationActivity.BUILD_NEW_RECEIPT)) {
                mURI = (Uri) intent.getExtras().get(InitApplicationActivity.KEY_FOR_IMAGE);
            }
        }
        return mURI;
    }

    //TODO Below is temporary. Remove later.
    public void nextPressed(View view) {
        Intent intent = new Intent(this, WizardActivity.class);
        writeData("mStrings", mStrings);
        writeData("mURI", mURI.toString());
        startActivity(intent);

    }
}
