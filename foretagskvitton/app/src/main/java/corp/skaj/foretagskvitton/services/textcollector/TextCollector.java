package corp.skaj.foretagskvitton.services.textcollector;

import java.util.List;

/**
 * This class collects information from a list of strings relevant to model data.
 */
public class TextCollector {

    private TextCollector() {
    }

    public static double getTotalSum(List<String> strings) {
        return new TotalSumCollector().getTotalSum(strings);
    }

    public static void getProducts(List<String> strings) {
        //for future extension
    }

    public static String getVat(List<String> strings){
        return new VatCollector().getVat(strings);
    }

    public static String getCard(List<String> strings) {
        return new CardCollector().getCard(strings);
    }

    public static String getDate(List<String> strings) {
        return new DateCollector().getDate(strings);
    }
}