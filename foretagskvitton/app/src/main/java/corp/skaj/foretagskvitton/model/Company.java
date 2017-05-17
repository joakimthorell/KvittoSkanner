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

    public boolean addEmployee(Employee employee) {
        return employees.add(employee);
    }

    public boolean removeEmployee(Employee employee) {
        return employees.size() > 1 && employees.remove(employee);
    }

    public boolean addCard(Card card) {
        return cards.add(card);
    }

    public boolean removeCard(Card card) {
        return cards.remove(card);
    }

    public boolean addSupplier(Supplier supplier) {
        return suppliers.add(supplier);
    }

    public boolean removeSupplier(Supplier supplier) {
        return suppliers.remove(supplier);
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
            if (employee.getPurchases().contains(purchase)) {
                return employee;
            }
        }
        return null;
    }

    public Card getCard(int cardNumber) {
        for (Card card : cards) {
            if (card.getCard() == cardNumber) {
                return card;
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
        for (Supplier supplier : suppliers) {
            if (supplier.getName().equals(supplierName)) {
                return supplier;
            }
        }
        return null;
    }
}