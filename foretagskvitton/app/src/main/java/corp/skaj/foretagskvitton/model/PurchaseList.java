package corp.skaj.foretagskvitton.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * List of all locally saved purchases, enabling sort and filter functions
 * for listing purchases.
 */
public class PurchaseList extends ArrayList<Purchase> {
    private User user;

    public PurchaseList(User user) {
        this.user = user;
    }

    public PurchaseList getPurchases(Company company) {
        PurchaseList list = new PurchaseList(user);
        for (Employee e : company.getEmployees()) {
            list.addAll(e.getPurchases());
        }
        return list;
    }

    public PurchaseList getPurchases(Employee employee) {
        PurchaseList list = new PurchaseList(user);
        list.addAll(employee.getPurchases());
        return list;
    }

    public PurchaseList getPurchases(Category category) {
        PurchaseList purchases = new PurchaseList(user);
        for (Purchase p : this) {
            for (Product pr : p.getReceipt().getProducts()) {
                if (pr.getCategory().equals(category)) {
                    purchases.add(p);
                }
            }
        }
        return purchases;
    }

    public Purchase getPurchase(String ID) {
        for (Purchase p : this) {
            if (p.getId().equals(ID)) {
                return p;
            }
        }
        return null;
    }

    public static PurchaseList convert(List<Purchase> list, User user) {
        PurchaseList purchaseList = new PurchaseList(user);
        purchaseList.addAll(list);
        return purchaseList;
    }

    public void sortByDate() {
        Collections.sort(this, new sortByDate());
    }

    public void sortByPrice() {
        Collections.sort(this, new sortByPrice());
    }

    private class sortByPrice implements Comparator<Purchase> {

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

    private class sortByDate implements Comparator<Purchase> {

        @Override
        public int compare(Purchase o1, Purchase o2) {
            return o1.getReceipt().getDate().compareTo(o2.getReceipt().getDate());
        }
    }
}