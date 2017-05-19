package corp.skaj.foretagskvitton.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Random;

import static org.junit.Assert.*;


public class EmployeeTest {
    private Employee employee;

    @Before
    public void setup() {
        employee = new Employee("testPerson");
    }

    private Receipt setupARecipt() {
        Product product = new Product("Apelsin Juice", Category.BENSIN, 19.50, 25);
        Calendar cal = Calendar.getInstance();
        Random rand = new Random();
        cal.set(rand.nextInt(18) + 2000, rand.nextInt(12) + 1, rand.nextInt(28) + 1);
        double total = 19.50;

        return new Receipt(product, cal, total, null);

    }

    @Test
    public void testAmountOfPurchases() {
        int n = 4;
        for (int i = 0; i < n; i++) {
            employee.addPurchase(new Purchase(setupARecipt(), Purchase.PurchaseType.PRIVATE));
        }

        assertEquals(n, employee.getPurchases().size());
    }

    @Test
    public void testRemoveOfPurchase() {
        Purchase purchase = new Purchase(setupARecipt(), Purchase.PurchaseType.PRIVATE);

        employee.addPurchase(purchase);

        try {
            employee.removePurchase(purchase);
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
        }

        assertEquals(0, employee.getPurchases().size());
    }
    @Test
    public void testAddComment (){
        Comment comment = new Comment("This should work");
        String expected = "This should work";
        Employee employee = new Employee("Torsten");
        employee.addComment(comment);
        assertEquals(expected,employee.getComments().get(0).getComment());
    }

    @Test
    public void testAddName (){

    }

}
