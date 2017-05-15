package corp.skaj.foretagskvitton.model;


public class Purchase {
    private Receipt receipt;
    private Supplier supplier;

    protected Purchase(Receipt receipt, Supplier supplier) {
        this.receipt = receipt;
        this.supplier = supplier;
    }

    protected Purchase(Receipt receipt) {
        this.receipt = receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public Supplier getSupplier() {
        return supplier;
    }

}
