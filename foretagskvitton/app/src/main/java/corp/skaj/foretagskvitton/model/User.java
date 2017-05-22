package corp.skaj.foretagskvitton.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private List<Supplier> suppliers;
    private List<Company> companies;
    private String name;

    public User(String name) {
        this.name = name;
        companies = new ArrayList<>();
        suppliers = new ArrayList<>();
    }

    public boolean addCompany(Company company) {
        return companies.add(company);
    }

    public boolean removeCompany(Company company) {
        return companies.size() > 1 && companies.remove(company);
    }

    public boolean addSupplier(Supplier supplier) {
        return suppliers.add(supplier);
    }

    public boolean removeSupplier(Supplier supplier) {
        return suppliers.remove(supplier);
    }

    public void setName(String name) {
        for (Company company : companies) {
            Employee employee = company.getEmployee(this.name);
            employee.setName(name);
        }
        this.name = name;
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
            for (Employee e : company.getEmployees()) {
                for (Purchase p : e.getPurchases()) {
                    if (p.getId().equals(purchase.getId())) {
                        return company;
                    }
                }
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

    public Supplier getSupplier(String supplierName) {
        for (Supplier supplier : suppliers) {
            if (supplier.getName().equals(supplierName)) {
                return supplier;
            }
        }
        return null;
    }

    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    public List<Company> getCompanies() {
        System.out.println(companies.get(0).getName() + "AHHAHAHA AH AhalOOLLLLLL ");
        return companies;
    }

    public String getName() {
        return name;
    }
}
