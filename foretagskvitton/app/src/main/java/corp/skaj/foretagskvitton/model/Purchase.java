package corp.skaj.foretagskvitton.model;


public class Purchase {

    public enum PurchaseType {
        PRIVATE,
        COMPANY;
    }

    private Receipt receipt;
    private Supplier supplier;

    private PurchaseType purchaseType;

    public Purchase(Receipt receipt, Supplier supplier, PurchaseType purchaseType) {
        this.receipt = receipt;
        this.supplier = supplier;
        this.purchaseType = purchaseType;
    }

    public Purchase(Receipt receipt, PurchaseType purchaseType) {
        this.receipt = receipt;
        this.purchaseType = purchaseType;
    }

    public void setPurchaseType(PurchaseType purchaseType) {
        this.purchaseType = purchaseType;
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

    public PurchaseType getPurchaseType() {
        return purchaseType;
    }


}
