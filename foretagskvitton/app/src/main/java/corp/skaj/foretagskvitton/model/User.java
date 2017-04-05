package corp.skaj.foretagskvitton.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by annekeller on 2017-04-05.
 */

public class User  {
   private Map<Company, Employee> listOfCompanies;

    public User () {
        listOfCompanies = new HashMap<>();
    }

    public void addCompany (Company company) {
    listOfCompanies.put(company, company.getEmployee("User"));
    }

    public void removeUser () {

    }

    public List<Company> getListOfCompanies () {
        List<Company> list = new ArrayList<>();
        list.addAll(listOfCompanies.keySet());
        return list;
    }

}
