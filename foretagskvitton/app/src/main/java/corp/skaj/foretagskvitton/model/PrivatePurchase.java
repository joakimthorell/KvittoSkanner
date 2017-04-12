package corp.skaj.foretagskvitton.model;

/**
 * Created by mattsson on 2017-04-05.
 */

public class PrivatePurchase extends Purchase {
    public PrivatePurchase(Receipt receipt, Supplier supplier, Employee employee) {
        super(receipt, supplier, employee);
    }

    public PrivatePurchase(Receipt receipt, Employee employee) {
        super(receipt, employee);
    }
}
