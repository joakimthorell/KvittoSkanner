package corp.skaj.foretagskvitton.model;

import java.util.ArrayList;
import java.util.List;


/**
 *
 */
public class Employee {
    private String name;
    private List<Purchase> listOfPurchases;
    private List<Comment> listOfComments;

    public Employee(String nameOfEmployee) {
        name = nameOfEmployee;
        listOfPurchases = new ArrayList<>();
        listOfComments = new ArrayList<>();
    }

    public Employee(Employee other) {
        this(other.getName());
    }

    /**
     *
     * @param purchase
     */
    public void addPurchase(Purchase purchase) {
        listOfPurchases.add(purchase);
    }

    /**
     *
     * @param purchase
     * @throws IllegalArgumentException
     */
    public void removePurchase(Purchase purchase) throws IllegalArgumentException {
        if (listOfPurchases.contains(purchase)) {
            listOfPurchases.remove(purchase);
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
        for (Purchase p : listOfPurchases) {
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
        listOfComments.add(comment);
    }

    /**
     *
     * @param comment
     * @throws IllegalArgumentException
     */
    public void removeComment(Comment comment) throws IllegalArgumentException {
        if (listOfComments.contains(comment)) {
            listOfComments.remove(comment);
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
        return listOfPurchases;
    }

    /**
     *
     * @return listOfComments
     */
    public List<Comment> getComments() {
        return listOfComments;
    }

    /**
     *
     * @return amount of purchases in list of Purchases
     */
    public int getAmountOfPurchases() {
        return listOfPurchases.size();
    }
}
