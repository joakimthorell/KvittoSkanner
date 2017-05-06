package corp.skaj.foretagskvitton.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.gson.Gson;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.controllers.DataHolder;
import corp.skaj.foretagskvitton.model.User;


public class ReadDataActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init_application);
        SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
        initData(sharedPref).start();
    }

    private Thread initData(final SharedPreferences sharedPref) {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                readData(sharedPref);
                endLoadingBar();
            }
        });
    }


    private void readData(SharedPreferences sharedPref) {
        Gson gson = new Gson();
        String savedData = sharedPref.getString(User.class.getName().toString(), "");
        DataHolder dataHolder = (DataHolder) getApplicationContext();
        dataHolder.setUser(gson.fromJson(savedData, User.class));
    }

    private void endLoadingBar() {
        Intent intent = new Intent(this, AddNewPostActivity.class);
        startActivity(intent);
    }


}
