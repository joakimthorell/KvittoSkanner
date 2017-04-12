package corp.skaj.foretagskvitton.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by annekeller on 2017-04-05.
 */

public class Company {
    private String companyName;
    private List<Employee> listOfEmployees;
    private List<Card> listOfCards;
    private List<Comment> listOfComments;
    private List<Supplier> listOfSuppliers;

    public Company (String companyName, List<Employee> listOfEmployees, List<Card> listOfCards, List<Comment> listOfComments, List<Supplier> listOfSuppliers) {
        this.companyName = companyName;
        this.listOfEmployees = listOfEmployees;
        this.listOfCards = listOfCards;
        this.listOfComments = listOfComments;
        this.listOfSuppliers = listOfSuppliers;
    }

    public Company (String companyName) {
        this.companyName = companyName;
        listOfEmployees = new ArrayList<>();
        listOfCards = new ArrayList<>();
        listOfComments = new ArrayList<>();
        listOfSuppliers = new ArrayList<>();
    }

    //Kolla så att vi inte lägger till dubletter
    public void addNewEmployee (String name) {


    }

    //Kolla så att vi inte lägger till dubletter
    public void addNewEmployee (Employee employee){
        listOfEmployees.add(employee);
    }

    //Kolla så att vi inte tar bort något som inte finns i listan
    public void removeEmployee (String name) {
        for (int i = 0; i < listOfEmployees.size(); i++) {
            if (listOfEmployees.get(i).equals(name)) {
                listOfEmployees.remove(i);
            }
        }
    }
    //Kolla så att vi inte tar bort något som inte finns i listan
    public void removeEmployee (Employee employee) {
        for (int i = 0; i < listOfEmployees.size(); i++) {
            if (listOfEmployees.get(i).equals(employee)) {
                listOfEmployees.remove(i);
            }
        }
    }

    //Kolla så att vi inte lägger till dubletter
    public void addNewCard (int cardNumber) {


    }

    //Kolla så att vi inte tar bort något som inte finns i listan
    public void removeCard (Card card) {

    }

    //Kolla så att vi inte lägger till dubletter
    public void addSupplier (String supplierName) {

    }

    //Kolla så att vi inte tar bort något som inte finns i listan
    public void removeSupplier (String supplierName) {

    }

    public String getName () {
        return companyName;
    }

    public List<Employee> getListOfEmployees() {
        return listOfEmployees;
    }

    public List<Card> getListOfCards() {
        return listOfCards;
    }

    public List<Comment> getCompanyComment () {
        return listOfComments;
    }

    public List<Supplier> getListOfSuppliers () {
        return listOfSuppliers;
    }
}
