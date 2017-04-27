package corp.skaj.foretagskvitton.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Random;

import static org.junit.Assert.*;

/**
 *
 */
public class EmployeeTest {
    Employee employee;

    @Before
    public void setup() {
        employee = new Employee("testPerson");
    }

    private Receipt setupARecipt() {
        Product product = new Product("Apelsin Juice", 19.50, 25);
        Calendar cal = Calendar.getInstance();
        Random rand = new Random();
        cal.set(rand.nextInt(18) + 2000, rand.nextInt(12) + 1, rand.nextInt(28) + 1);
        double total = 19.50;
        //TODO
        return new Receipt(product, cal, total, null);

    }

    @Test
    public void testAmountOfPurchases() {
        int n = 4;
        for (int i = 0; i < n; i++) {
            employee.addPurchase(new PrivatePurchase(setupARecipt()));
        }

        assertEquals(n, employee.getAmountOfPurchases());
    }

    @Test
    public void testRemoveOfPurchase() {
        Purchase purchase = new PrivatePurchase(setupARecipt());

        employee.addPurchase(purchase);

        try {
            employee.removePurchase(purchase);
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
        }

        assertEquals(0, employee.getAmountOfPurchases());
    }

    @Test
    public void testRemoveAPurchaseNotExcisting() {
        Purchase pur1 = new PrivatePurchase(setupARecipt());
        Purchase pur2 = new PrivatePurchase(setupARecipt());

        employee.addPurchase(pur1);

        try {
            employee.removePurchase(pur2);
            assertTrue(false);
        } catch (IllegalArgumentException iae) {
            assertTrue(true);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveCommentFromEmployee() {
        Comment comment = new Comment("Hej");
        employee.addComment(comment);

        Comment comment1 = new Comment("Då");
        employee.removeComment(comment1);
    }
}