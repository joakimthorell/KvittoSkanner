package corp.skaj.foretagskvitton.model;

/**
 * Created by mattsson on 2017-04-05.
 */

public class PrivatePurchase extends Purchase {
    public PrivatePurchase(Receipt receipt, Supplier supplier) {
        super(receipt, supplier);
    }

    public PrivatePurchase(Receipt receipt) {
        super(receipt);
    }
}
