package corp.skaj.foretagskvitton.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Holds purchase information: type of purchase, possible supplier, comment
 * and purchase id for identification.
 */
public class Purchase {

    public enum PurchaseType {
        PRIVATE,
        COMPANY
    }

    private Receipt receipt;
    private Supplier supplier;
    private List<Comment> comments;
    private PurchaseType purchaseType;
    private String id;

    public Purchase(Receipt receipt, Supplier supplier, PurchaseType purchaseType) {
        this.receipt = receipt;
        this.supplier = supplier;
        this.purchaseType = purchaseType;
        comments = new ArrayList<>();
        id = UUID.randomUUID().toString();
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

    public String getId() {
        return id;
    }

}
