package corp.skaj.foretagskvitton.model;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String name;
    private List<Purchase> purchases;
    private List<Comment> comments;

    public Employee(String nameOfEmployee) {
        name = nameOfEmployee;
        purchases = new ArrayList<>();
        comments = new ArrayList<>();
    }

    public Employee(Employee other) {
        this(other.getName());
    }

    public boolean addPurchase(Purchase purchase) {
        return purchases.add(purchase);
    }

    public boolean removePurchase(Purchase purchase) {
        return purchases.remove(purchase);
    }

    public boolean addComment(Comment comment) {
        return comments.add(comment);
    }

    public boolean removeComment(Comment comment) {
        return comments.remove(comment);
    }

    public void setName(String employeeName) {
        name = employeeName;
    }

    public String getName() {
        return name;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public List<Comment> getComments() {
        return comments;
    }
}
