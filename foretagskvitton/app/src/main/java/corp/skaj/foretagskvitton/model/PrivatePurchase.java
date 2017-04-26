package corp.skaj.foretagskvitton.model;

/**
 *
 */
public class PrivatePurchase extends Purchase {

    public PrivatePurchase(Receipt receipt, Supplier supplier) {
        super(receipt, supplier);
    }

    public PrivatePurchase(Receipt receipt) {
        super(receipt);
    }
}
