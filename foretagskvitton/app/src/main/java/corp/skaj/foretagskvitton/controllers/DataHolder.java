package corp.skaj.foretagskvitton.controllers;

import android.app.Application;

import java.util.List;

import corp.skaj.foretagskvitton.model.User;

public class DataHolder extends Application {
    private User user;
    private List<String> strings;

    public void setUser(User user) {
        if (user == null) {
            this.user = user;
        }
    }

    public void setStrings(List<String> strings) {
        this.strings = strings;
    }

    public User getUser() {
        return user;
    }

    public List<String> getStrings() {
        return strings;
    }
}
