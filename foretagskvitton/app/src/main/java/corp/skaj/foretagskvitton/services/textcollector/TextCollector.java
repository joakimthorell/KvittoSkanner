package corp.skaj.foretagskvitton.services.textcollector;

import java.util.List;

/**
 * This class collects information from a list of strings relevant to model data.
 */
public class TextCollector {

    private TextCollector() {
    }

    public static double getTotalSum(List<String> strings) {
        TotalSumCollector tsc = new TotalSumCollector();
        return tsc.getTotalSum(strings);
    }

    public static void getProducts(List<String> strings) {
        //for future extension
    }

    public static String getVat(List<String> strings){
        return new VatCollector().getVat(strings);
    }

    public static String getCard(List<String> strings) {
        CardCollector cc = new CardCollector();
        return cc.getCard(strings);
    }

    public static String getDate(List<String> strings) {
        DateCollector dc = new DateCollector();
        return dc.getDate(strings);
    }
}