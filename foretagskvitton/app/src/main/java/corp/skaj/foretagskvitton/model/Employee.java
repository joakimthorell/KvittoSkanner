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

    public void addPurchase(Purchase purchase) {
        purchases.add(purchase);
    }

    public void removePurchase(Purchase purchase) throws IllegalArgumentException {
        if (purchases.contains(purchase)) {
            purchases.remove(purchase);
        } else {
            throw new IllegalArgumentException("No such purchase existing");
        }
    }

    public boolean containsPurchase(Purchase purchase) {
        for (Purchase p : purchases) {
            if (p == purchase) {
                return true;
            }
        }
        return false;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void removeComment(Comment comment) throws IllegalArgumentException {
        if (comments.contains(comment)) {
            comments.remove(comment);
        } else {
            throw new IllegalArgumentException("No such comment existing");
        }
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

    public int getAmountOfPurchases() {
        return purchases.size();
    }
}
