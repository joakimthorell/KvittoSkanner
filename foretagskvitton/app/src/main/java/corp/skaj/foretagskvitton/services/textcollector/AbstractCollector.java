package corp.skaj.foretagskvitton.services.textcollector;

public abstract class AbstractCollector {

    public AbstractCollector() {
    }

    protected static String replaceLetters(String s) {
        return s.replaceAll("i", "1")
                .replaceAll("l", "1")
                .replaceAll("o", "0")
                .replaceAll("s", "5")
                .replaceAll("b", "8");
    }
}
