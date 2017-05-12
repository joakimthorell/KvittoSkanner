package corp.skaj.foretagskvitton.services;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import corp.skaj.foretagskvitton.model.IData;

public class DataHandler extends Application implements IData {

    public <T> void writeData(String key, T t) {
        SharedPreferences.Editor spe = getEditor();
        spe.putString(key, toJson(t));
        spe.apply();
    }

    public <T> T readData(String key, Class<T> classOfT) {
        return new Gson().fromJson(PreferenceManager.getDefaultSharedPreferences(this).getString(key, ""), classOfT);
    }

    private <T> String toJson(T data) {
        Gson gson = new Gson();
        return gson.toJson(data);
    }

    private SharedPreferences.Editor getEditor() {
        return PreferenceManager.getDefaultSharedPreferences(this).edit();
    }
}
