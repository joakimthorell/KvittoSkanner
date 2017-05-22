package corp.skaj.foretagskvitton.services;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.Employee;
import corp.skaj.foretagskvitton.model.IData;
import corp.skaj.foretagskvitton.model.PurchaseList;
import corp.skaj.foretagskvitton.model.User;

public class DataHandler extends Application implements IData {

    @Override
    public <T> void writeData(String key, T writeT) {
        getEditor().putString(key, toJson(writeT)).apply();
    }

    @Override
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

    @Override
    public void clearData() {
        getEditor().clear().apply();
    } // probobly temporary for testing purpose

    @Override
    public void removeData(String key) {
        getEditor().remove(key).apply();
    }

    @Override
    public void initDefaultUser() {
        if (readData(User.class.getName(), User.class) == null) {
            User user = new User("USER");
            Company company = new Company("SKAJ Corp.");
            company.addEmployee(new Employee(user.getName()));
            user.addCompany(company);
            writeData(User.class.getName(), user);
        }
    }

    @Override
    public PurchaseList getPurchases(User user) {
        PurchaseList purchases = new PurchaseList(user);
        for (Company c : user.getCompanies()) {
            for (Employee e : c.getEmployees()) {
                purchases.addAll(e.getPurchases());
            }
        }
        return purchases;
    }

}