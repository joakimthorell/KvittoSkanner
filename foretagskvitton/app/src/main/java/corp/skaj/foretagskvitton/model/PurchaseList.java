package corp.skaj.foretagskvitton.model;

import java.util.ArrayList;
import java.util.Comparator;

public class PurchaseList extends ArrayList<Purchase> {

    public PurchaseList() {
    }

    private class PurchaseSortByPrice implements Comparator<Purchase> {
        @Override
        public int compare(Purchase o1, Purchase o2) {
            double r1 = o1.getReceipt().getTotal();
            double r2 = o2.getReceipt().getTotal();
            if (r1 > r2) {
                return 1;
            } else if (r1 == r2) {
                return 0;
            }
            return -1;
        }
    }
}

