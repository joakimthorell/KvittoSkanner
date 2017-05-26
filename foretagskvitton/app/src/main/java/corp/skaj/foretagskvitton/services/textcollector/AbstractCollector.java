package corp.skaj.foretagskvitton.services.textcollector;

abstract class AbstractCollector {

    AbstractCollector() {
    }

    protected static String replaceLetters(String s) {
        return s.replaceAll("i", "1")
                .replaceAll("l", "1")
                .replaceAll("o", "0")
                .replaceAll("s", "5")
                .replaceAll("b", "8");
    }
}
