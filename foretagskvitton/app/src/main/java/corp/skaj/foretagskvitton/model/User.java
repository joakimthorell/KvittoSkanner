package corp.skaj.foretagskvitton.model;

import java.util.ArrayList;
import java.util.List;

/**
 * User class contains...
 */
public class User {
    private String name;
    private List<Company> listOfCompanies;

    public User(String name) {
        this.name = name;
        listOfCompanies = new ArrayList<>();

    }

    /**
     *
     * @param companyName
     */
    public void addNewCompany(String companyName) {
        if (!containsCompany(companyName)) {
            Company company = new Company(companyName);
        }
    }

    /**
     *
     * @param companyName
     * @return
     */
    private boolean containsCompany(String companyName) {
        for (int i = 0; i < listOfCompanies.size(); i++) {
            Company company = listOfCompanies.get(i);
            if (companyName.contains(company.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param companyName
     */
    public void removeCompany(String companyName) {
        if (containsCompany(companyName)) {
            Company company = getCompany(companyName);
            listOfCompanies.remove(company);
        }
    }

    /**
     *
     * @return
     */
    public List<Company> getListOfCompanies() {
        return listOfCompanies;
    }

    /**
     *
     * @param companyName
     * @return
     */
    public Company getCompany(String companyName) {
        for (int i = 0; i < listOfCompanies.size(); i++) {
            Company company = listOfCompanies.get(i);
            if (companyName.contains(company.getName())) {
                return company;
            }
        }
        return null;
    }

    /**
     *
     * @param receipt
     * @return
     */
    public Company getCompany(Receipt receipt) {
        for (int i = 0; i < listOfCompanies.size(); i++) {
            Company company = listOfCompanies.get(i);
            Purchase purchase = company.getPurchases();
            Receipt temp = purchase.getReceipt();
            if (receipt == temp) {
                return company;
            }
        }
        return null;
    }

    /**
     *
     * @param card
     * @return
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
     *
     * @param listOfCards
     * @param card
     * @return
     */
    private boolean containsCard(List<Card> listOfCards, Card card) {
        for(int i = 0; i < listOfCards.size(); i++) {
            Card temp = listOfCards.get(i);
            if (card == temp) {
                return true;
            }
        }
        return false;
    }

}
