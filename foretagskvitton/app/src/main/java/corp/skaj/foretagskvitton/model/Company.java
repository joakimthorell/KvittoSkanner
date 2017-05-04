package corp.skaj.foretagskvitton.model;

import java.util.ArrayList;
import java.util.List;


/**
 *
 */
public class Company {
    private String companyName;
    private List<Employee> employees;
    private List<Card> cards;
    private List<Comment> comments;
    private List<Supplier> suppliers;

    //TODO Check if constructor is needed
    public Company(String companyName, List<Employee> listOfEmployees, List<Card> listOfCards, List<Comment> listOfComments, List<Supplier> listOfSuppliers) {
        this.companyName = companyName;
        this.employees = employees;
        this.cards = cards;
        this.comments = comments;
        this.suppliers = suppliers;
    }

    public Company(String companyName) {
        this.companyName = companyName;
        employees = new ArrayList<>();
        cards = new ArrayList<>();
        comments = new ArrayList<>();
        suppliers = new ArrayList<>();
    }

    /**
     * @param employee
     * @throws IllegalArgumentException
     */
    public void addEmployee(Employee employee) throws IllegalArgumentException {
        if (!containsEmployee(employee.getName())) {
            employees.add(employee);
        } else {
            throw new IllegalArgumentException("Employee already existing");
        }
    }

    /**
     * @param employee
     * @throws IllegalArgumentException
     */
    public void removeEmployee(Employee employee) throws IllegalArgumentException {
        if (containsEmployee(employee.getName())) {
            employees.remove(employee);
        } else {
            throw new IllegalArgumentException("No such employee existing");
        }
    }

    //TODO Använder vi nedanstående metod? Kommer vi använda den när vi har ovanstående?
    /**
     * @param name
     * @throws IllegalArgumentException
     */
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

    /**
     * @param card
     * @throws IllegalArgumentException
     */
    public void addCard(Card card) throws IllegalArgumentException {
        if (!containsCard(card.getCard())) {
            cards.add(card);
        } else {
            throw new IllegalArgumentException("This card already exists");
        }
    }

    /**
     * @param card
     * @throws IllegalArgumentException
     */
    public void removeCard(Card card) throws IllegalArgumentException {
        if (containsCard(card.getCard())) {
            cards.remove(card);
        } else {
            throw new IllegalArgumentException("No such card existing");
        }
    }

    /**
     * @param supplier
     * @throws IllegalArgumentException
     */
    public void addSupplier(Supplier supplier) throws IllegalArgumentException {
        if (!containsSupplier(supplier.getName())) {
            suppliers.add(supplier);
        } else {
            throw new IllegalArgumentException("The supplier already exists");
        }
    }

    /**
     * @param supplier
     * @throws IllegalArgumentException
     */
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

    /**
     * @param name
     * @return <code>true</code> if listOfEmployees already contains name
     * <code>false</code> otherwise
     */
    private boolean containsEmployee(String name) {
        for (int i = 0; i < employees.size(); i++) {
            Employee temp = employees.get(i);
            if (temp.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param cardNumber
     * @return <code>true</code> if listOfCards already contains cardNumber
     * <code>false</code> otherwise
     */
    private boolean containsCard(int cardNumber) {
        for (int i = 0; i < cards.size(); i++) {
            Card temp = cards.get(i);
            if (temp.getCard() == cardNumber) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param name
     * @return <code>true</code> if listOfSuppliers already contains name
     * <code>false</code> otherwise
     */
    private boolean containsSupplier(String name) {
        for (int i = 0; i < suppliers.size(); i++) {
            Supplier temp = suppliers.get(i);
            if (temp.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return listOfEmployees
     */
    public List<Employee> getListOfEmployees() {
        return employees;
    }

    /**
     * @param employeeName
     * @return returns employee with given name, else null
     */
    public Employee getEmployee(String employeeName) {
        for (Employee employee : employees) {
            if (employee.getName().equals(employeeName)) {
                return employee;
            }
        }
        return null;
    }

    /**
     * @param purchase
     * @return employee containing given Purchase
     */
    public Employee getEmployee(Purchase purchase) {
        for (Employee employee : employees) {
            if (employee.containsPurchase(purchase)) {
                return employee;
            }
        }
        return null;
    }

    /**
     * @param cardNumber
     * @return
     * @throws IllegalArgumentException
     */
    public Card getCard(int cardNumber) throws IllegalArgumentException {
        for (int i = 0; i < cards.size(); i++) {
            Card temp = cards.get(i);
            if (temp.getCard() == cardNumber) {
                return temp;
            }
        }
        return null;
    }

    /**
     * @return companyName
     */
    public String getName() {
        return companyName;
    }

    /**
     * @return listOfCards
     */
    public List<Card> getCards() {
        return cards;
    }

    /**
     * @return listOfComments
     */
    public List<Comment> getComments() {
        return comments;
    }

    /**
     * @return listOfSuppliers
     */
    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    /**
     * @return amount of employees in listOfEmployees
     */
    public int getAmountOfEmployees() {
        return employees.size();
    }

    /**
     * @return amount of cards in listOfCards
     */
    public int getAmountOfCards() {
        return cards.size();
    }

    /**
     * @return amount of cards in listOfComments
     */
    public int getAmountOfComments() {
        return comments.size();
    }

    /**
     * @return amount of suppliers in listOfSuppliers
     */
    public int getAmountOfSuppliers() {
        return suppliers.size();
    }
}