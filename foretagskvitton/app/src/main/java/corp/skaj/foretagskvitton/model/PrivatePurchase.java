package corp.skaj.foretagskvitton.model;

import java.io.Serializable;

/**
 *
 */
public class PrivatePurchase extends Purchase implements Serializable {

    public PrivatePurchase(Receipt receipt, Supplier supplier) {
        super(receipt, supplier);
    }

    public PrivatePurchase(Receipt receipt) {
        super(receipt);
    }
}
