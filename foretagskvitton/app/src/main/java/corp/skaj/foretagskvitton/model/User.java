package corp.skaj.foretagskvitton.model;

import corp.skaj.foretagskvitton.exceptions.IllegalInputException;
import corp.skaj.foretagskvitton.exceptions.NoSuchCompanyException;

import java.util.ArrayList;
import java.util.List;

/**
 * User class.
 */
public class User {
    private String name;
    private List<Company> listOfCompanies;

    public User(String name) {
        this.name = name;
        listOfCompanies = new ArrayList<>();
    }

    /**
     * @param companyName
     * @throws IllegalInputException
     */
    public void addNewCompany(String companyName) throws IllegalInputException {
        if (!containsCompany(companyName)) {
            Company company = new Company(companyName);
            listOfCompanies.add(company);
            setUserAsEmpolyee(company, name);
        } else {
            throw new IllegalInputException(this);
        }
    }

    /**
     * @param companyName
     * @throws NoSuchCompanyException
     */
    public void removeCompany(String companyName) throws NoSuchCompanyException {
        if (containsCompany(companyName)) {
            Company company = getCompany(companyName);
            listOfCompanies.remove(company);
        } else {
            throw new NoSuchCompanyException(companyName);
        }
    }

    /**
     * @param companyName
     * @return <code>true</code> if User contains Company;
     * <code>false</code> otherwise
     */
    private boolean containsCompany(String companyName) {
        for (int i = 0; i < listOfCompanies.size(); i++) {
            Company company = listOfCompanies.get(i);
            if (companyName == company.getName()) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param listOfCards
     * @param card
     * @return <code>true</code> if Company contains Card;
     * <code>false</code> otherwise
     */
    private boolean containsCard(List<Card> listOfCards, Card card) {
        for (int i = 0; i < listOfCards.size(); i++) {
            Card temp = listOfCards.get(i);
            if (card == temp) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param listofEmployees
     * @param purchase
     * @return <code>true</code> if Employee contains Purchase;
     * <code>false</code> otherwise
     */
    private boolean containsPurchase(List<Employee> listofEmployees, Purchase purchase) {
        for (int i = 0; i < listofEmployees.size(); i++) {
            Employee employee = listofEmployees.get(i);
            List<Purchase> listOfPurchases = employee.getListOfPurchases();
            if (containsReceipt(listOfPurchases, purchase)) {
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
            Receipt receipt = purchase.getReceipt();
            if (receipt == temp) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param company
     * @param name
     */
    public void setUserAsEmpolyee(Company company, String name) {
        try {
            company.addNewEmployee(name);
        } catch (IllegalInputException iie) {
            iie.printStackTrace();
        }
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
        for (int i = 0; i < listOfCompanies.size(); i++) {
            Company company = listOfCompanies.get(i);
            Employee temp = company.getEmployee(name);
            if (temp != null) {
                temp.setName(name);
            }
        }
    }

    /**
     * @param companyName
     * @return Company
     */
    public Company getCompany(String companyName) {
        for (int i = 0; i < listOfCompanies.size(); i++) {
            Company company = listOfCompanies.get(i);
            if (companyName == company.getName()) {
                return company;
            }
        }
        return null;
    }

    /**
     * @param purchase
     * @return Company
     */
    public Company getCompany(Purchase purchase) {
        for (int i = 0; i < listOfCompanies.size(); i++) {
            Company company = listOfCompanies.get(i);
            List<Employee> listOfEmployees = company.getListOfEmployees();
            if (containsPurchase(listOfEmployees, purchase)) {
                return company;
            }
        }
        return null;
    }

    /**
     * @param card
     * @return Company
     */
    public Company getCompany(Card card) {
        for (int i = 0; i < listOfCompanies.size(); i++) {
            Company company = listOfCompanies.get(i);
            List<Card> listOfCards = company.getListOfCards();
            if (containsCard(listOfCards, card)) {
                return company;
            }
        }
        return null;
    }

    /**
     * @return List<Company>
     */
    public List<Company> getListOfCompanies() {
        return listOfCompanies;
    }

    /**
     * @return
     */
    public String getName() {
        return name;
    }
}
