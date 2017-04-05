package corp.skaj.foretagskvitton.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by annekeller on 2017-04-05.
 */

public class Company {

    private String name;

    private List<Supplier> listOfSuppliers;
    private List<Card> listOfCards;
    private List<Comment> listOfComments;

    private HashMap<String, Employee> mapOfEmployees;

    public Company(String name) {
        this.name = name;

        listOfSuppliers = new ArrayList<>();
        listOfCards = new ArrayList<>();
        listOfComments = new ArrayList<>();

        mapOfEmployees = new HashMap<>();
    }

    public boolean createNewEmployee(String name) {
        mapOfEmployees.put(name, new Employee(name));
    }

    public boolean addNewEmployee(Employee employee) {
        mapOfEmployees.put(employee.getName(), employee);
    }
}
