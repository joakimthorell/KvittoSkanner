package corp.skaj.foretagskvitton.model;

import org.junit.Before;
import org.junit.Test;

import corp.skaj.foretagskvitton.exceptions.IllegalInputException;
import corp.skaj.foretagskvitton.exceptions.NoSuchCardException;
import corp.skaj.foretagskvitton.exceptions.NoSuchEmployeeException;
import corp.skaj.foretagskvitton.exceptions.NoSuchSupplierException;

import static org.junit.Assert.*;

/**
 * Created by annekeller on 2017-04-13.
 */
public class CompanyTest {
    Company company;
    Employee employee;
    Card card;
    Supplier supplier;

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
        } catch (IllegalInputException iie) {
            assertTrue(false);
        }
        assertTrue(true);
    }

    @Test
    public void testRemoveEmployeeWithEmployee () {
        addNewEmployee();
        try {
            company.removeEmployee(employee);
        } catch (NoSuchEmployeeException nsee) {
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
        } catch (NoSuchEmployeeException nsee) {
            assertTrue(false);
        }
        assertTrue(true);
    }

    @Test
    public void testAddCard () {
        try {
            company.addCard(new Card(1111));
        } catch (IllegalInputException iie) {
            assertTrue(false);
        }
        assertTrue(true);
    }
    @Test
    public void testRemoveCard () {
        addNewCard();
        try {
            company.removeCard(card);
        } catch (NoSuchCardException nsce) {
            assertTrue(false);
        }
        assertTrue(true);
    }

    @Test
    public void testAddASupplier () {
        try {
            company.addSupplier(new Supplier("testSupplier"));
        } catch (IllegalInputException iie) {
            assertTrue(false);
        }
        assertTrue(true);
    }

    @Test
    public void testRemoveSupplier () {
        addNewSupplier();
        try {
            company.removeSupplier(supplier);
        } catch (NoSuchSupplierException nsse) {
            assertTrue(false);
        }
        assertTrue(true);
    }

    public void addNewEmployee () {
        try {
            company.addEmployee(employee);
        } catch (IllegalInputException iie) {
            iie.getCause();
        }
    }

    public void addNewCard () {
        try {
            company.addCard(new Card(5678));
        } catch (IllegalInputException iie) {
            iie.getCause();
        }
    }
    public void addNewSupplier () {
        try {
            company.addSupplier(new Supplier("testSupplier"));
        } catch (IllegalInputException iie) {
            iie.getCause();
        }
    }

}