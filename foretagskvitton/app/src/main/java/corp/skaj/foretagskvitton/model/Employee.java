package corp.skaj.foretagskvitton.model;

import java.util.ArrayList;
import java.util.List;

import corp.skaj.foretagskvitton.exceptions.NoSuchPurchaseException;

/**
 * Created by annekeller on 2017-04-05.
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

    public void addNewPurchase(Purchase purchase) {
        listOfPurchases.add(purchase);
    }

    public void removePurchase(Purchase purchase) throws NoSuchPurchaseException {
        if (listOfPurchases.contains(purchase)) {
            listOfPurchases.remove(purchase);
            return;
        }
        throw new NoSuchPurchaseException();
    }

    public void setName(String employeeName) {
        name = employeeName;
    }

    public String getName() {
        return name;
    }

    public List<Purchase> getListOfPurchases() {
        return listOfPurchases;
    }

    public List<Comment> getListOfComments() {
        return listOfComments;
    }
}
