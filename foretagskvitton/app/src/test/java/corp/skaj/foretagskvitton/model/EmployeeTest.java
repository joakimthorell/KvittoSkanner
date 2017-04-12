package corp.skaj.foretagskvitton.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Random;

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
    public void test() {

    }
}