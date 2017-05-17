package corp.skaj.foretagskvitton.services;

import java.util.Comparator;

import corp.skaj.foretagskvitton.model.Purchase;

public class PurchaseSortByDate implements Comparator<Purchase> {

    @Override
    public int compare(Purchase o1, Purchase o2) {
        return o1.getReceipt().getDate().compareTo(o2.getReceipt().getDate());
    }
}
