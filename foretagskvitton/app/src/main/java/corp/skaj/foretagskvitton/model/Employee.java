package corp.skaj.foretagskvitton.model;

import java.util.ArrayList;
import java.util.List;

import corp.skaj.foretagskvitton.exceptions.NoSuchCommentException;
import corp.skaj.foretagskvitton.exceptions.NoSuchPurchaseException;

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
     * @throws NoSuchPurchaseException
     */
    public void removePurchase(Purchase purchase) throws NoSuchPurchaseException {
        if (listOfPurchases.contains(purchase)) {
            listOfPurchases.remove(purchase);
        } else {
            throw new NoSuchPurchaseException();
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
     * @throws NoSuchCommentException
     */
    public void removeComment(Comment comment) throws NoSuchCommentException {
        if (listOfComments.contains(comment)) {
            listOfComments.remove(comment);
        } else {
            throw new NoSuchCommentException();
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
    public List<Purchase> getListOfPurchases() {
        return listOfPurchases;
    }

    /**
     *
     * @return listOfComments
     */
    public List<Comment> getListOfComments() {
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
