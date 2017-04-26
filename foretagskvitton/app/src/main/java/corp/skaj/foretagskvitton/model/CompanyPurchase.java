package corp.skaj.foretagskvitton.model;

/**
 *
 */
public class CompanyPurchase extends Purchase {
    private Card payment;

    public CompanyPurchase(Receipt receipt, Supplier supplier) {
        super(receipt, supplier);
    }

    public CompanyPurchase(Receipt receipt) {
        super(receipt);
    }

    /**
     *
     * @return payment
     */
    public Card getPayment() {
        return payment;
    }
}
