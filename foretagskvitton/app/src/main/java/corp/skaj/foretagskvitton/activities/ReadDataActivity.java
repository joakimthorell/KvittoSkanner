package corp.skaj.foretagskvitton.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.controllers.DataHolder;
import corp.skaj.foretagskvitton.model.User;


public class ReadDataActivity extends AbstractActivity {

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
                readData();
                endLoadingBar();
            }
        });
    }


    private void readData() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String savedData = sharedPref.getString(User.class.getName().toString(), "");
        DataHolder dataHolder = (DataHolder) getApplicationContext();
        dataHolder.setUser(gson.fromJson(savedData, User.class));

        // Testing...
        /*
        try {
            System.out.println(dataHolder.getUser().getCompanies().get(0).getName());
        } catch (Exception e) {}
        */
    }

    private void endLoadingBar() {
        Intent intent = new Intent(this, AddNewPostActivity.class);
        startActivity(intent);
    }


}
