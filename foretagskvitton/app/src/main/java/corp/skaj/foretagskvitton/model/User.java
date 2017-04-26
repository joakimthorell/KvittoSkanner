package corp.skaj.foretagskvitton.model;

import corp.skaj.foretagskvitton.exceptions.IllegalInputException;
import corp.skaj.foretagskvitton.exceptions.NoSuchCardException;
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
     * @param company
     * @throws IllegalInputException
     */
    public void addCompany(Company company) throws IllegalInputException {
        if (!containsCompany(company.getName())) {
            listOfCompanies.add(company);
        } else {
            throw new IllegalInputException(this);
        }
    }

    /**
     * @param company
     * @throws NoSuchCompanyException
     */
    public void removeCompany(Company company) throws NoSuchCompanyException {
        if (containsCompany(company.getName())) {
            listOfCompanies.remove(company);
        } else {
            throw new NoSuchCompanyException(company.getName());
        }
    }

    /**
     * @param companyName
     * @return <code>true</code> if User contains Company;
     * <code>false</code> otherwise
     */
    private boolean containsCompany(String companyName) {
        for (int i = 0; i < listOfCompanies.size(); i++) {
            Company temp = listOfCompanies.get(i);
            if (companyName.equals(temp.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param company
     * @param employee
     */
    public void addUserToCompany(Company company, Employee employee) {
        try {
            company.addEmployee(employee);
        } catch (IllegalInputException iie) {
            iie.printStackTrace();
        }
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
        for (int i = 0; i < listOfCompanies.size(); i++) {
            Company company = listOfCompanies.get(i);
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
        for (int i = 0; i < listOfCompanies.size(); i++) {
            Company temp = listOfCompanies.get(i);
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
    public Company getCompany(Card card) {
        for (int i = 0; i < listOfCompanies.size(); i++) {
            Company temp = listOfCompanies.get(i);
            try {
                Card tempCard = temp.getCard(card.getCard());
                if (tempCard.getCard() == card.getCard()){
                    return temp;
                }
            } catch (NoSuchCardException nsce) {
                nsce.getCause();
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
            Company temp = listOfCompanies.get(i);
            List<Purchase> listOfReceipts = temp.getEmployee(purchase).getPurchases();
            if (containsReceipt(listOfReceipts, purchase)) {
                return temp;
            }
        }
        return null;
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
     * @return List<Company>
     */
    public List<Company> getListOfCompanies() {
        return listOfCompanies;
    }

    /**
     * @return name of User
     */
    public String getName() {
        return name;
    }
}
