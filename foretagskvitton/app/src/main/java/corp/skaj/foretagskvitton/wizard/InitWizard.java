package corp.skaj.foretagskvitton.wizard;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.activities.AddNewPost;
import corp.skaj.foretagskvitton.activities.WizardActivity;
import corp.skaj.foretagskvitton.controllers.WizardController;
import corp.skaj.foretagskvitton.services.ReceiptScanner;
import corp.skaj.foretagskvitton.services.TextCollector;

/**
 *
 */
public class InitWizard extends AppCompatActivity {
    public static final String KEY_FOR_WIZARD_CONTROLLER = "corp.skaj.foretagskvitton.wizard.KEY_FOR_CONTROLLER";
    private boolean progressBarShowing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_init_wizard);
        final Uri URI = catchIntent(getIntent());
        collectStrings(URI).start();

        progressBarShowing = false;
        toggleProgressBar();
    }

    private Thread collectStrings(final Uri URI) {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    List<String> listOfStrings = TextCollector.collectStringsFromImage(getApplicationContext(), URI);
                    ArrayList<String> list = new ArrayList<>();
                    list.addAll(listOfStrings);
                    endLoadingBar(list);
                } catch (IOException io) {
                    System.out.println("TextCollector is not operational");
                }
            }
        });
    }

    private void endLoadingBar(final ArrayList<String> arrayList) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplication(), WizardActivity.class);
                Bundle b = new Bundle();
                b.putStringArrayList(KEY_FOR_WIZARD_CONTROLLER, arrayList);
                intent.putExtras(b);

                toggleProgressBar();

                startActivity(intent);
            }
        });
    }

    private void toggleProgressBar() {
        int set = progressBarShowing ? View.GONE : View.VISIBLE;
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(set);
        progressBarShowing = !progressBarShowing;
    }

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
