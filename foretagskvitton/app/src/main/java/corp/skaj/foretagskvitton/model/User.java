package corp.skaj.foretagskvitton.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private List<Company> listOfCompanies;

    public User() {
        listOfCompanies = new ArrayList<>();

    }

    public void addNewCompany(String companyName) {
        if (!containsCompany(companyName)) {
            Company company = new Company(companyName);
        }
    }

    private boolean containsCompany(String companyName) {
        for (int i = 0; i < listOfCompanies.size(); i++) {
            Company company = listOfCompanies.get(i);
            if (companyName == company.getName()) {
                return true;
            }
        }
        return false;
    }

    public void removeCompany(Company company) {
        
    }

    public List<Company> getListOfCompanies() {

    }

    public Company getCompany(String companyName) {
        return null;
    }

    public Company getCompany(Receipt receipt) {
        return null;
    }

    public Company getCompany(Card card) {
        return null;
    }

}
