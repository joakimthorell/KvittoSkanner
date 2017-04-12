package corp.skaj.foretagskvitton.model;

import java.util.ArrayList;
import java.util.List;

import corp.skaj.foretagskvitton.exceptions.IllegalInputException;
import corp.skaj.foretagskvitton.exceptions.NoSuchCardException;
import corp.skaj.foretagskvitton.exceptions.NoSuchEmployeeException;
import corp.skaj.foretagskvitton.exceptions.NoSuchSupplierException;

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

    public void addNewEmployee (String name) throws IllegalInputException{
        if (containsEmployee(name)) {
            listOfEmployees.add(new Employee(name));
        } else {
            throw new IllegalInputException(this);
        }
    }

    public void addNewEmployee (Employee employee) throws IllegalInputException {
        if(containsEmployee(employee)) {
            listOfEmployees.add(employee);
        } else {
            throw new IllegalInputException(this);
        }
    }

    public void removeEmployee (String name) throws NoSuchEmployeeException{
        for (int i = 0; i < listOfEmployees.size(); i++) {
            Employee temp = listOfEmployees.get(i);
            if (temp.getName().equals(name)) {
                listOfEmployees.remove(i);
            } else {
                throw new NoSuchEmployeeException();
            }
        }
    }
    public void removeEmployee (Employee employee) throws NoSuchEmployeeException{
        for (int i = 0; i < listOfEmployees.size(); i++) {
            Employee temp = listOfEmployees.get(i);
            if (temp.equals(employee)) {
                listOfEmployees.remove(i);
            } else {
                throw new NoSuchEmployeeException();
            }
        }
    }

    public void addNewCard (int cardNumber) throws IllegalInputException {
        if (containsCard(cardNumber)) {
            listOfCards.add(new Card(cardNumber));
        } else {
            throw new IllegalInputException(this);
        }
    }

    public void removeCard (Card card) throws NoSuchCardException {
        for (int i = 0; i < listOfCards.size(); i++) {
            Card temp = listOfCards.get(i);
            if(temp.equals(card)) {
                listOfCards.remove(i);
            } else {
                throw new NoSuchCardException();
            }
        }
    }

    public void addSupplier (String supplierName) throws IllegalInputException {
        if(containsSupplier(supplierName)) {
            listOfSuppliers.add(new Supplier(supplierName));
        } else {
            throw new IllegalInputException(this);
        }
    }

    public void removeSupplier (String supplierName) throws NoSuchSupplierException {
        for (int i = 0; i < listOfSuppliers.size(); i++) {
            Supplier temp = listOfSuppliers.get(i);
            if (temp.equals(supplierName)) {
                listOfSuppliers.remove(i);
            } else {
                throw new NoSuchSupplierException();
            }
        }

    }

    public boolean containsEmployee(Employee employee) {
        for (int i = 0; i < listOfEmployees.size(); i++) {
            if (listOfEmployees.get(i).equals(employee)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsEmployee(String name) {
        for (int i = 0; i < listOfEmployees.size(); i++) {
            if(listOfEmployees.get(i).getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsSupplier(String name) {
        for(int i = 0; i < listOfSuppliers.size(); i++) {
            if(listOfSuppliers.get(i).toString().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsCard (int cardNumber) {
        for(int i = 0; i < listOfCards.size(); i++) {
            if(listOfCards.get(i).getLastFourDigits() == cardNumber) {
                return true;
            }
        }
        return false;
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
