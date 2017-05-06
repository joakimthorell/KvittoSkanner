package corp.skaj.foretagskvitton.model;

import java.util.ArrayList;
import java.util.List;


public class User {
    private List<Company> companies;
    private String name;

    public User(String name) {
        this.name = name;
        companies = new ArrayList<>();
    }

    public void addCompany(Company company) throws IllegalArgumentException {
        if (!containsCompany(company.getName())) {
            companies.add(company);
        } else {
            throw new IllegalArgumentException("Company already exists");
        }
    }

    public void removeCompany(Company company) throws IllegalArgumentException {
        if (containsCompany(company.getName())) {
            companies.remove(company);
        } else {
            throw new IllegalArgumentException("No such company exists");
        }
    }

    private boolean containsCompany(String companyName) {
        for (int i = 0; i < companies.size(); i++) {
            Company temp = companies.get(i);
            if (companyName.equals(temp.getName())) {
                return true;
            }
        }
        return false;
    }

    private boolean containsReceipt(List<Purchase> listOfPurchases, Purchase purchase) {
        for (int i = 0; i < listOfPurchases.size(); i++) {
            Receipt temp = listOfPurchases.get(i).getReceipt();
            if (purchase.getReceipt() == temp) {
                return true;
            }
        }
        return false;
    }

    public void addUserToCompany(Company company, Employee employee) throws IllegalArgumentException {
        company.addEmployee(employee);
    }

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

    public Company getCompany(Company company) {
        for (int i = 0; i < companies.size(); i++) {
            Company temp = companies.get(i);
            if (temp.getName().equals(company.getName())) {
                return temp;
            }
        }
        return null;
    }

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

    public List<Company> getCompanies() {
        return companies;
    }

    public String getName() {
        return name;
    }
}
