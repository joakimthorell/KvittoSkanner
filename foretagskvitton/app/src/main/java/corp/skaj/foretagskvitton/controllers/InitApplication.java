package corp.skaj.foretagskvitton.controllers;

import corp.skaj.foretagskvitton.model.User;

public class InitApplication {
    private static InitApplication instance;
    private User user;

    private InitApplication() {
        // TODO Load from txt file
        user = new User("User"); //Tillfällig lösning
    }

    public static InitApplication getInstance() {
        if (instance == null) {
            instance = new InitApplication();
        }
        return instance;
    }
}
