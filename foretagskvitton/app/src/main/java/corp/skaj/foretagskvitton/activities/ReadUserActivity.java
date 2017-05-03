package corp.skaj.foretagskvitton.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.controllers.DataHandler;
import corp.skaj.foretagskvitton.model.User;

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
                readData(userPref);
                endLoadingBar();
            }
        });
    }

    private void readData(SharedPreferences userPref) {
        Gson gson = new Gson();
        String savedData = userPref.getString(DataHandler.getGetDataKey(), "");
        User user = savedData.isEmpty() ? new User("Hejsan") : gson.fromJson(savedData, User.class);
        System.out.println(user.getName());
        DataHandler.setUser(user);

        // Detta måste testas på mobiltelefon
    }

    private void endLoadingBar() {
        Intent intent = new Intent(this, AddNewPost.class);
        startActivity(intent);
    }


}
