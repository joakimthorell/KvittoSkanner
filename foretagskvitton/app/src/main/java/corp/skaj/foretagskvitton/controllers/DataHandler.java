package corp.skaj.foretagskvitton.controllers;

import corp.skaj.foretagskvitton.model.User;

public class DataHandler {
    private static final String GET_DATA_KEY = "GET_USER_DATA";
    private static User user;

    private DataHandler() {
    }

    public static void setUser(User userman) {
        if (user == null) {
            user = userman;
        }
    }

    public static User getUser() {
        return user;
    }

    public static String getGetDataKey() {
        return GET_DATA_KEY;
    }
}