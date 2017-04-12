package corp.skaj.foretagskvitton.model;

/**
 * Created by mattsson on 2017-04-05.
 */

public class CompanyPurchase extends Purchase {
   private Card payment;

    public CompanyPurchase(Receipt receipt, Supplier supplier, Employee employee) {
        super(receipt, supplier, employee);
    }

    public CompanyPurchase(Receipt receipt, Employee employee) {
        super(receipt, employee);
    }

    public Card getPayment() {
        return payment;
    }
}
