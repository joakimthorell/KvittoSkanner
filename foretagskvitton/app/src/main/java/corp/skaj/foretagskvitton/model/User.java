package corp.skaj.foretagskvitton.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * User class.
 */
public class User {
    private String name;
    private List<Company> companies;

    public User(String name) {
        this.name = name;
        companies = new ArrayList<>();
    }

    /**
     * @param company
     * @throws IllegalArgumentException
     */
    public void addCompany(Company company) throws IllegalArgumentException {
        if (!containsCompany(company.getName())) {
            companies.add(company);
        } else {
            throw new IllegalArgumentException("Company already exists");
        }
    }

    /**
     * @param company
     * @throws IllegalArgumentException
     */
    public void removeCompany(Company company) throws IllegalArgumentException {
        if (containsCompany(company.getName())) {
            companies.remove(company);
        } else {
            throw new IllegalArgumentException("No such company exists");
        }
    }

    /**
     * @param companyName
     * @return <code>true</code> if User contains Company;
     * <code>false</code> otherwise
     */
    private boolean containsCompany(String companyName) {
        for (int i = 0; i < companies.size(); i++) {
            Company temp = companies.get(i);
            if (companyName.equals(temp.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param listOfPurchases
     * @param purchase
     * @return <code>true</code> if Purchase contains Receipt;
     * <code>false</code> otherwise
     */
    private boolean containsReceipt(List<Purchase> listOfPurchases, Purchase purchase) {
        for (int i = 0; i < listOfPurchases.size(); i++) {
            Receipt temp = listOfPurchases.get(i).getReceipt();
            if (purchase.getReceipt() == temp) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param company
     * @param employee
     */
    public void addUserToCompany(Company company, Employee employee) throws IllegalArgumentException {
        company.addEmployee(employee);
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
        for (int i = 0; i < companies.size(); i++) {
            Company company = companies.get(i);
            Employee employee = company.getEmployee(name);
            if (employee != null) {
                employee.setName(name);
            }
        }
    }

    /**
     * @param company
     * @return Company
     */
    public Company getCompany(Company company) {
        for (int i = 0; i < companies.size(); i++) {
            Company temp = companies.get(i);
            if (temp.getName().equals(company.getName())) {
                return temp;
            }
        }
        return null;
    }

    /**
     * @param card
     * @return
     */
    public Company getCompany(Card card) throws IllegalArgumentException {
        for (int i = 0; i < companies.size(); i++) {
            Company temp = companies.get(i);
            Card tempCard = temp.getCard(card.getCard());
            if (tempCard.getCard() == card.getCard()) {
                return temp;
            }
        }
        return null;
    }

    /**
     * @param purchase
     * @return Company
     */
    public Company getCompany(Purchase purchase) {
        for (int i = 0; i < companies.size(); i++) {
            Company temp = companies.get(i);
            List<Purchase> listOfReceipts = temp.getEmployee(purchase).getPurchases();
            if (containsReceipt(listOfReceipts, purchase)) {
                return temp;
            }
        }
        return null;
    }

    /**
     * @return List<Company>
     */
    public List<Company> getListOfCompanies() {
        return companies;
    }

    /**
     * @return name of User
     */
    public String getName() {
        return name;
    }
}
