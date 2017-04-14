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
     * @param companyName
     * @throws IllegalInputException
     */
    public void addNewCompany(String companyName) throws IllegalInputException {
        if (!containsCompany(companyName)) {
            Company newCompany = new Company(companyName);
            listOfCompanies.add(newCompany);
            setUserAsEmpolyee(newCompany, name);
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
            Company temp = listOfCompanies.get(i);
            if (companyName == temp.getName()) {
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
            Employee temp = listofEmployees.get(i);
            List<Purchase> listOfPurchases = temp.getListOfPurchases();
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
            if (purchase.getReceipt() == temp) {
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
     * @param companyName
     * @return Company
     */
    public Company getCompany(String companyName) {
        for (int i = 0; i < listOfCompanies.size(); i++) {
            Company temp = listOfCompanies.get(i);
            if (companyName == temp.getName()) {
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
        for (int i = 0; i < listOfCompanies.size(); i++) {
            Company temp = listOfCompanies.get(i);
            List<Employee> listOfEmployees = temp.getListOfEmployees();
            if (containsPurchase(listOfEmployees, purchase)) {
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
