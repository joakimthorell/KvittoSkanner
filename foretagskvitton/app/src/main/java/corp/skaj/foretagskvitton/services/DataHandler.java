package corp.skaj.foretagskvitton.services;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

public class DataHandler extends Application implements IData {

    public <T> void writeData(String key, T t) {
        SharedPreferences.Editor spe = getEditor();
        System.out.println("THIS IS A STRING YOLO MOLO SOLO: " + toJson(t));
        spe.putString(key, toJson(t));
        spe.apply();
    }

    public Object readData(String key, Class c) {
        System.out.println("I AM HERE ");
        return new Gson().fromJson(PreferenceManager.getDefaultSharedPreferences(this).getString(key, ""), c);
    }

    private <T> String toJson(T data) {
        Gson gson = new Gson();
        return gson.toJson(data);
    }

    private SharedPreferences.Editor getEditor() {
        return PreferenceManager.getDefaultSharedPreferences(this).edit();
    }
}
