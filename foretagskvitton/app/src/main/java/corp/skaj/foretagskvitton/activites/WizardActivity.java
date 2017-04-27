package corp.skaj.foretagskvitton.activites;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import corp.skaj.foretagskvitton.R;

public class WizardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wizard);

        Uri URI = catchIntent(getIntent());


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
