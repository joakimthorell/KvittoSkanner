package corp.skaj.foretagskvitton.model;

import java.util.ArrayList;
import java.util.List;

public class Purchase {

    public enum PurchaseType {
        PRIVATE,
        COMPANY;
    }

    private Receipt receipt;
    private Supplier supplier;
    private List<Comment> comments;
    private PurchaseType purchaseType;

    public Purchase(Receipt receipt, Supplier supplier, PurchaseType purchaseType) {
        this.receipt = receipt;
        this.supplier = supplier;
        this.purchaseType = purchaseType;
        comments = new ArrayList<>();
    }

    public Purchase(Receipt receipt, PurchaseType purchaseType) {
        this(receipt, null, purchaseType);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
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

    public List<Comment> getComments() {
        return comments;
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
