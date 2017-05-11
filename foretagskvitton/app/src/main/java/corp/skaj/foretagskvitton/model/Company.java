package corp.skaj.foretagskvitton.model;

import java.util.ArrayList;
import java.util.List;


public class Company {
    private String companyName;
    private List<Employee> employees;
    private List<Card> cards;
    private List<Comment> comments;
    private List<Supplier> suppliers;

    public Company(String companyName) {
        this.companyName = companyName;
        employees = new ArrayList<>();
        cards = new ArrayList<>();
        comments = new ArrayList<>();
        suppliers = new ArrayList<>();
    }

    public void addEmployee(Employee employee) throws IllegalArgumentException {
        if (!containsEmployee(employee.getName())) {
            employees.add(employee);
        } else {
            throw new IllegalArgumentException("Employee already existing");
        }
    }

    public void removeEmployee(Employee employee) throws IllegalArgumentException {
        if (containsEmployee(employee.getName())) {
            employees.remove(employee);
        } else {
            throw new IllegalArgumentException("No such employee existing");
        }
    }

    public void removeEmployee(String name) throws IllegalArgumentException {
        for (int i = 0; i < employees.size(); i++) {
            Employee temp = employees.get(i);
            if (temp.getName().equals(name)) {
                employees.remove(i);
                return;
            }
        }
        throw new IllegalArgumentException("No such employee existing");
    }

    public void addCard(Card card) throws IllegalArgumentException {
        if (!containsCard(card.getCard())) {
            cards.add(card);
        } else {
            throw new IllegalArgumentException("This card already exists");
        }
    }

    public void removeCard(Card card) throws IllegalArgumentException {
        if (containsCard(card.getCard())) {
            cards.remove(card);
        } else {
            throw new IllegalArgumentException("No such card existing");
        }
    }

    public void addSupplier(Supplier supplier) throws IllegalArgumentException {
        if (!containsSupplier(supplier.getName())) {
            suppliers.add(supplier);
        } else {
            throw new IllegalArgumentException("The supplier already exists");
        }
    }

    public void removeSupplier(Supplier supplier) throws IllegalArgumentException {
        for (int i = 0; i < suppliers.size(); i++) {
            Supplier temp = suppliers.get(i);
            if (temp.getName().equals(supplier.getName())) {
                suppliers.remove(i);
                return;
            }
        }
        throw new IllegalArgumentException("No such supplier existing");
    }

    private boolean containsEmployee(String name) {
        for (int i = 0; i < employees.size(); i++) {
            Employee temp = employees.get(i);
            if (temp.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    private boolean containsCard(int cardNumber) {
        for (int i = 0; i < cards.size(); i++) {
            Card temp = cards.get(i);
            if (temp.getCard() == cardNumber) {
                return true;
            }
        }
        return false;
    }

    private boolean containsSupplier(String name) {
        for (int i = 0; i < suppliers.size(); i++) {
            Supplier temp = suppliers.get(i);
            if (temp.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public Employee getEmployee(String employeeName) {
        for (Employee employee : employees) {
            if (employee.getName().equals(employeeName)) {
                return employee;
            }
        }
        return null;
    }

    public Employee getEmployee(Purchase purchase) {
        for (Employee employee : employees) {
            if (employee.containsPurchase(purchase)) {
                return employee;
            }
        }
        return null;
    }

    public Card getCard(int cardNumber) {
        for (int i = 0; i < cards.size(); i++) {
            Card temp = cards.get(i);
            if (temp.getCard() == cardNumber) {
                return temp;
            }
        }
        return null;
    }

    public String getName() {
        return companyName;
    }

    public List<Card> getCards() {
        return cards;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    public Supplier getSupplier(String supplierName) {
        for (Supplier s : suppliers) {
            if (s.getName().equals(supplierName)) {
                return s;
            }
        }
        return null;
    }
}