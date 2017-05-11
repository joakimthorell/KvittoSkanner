package corp.skaj.foretagskvitton.services;

import android.app.Application;
import android.net.Uri;

import java.util.List;

import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.Employee;
import corp.skaj.foretagskvitton.model.User;

public class DataHolder extends Application {
    private boolean ONCE = true;
    private List<String> strings;
    private User user;
    private Uri URI;

    public void setURI(Uri URI) {
        this.URI = URI;
    }

    public void setStrings(List<String> strings) {
        this.strings = strings;
    }

    public void setUser(User user) {
        if (ONCE) {
            this.user = user;
            ONCE = false;
        }
    }

    public Uri getURI() {
        return URI;
    }

    public User getUser() {
        if (user == null) {
            user = new User("MASTER USER");
            Company company = new Company("DEFAULT COMPANY");
            user.addCompany(company);
            user.getCompany(company).addEmployee(new Employee(user.getName()));
        }
        return user;
    }

    public List<String> getStrings() {
        return strings;
    }
}
