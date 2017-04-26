package corp.skaj.foretagskvitton.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

/**
 *
 */
public class UserTest {
    User user;
    Company company;

    @Before
    public void setup() {
        user = new User("User");
        company = new Company("Company");
        try {
            company.addEmployee(new Employee(user.getName()));
        } catch (IllegalArgumentException iae) {
            iae.getCause();
        }
    }

    @Test
    public void testAddNewCompany() {
        try {
            user.addCompany(company);
        } catch (IllegalArgumentException iae) {
            assertTrue(false);
        }
        assertTrue(true);
    }

    @Test
    public void testRemoveCompany() {
        addNewCompany();
        try {
            user.removeCompany(company);
        } catch (IllegalArgumentException iae) {
            assertTrue(false);
        }
        assertTrue(true);
    }

    @Test
    public void testGetCompanyWithString() {
        addNewCompany();
        assertEquals(company.getName(), user.getCompany(company).getName());
    }

    @Test
    public void testGetCompanyWithCard() {
        addNewCompany();
        Card card = setupCard(1234);
        assertEquals(company.getName(), user.getCompany(card).getName());
    }

    @Test
    public void testGetCompanyWithPurchase() {
        Purchase purchase = setupPurchase();
        assertEquals(company.getName(), user.getCompany(purchase).getName());
    }

    private Card setupCard(int cardNumber) {
        addNewCompany();
        Card card = new Card(cardNumber);
        try {
            user.getCompany(company).addCard(card);
        } catch (IllegalArgumentException iae) {
        }
        return card;
    }

    private void addNewCompany() {
        try {
            user.addCompany(company);
        } catch (IllegalArgumentException iae) {
            iae.getCause();
        }
    }

    private Purchase setupPurchase() {
        Product product = new Product("Apelsin", 10, 12.5);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 4, 20);
        Receipt receipt = new Receipt(product, calendar, 10);
        Purchase purchase = new PrivatePurchase(receipt);
        addNewCompany();
        company.getEmployee(user.getName()).addPurchase(purchase);
        return purchase;
    }
}