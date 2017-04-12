package corp.skaj.foretagskvitton.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Random;

import corp.skaj.foretagskvitton.exceptions.NoSuchCommentException;
import corp.skaj.foretagskvitton.exceptions.NoSuchPurchaseException;

import static org.junit.Assert.*;

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

        return new Receipt(product, cal, total);
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
        } catch (NoSuchPurchaseException e) {
            e.printStackTrace();
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
        } catch (NoSuchPurchaseException e) {
            assertTrue(true);
        }
    }

    @Test(expected = NoSuchCommentException.class)
    public void testRemoveCommentFromEmployee() {
        Comment comment = new Comment("Hej");
        employee.addComment(comment);

        Comment comment1 = new Comment("Då");
        employee.removeComment(comment1);
    }
}