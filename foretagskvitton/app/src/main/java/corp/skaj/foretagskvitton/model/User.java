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

    public boolean addCompany(Company company) {
        return companies.add(company);
    }

    public boolean removeCompany(Company company) {
        return companies.size() > 1 && companies.remove(company);
    }

    private boolean containsReceipt(List<Purchase> purchases, Purchase purchase) {
        if (purchases == null) {
            return false;
        }
        for (Purchase p : purchases) {
            if (purchase.getReceipt() == p.getReceipt()) {
                return true;
            }
        }
        return false;
    }

    public void setName(String name) {
        this.name = name;
        for (Company company : companies) {
            Employee employee = company.getEmployee(name);
            if (employee != null) {
                employee.setName(name);
            }
        }
    }

    public Company getCompany(Company company) {
        for (Company c : companies) {
            if (c.getName().equals(company.getName())) {
                return c;
            }
        }
        return null;
    }

    public Company getCompany(Card card) {
        for (Company company : companies) {
            Card c = company.getCard(card.getCard());
            if (c.getCard() == card.getCard()) {
                return company;
            }
        }
        return null;
    }

    public Company getCompany(Purchase purchase) {
        for (Company company : companies) {
            List<Purchase> purchases = getPurchases(company, purchase);
            if (containsReceipt(purchases, purchase)) {
                return company;
            }
        }
        return null;
    }

    private List<Purchase> getPurchases(Company company, Purchase purchase) {
        List<Purchase> receipts;
        try {
            receipts = company.getEmployee(purchase).getPurchases();
        } catch (Exception e) {
            System.out.println("getPurchase is null " + this.getClass().getName());
            return null;
        }
        return receipts;
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
