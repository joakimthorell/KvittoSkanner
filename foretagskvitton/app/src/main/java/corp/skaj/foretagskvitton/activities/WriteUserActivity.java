package corp.skaj.foretagskvitton.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;

public class WriteUserActivity extends AppCompatActivity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences userPref = getPreferences(MODE_PRIVATE);
        writeData(userPref);
    }

    private void writeData(SharedPreferences userPref) {
        SharedPreferences.Editor prefEditor = userPref.edit();
        Gson gson = new Gson();
        //String savedData = gson.toJson(DataHandler.getUser());

        //prefEditor.putString(DataHandler.getGetDataKey(), savedData);
        prefEditor.apply();
    }
}
