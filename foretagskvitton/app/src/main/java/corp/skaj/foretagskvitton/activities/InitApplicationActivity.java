package corp.skaj.foretagskvitton.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.controllers.InitApplication;

public class InitApplicationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init_application);

        initData().start();

    }

    private Thread initData() {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                InitApplication.getInstance();
                endLoadingBar();
            }
        });
    }

    private void endLoadingBar() {
        Intent intent = new Intent(this, AddNewPost.class);
        startActivity(intent);
    }
}
