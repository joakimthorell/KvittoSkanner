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

    public void addCompany(Company company) {
        if (!containsCompany(company.getName())) {
            companies.add(company);
        } else {
            System.out.println("Illegal argument " + this.getClass().getName());
        }
    }

    public void removeCompany(Company company) {
        if (containsCompany(company.getName())) {
            companies.remove(company);
        } else {
            System.out.println("Illegal argument " + this.getClass().getName());
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
        if(listOfPurchases == null){
            return false;
        }
        for (int i = 0; i < listOfPurchases.size(); i++) {
            Receipt temp = listOfPurchases.get(i).getReceipt();
            if (purchase.getReceipt() == temp) {
                return true;
            }
        }
        return false;
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

    public Company getCompany(Card card) {
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

    public Company getCompany(String companyName) {
        for (Company c : companies) {
            if (c.getName().equals(companyName)) {
                return c;
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
