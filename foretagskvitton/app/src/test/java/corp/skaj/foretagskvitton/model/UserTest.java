package corp.skaj.foretagskvitton.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import corp.skaj.foretagskvitton.exceptions.IllegalInputException;
import corp.skaj.foretagskvitton.exceptions.NoSuchCompanyException;

import static org.junit.Assert.*;

/**
 *
 */
public class UserTest {
    User user;
    Receipt receipt;

    @Before
    public void setup() {
        user = new User("User");
        /*
        try {
            user.addNewCompany("Company1");
            user.addNewCompany("Company2");
        } catch (IllegalInputException iie) {
            iie.printStackTrace();
        }
        setupReceipt();
        */
    }

    @Test
    public void testAddNewCompany() {
        try {
            user.addNewCompany("Company1");
        } catch (IllegalInputException iie) {
            assertTrue(false);
        }
        assertTrue(true);
    }

    private void setupReceipt() {
        Product product = new Product("Apelsin", 10, 12.5);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 4, 20);
        receipt = new Receipt(product, calendar, 10);
        Purchase purchase = new PrivatePurchase(receipt);
        //purchase.setReceipt(receipt);
        user.getCompany("Company1").getEmployee("User").addPurchase(purchase);
    }

    public void testGetCompanyReceipt() {
        assertEquals("Company1", user.getCompany(receipt));
    }
}