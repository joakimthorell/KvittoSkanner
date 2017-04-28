package corp.skaj.foretagskvitton.controllers;

public class InitApplication {
    private static InitApplication instance;

    private InitApplication() {
        // TODO Load from txt file
    }

    public static InitApplication getInstance() {
        if (instance == null) {
            instance = new InitApplication();
        }
        return instance;
    }
}
