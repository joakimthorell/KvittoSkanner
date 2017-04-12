package corp.skaj.foretagskvitton.model;

/**
 * 
 */
public abstract class Purchase {
    private Receipt receipt;
    private Supplier supplier;

    protected Purchase(Receipt receipt, Supplier supplier) {
        this(receipt);
        this.supplier = supplier;
    }

    protected Purchase(Receipt receipt) {
        this.receipt = receipt;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

}
