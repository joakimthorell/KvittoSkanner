package corp.skaj.foretagskvitton.model;


import java.util.ArrayList;
import java.util.List;

public class Purchase {

    private static int ID_BUILDER = 0;

    public enum PurchaseType {
        PRIVATE,
        COMPANY;
    }

    private Receipt receipt;
    private Supplier supplier;
    private List<Comment> comments;
    private int id;

    private PurchaseType purchaseType;

    public Purchase(Receipt receipt, Supplier supplier, PurchaseType purchaseType) {
        this.receipt = receipt;
        this.supplier = supplier;
        this.purchaseType = purchaseType;
        comments = new ArrayList<>();
        id = Purchase.ID_BUILDER++;

        System.out.println("Receipt ID is now    :   " + id);
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

    public int getId() {
        return id;
    }


}
