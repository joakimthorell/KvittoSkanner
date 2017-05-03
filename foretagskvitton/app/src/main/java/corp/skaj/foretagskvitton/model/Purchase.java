package corp.skaj.foretagskvitton.model;

import java.io.Serializable;

/**
 * 
 */
public abstract class Purchase implements Serializable {
    private Receipt receipt;
    private Supplier supplier;

    protected Purchase(Receipt receipt, Supplier supplier) {
        this.receipt = receipt;
        this.supplier = supplier;
    }

    protected Purchase(Receipt receipt) {
        this.receipt = receipt;
    }

    /**
     *
     * @return receipt
     */
    public Receipt getReceipt() {
        return receipt;
    }

    /**
     *
     * @return supplier
     */
    public Supplier getSupplier() {
        return supplier;
    }

    /**
     *
     * @param receipt
     */
    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    /**
     *
     * @param supplier
     */
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

}
