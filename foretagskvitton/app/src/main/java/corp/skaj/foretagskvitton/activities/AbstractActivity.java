package corp.skaj.foretagskvitton.activities;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;

import corp.skaj.foretagskvitton.controllers.DataHolder;
import corp.skaj.foretagskvitton.model.User;

public abstract class AbstractActivity extends AppCompatActivity {

    public void writeData() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        DataHolder dataHolder = (DataHolder)getApplicationContext();
        SharedPreferences.Editor prefsEditor = sharedPref.edit();
        //prefsEditor.clear(); Not sure if necessary...
        Gson gson = new Gson();
        String saveData = gson.toJson(dataHolder.getUser());
        prefsEditor.putString(User.class.getName().toString(), saveData);
        prefsEditor.apply();
    }
}
