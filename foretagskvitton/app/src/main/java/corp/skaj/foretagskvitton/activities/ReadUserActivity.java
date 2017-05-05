package corp.skaj.foretagskvitton.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import corp.skaj.foretagskvitton.R;


public class ReadUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init_application);
        SharedPreferences userPref = getPreferences(MODE_PRIVATE);
        initData(userPref).start();
    }

    private Thread initData(final SharedPreferences userPref) {
        return new Thread(new Runnable() {
            @Override
            public void run() {

                //TODO Fix diss.

                readData(userPref);
                endLoadingBar();
            }
        });
    }


    private void readData(SharedPreferences userPref) {
    }

    private void endLoadingBar() {
        Intent intent = new Intent(this, AddNewPostActivity.class);
        startActivity(intent);
    }


}
