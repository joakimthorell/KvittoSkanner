package corp.skaj.foretagskvitton.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PurchaseList extends ArrayList<Purchase> {
    private User user;

    public PurchaseList(User user) {
        this.user = user;
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

    public PurchaseList getReceipts(Company company) {
        PurchaseList list = new PurchaseList(user);
        for (Employee e : company.getEmployees()) {
            list.addAll(e.getPurchases());
        }
        return list;
    }

    public PurchaseList getReceipts(Employee employee) {
        PurchaseList list = new PurchaseList(user);
        list.addAll(employee.getPurchases());
        return list;
    }

    public PurchaseList getReceipts(Category category) {
        PurchaseList purchases = new PurchaseList(user);
        for (Company c : user.getCompanies()) {
            for (Employee e : c.getEmployees()) {
                for (Purchase p : e.getPurchases()) {
                    for (Product product : p.getReceipt().getProducts()) {
                        if (category.equals(product.getCategory())) {
                            purchases.add(p);
                        }
                    }
                }
            }
        }
        return purchases;
    }

    public void sortByDate() {
        Collections.sort(this, new sortByDate());
    }

    public void sortByPrice() {
        Collections.sort(this, new sortByPrice());
    }
}

