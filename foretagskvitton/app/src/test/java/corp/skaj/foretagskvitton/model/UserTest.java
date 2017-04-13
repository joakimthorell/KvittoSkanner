package corp.skaj.foretagskvitton.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import corp.skaj.foretagskvitton.exceptions.IllegalInputException;
import corp.skaj.foretagskvitton.exceptions.NoSuchCardException;
import corp.skaj.foretagskvitton.exceptions.NoSuchCompanyException;

import static org.junit.Assert.*;

/**
 *
 */
public class UserTest {
    User user;
    String companyName;

    @Before
    public void setup() {
        user = new User("User");
        companyName = "Company";
    }

    @Test
    public void testAddNewCompany() {
        try {
            user.addNewCompany(companyName);
        } catch (IllegalInputException iie) {
            assertTrue(false);
        }
        assertTrue(true);
    }

    @Test
    public void testRemoveCompany() {
        addNewCompany();
        try {
            user.removeCompany(companyName);
        } catch (NoSuchCompanyException nsce) {
            assertTrue(false);
        }
        assertTrue(true);
    }

    @Test
    public void testGetCompanyWithString() {
        addNewCompany();
        assertEquals(companyName, user.getCompany(companyName).getName());
    }

    @Test
    public void testGetCompanyWithCard() {
        addNewCompany();
        Card card = setupCard(1234);
        assertEquals(companyName, user.getCompany(card).getName());
    }

    @Test
    public void testGetCompanyWithPurchase() {
        Purchase purchase = setupPurchase();
        assertEquals(companyName, user.getCompany(purchase).getName());
    }

    private Card setupCard(int cardNumber) {
        addNewCompany();
        Card card = new Card(cardNumber);
        try {
            user.getCompany(companyName).addNewCard(card);
        } catch (IllegalInputException iie) {
        }
        return card;
    }

    private void addNewCompany() {
        try {
            user.addNewCompany(companyName);
        } catch (IllegalInputException iie) {
            iie.getCause();
        }
    }

    private Purchase setupPurchase() {
        Product product = new Product("Apelsin", 10, 12.5);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 4, 20);
        Receipt receipt = new Receipt(product, calendar, 10);
        Purchase purchase = new PrivatePurchase(receipt);
        addNewCompany();
        user.getCompany(companyName).getEmployee(user.getName()).addPurchase(purchase);
        return purchase;
    }
}