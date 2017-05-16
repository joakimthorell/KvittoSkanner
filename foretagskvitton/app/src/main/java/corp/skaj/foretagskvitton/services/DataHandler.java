package corp.skaj.foretagskvitton.services;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import corp.skaj.foretagskvitton.model.IData;

public class DataHandler extends Application implements IData {

    public <T> void writeData(String key, T writeT) {
        getEditor().putString(key, toJson(writeT)).apply();
    }

    public <T> T readData(String key, Class<T> classOfT) {
        return new Gson().fromJson(getString(key), classOfT);
    }

    private <T> String toJson(T translateT) {
        return new Gson().toJson(translateT);
    }

    private SharedPreferences.Editor getEditor() {
        return PreferenceManager.getDefaultSharedPreferences(this).edit();
    }

    private String getString(String key) {
        return PreferenceManager.getDefaultSharedPreferences(this).getString(key, "");
    }

    public void clearData() {
        getEditor().clear().apply();
    }

    public void removeData(String key) {
        getEditor().remove(key).apply();
    }
}