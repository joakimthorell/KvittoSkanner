package corp.skaj.foretagskvitton.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 *
 */
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

    /**
     *
     * @param purchase
     */
    public void addPurchase(Purchase purchase) {
        purchases.add(purchase);
    }

    /**
     *
     * @param purchase
     * @throws IllegalArgumentException
     */
    public void removePurchase(Purchase purchase) throws IllegalArgumentException {
        if (purchases.contains(purchase)) {
            purchases.remove(purchase);
        } else {
            throw new IllegalArgumentException("No such purchase existing");
        }
    }

    /**
     *
     * @param purchase
     * @return
     */
    public boolean containsPurchase(Purchase purchase) {
        for (Purchase p : purchases) {
            if (p == purchase) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param comment
     */
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    /**
     *
     * @param comment
     * @throws IllegalArgumentException
     */
    public void removeComment(Comment comment) throws IllegalArgumentException {
        if (comments.contains(comment)) {
            comments.remove(comment);
        } else {
            throw new IllegalArgumentException("No such comment existing");
        }
    }

    /**
     *
     * @param employeeName
     */
    public void setName(String employeeName) {
        name = employeeName;
    }

    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return listOfPurchases
     */
    public List<Purchase> getPurchases() {
        return purchases;
    }

    /**
     *
     * @return listOfComments
     */
    public List<Comment> getComments() {
        return comments;
    }

    /**
     *
     * @return amount of purchases in list of Purchases
     */
    public int getAmountOfPurchases() {
        return purchases.size();
    }
}
