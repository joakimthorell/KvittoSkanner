package corp.skaj.foretagskvitton.model;

import java.util.ArrayList;

public class DataModel {
    private User user;

    public DataModel() {
        user = new User();
        // TODO Need to load old data, companies etc
    }


    /**
     * This method creates a new company to listOfCompanies
     * @param name company name
     * @return <h1>true</h1> if create was successful, <h1>false</h1> otherwise
     */
    public boolean createNewCompany(String name) {
        if (containsCompanyName(name)) {
            return false;
        }

        Company company = new Company(name);
        company.addNewEmployee(user.getName());

        return true;
    }

    private void

    private boolean containsCompanyName(String name) {
        for (Company c : user.getListOfCompanies()) {
            if (c.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return listOfCompanies
     */
    public ArrayList<Company> getListOfCompanies() {
        return listOfCompanies;
    }

    /**
     * This method returns the company with given name
     * @param name name of wanted company
     * @return company with given name, if not existing returns null.
     */
    public Company getCompany(String name) {
        for (Company c : listOfCompanies) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }
}
