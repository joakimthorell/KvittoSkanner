package corp.skaj.foretagskvitton.model;

import org.junit.Before;

import java.util.Calendar;

import corp.skaj.foretagskvitton.exceptions.IllegalInputException;
import corp.skaj.foretagskvitton.exceptions.NoSuchCompanyException;

import static org.junit.Assert.*;

/**
 *
 */
public class UserTest {
    User user;

    @Before
    public void setup() {
        user = new User("User");
        try {
            user.addNewCompany("Company1");
            user.addNewCompany("Company2");
        } catch (IllegalInputException iie) {
            iie.printStackTrace();
        }
        setupReceipt();
    }

    private void setupReceipt() {
        Product product = new Product("Apelsin", 10, 12.5);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 4, 20);
        Receipt receipt = new Receipt(product, calendar, 10);
        Purchase purchase = new PrivatePurchase(receipt);

    }
}