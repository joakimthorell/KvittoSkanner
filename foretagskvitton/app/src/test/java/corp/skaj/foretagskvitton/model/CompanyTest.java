package corp.skaj.foretagskvitton.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class CompanyTest {
    private Company company;
    private Employee employee;
    private Card card;
    private Supplier supplier;

    @Before
    public void setup () {
        company = new Company("testCompany");
        employee = new Employee("testEmployee");
        card = new Card(5678);
        supplier = new Supplier("testSupplier");

    }

    @Test
    public void testAddEmployee() {
        try {
            company.addEmployee(employee);
        } catch (IllegalArgumentException iae) {
            assertTrue(false);
        }
        assertTrue(true);
    }

    @Test
    public void testRemoveEmployeeWithEmployee () {
        addNewEmployee();
        try {
            company.removeEmployee(employee);
        } catch (IllegalArgumentException iae) {
            assertTrue(false);
        }
        assertTrue(true);
    }

    @Test
    public void testRemoveEmployeeWithString () {
        addNewEmployee();
        try {
            company.removeEmployee("testEmployee"); {

            }
        } catch (IllegalArgumentException iae) {
            assertTrue(false);
        }
        assertTrue(true);
    }

    @Test
    public void testAddCard () {
        try {
            company.addCard(new Card(1111));
        } catch (IllegalArgumentException iae) {
            assertTrue(false);
        }
        assertTrue(true);
    }
    @Test
    public void testRemoveCard () {
        addNewCard();
        try {
            company.removeCard(card);
        } catch (IllegalArgumentException iae) {
            assertTrue(false);
        }
        assertTrue(true);
    }

    @Test
    public void testAddASupplier () {
        try {
            company.addSupplier(new Supplier("testSupplier"));
        } catch (IllegalArgumentException iae) {
            assertTrue(false);
        }
        assertTrue(true);
    }

    @Test
    public void testRemoveSupplier () {
        addNewSupplier();
        try {
            company.removeSupplier(supplier);
        } catch (IllegalArgumentException iae) {
            assertTrue(false);
        }
        assertTrue(true);
    }

    public void addNewEmployee () {
        try {
            company.addEmployee(employee);
        } catch (IllegalArgumentException iae) {
            iae.getCause();
        }
    }

    public void addNewCard () {
        try {
            company.addCard(new Card(5678));
        } catch (IllegalArgumentException iae) {
            iae.getCause();
        }
    }
    public void addNewSupplier () {
        try {
            company.addSupplier(new Supplier("testSupplier"));
        } catch (IllegalArgumentException iae) {
            iae.getCause();
        }
    }

}