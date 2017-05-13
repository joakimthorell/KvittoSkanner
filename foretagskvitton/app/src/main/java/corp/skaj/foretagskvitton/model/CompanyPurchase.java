package corp.skaj.foretagskvitton.model;


public class CompanyPurchase extends Purchase {
    private Card card;

    public CompanyPurchase(Receipt receipt, Supplier supplier) {
        super(receipt, supplier);
    }

    public CompanyPurchase(Receipt receipt) {
        super(receipt);
    }

    public Card getPayment() {
        return card;
    }
}
