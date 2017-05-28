package corp.skaj.foretagskvitton.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class PurchaseListTest {
    private PurchaseList purchases;
    private User user;

    @Before
    public void setup() {
        setupAUser();
    }

    public void setupAUser() {
        User user = new User("namn");
        Company c = new Company("företag");
        Employee e = new Employee(user.getName());
        c.addEmployee(e);
        user.addCompany(c);

        purchases = new PurchaseList(user);

        for (int i = 0; i < 10; i++) {
            Purchase p = createRandomPurchase();
            e.addPurchase(p);
            purchases.add(p);
        }

    }

    public Purchase createRandomPurchase() {
        Random rand = new Random();
        Product p = new Product("product namn", randomCat(rand), rand.nextInt(999) + 1, 25);
        Receipt r = new Receipt(p, Calendar.getInstance(), rand.nextInt(999) + 1, null);
        Purchase pu = new Purchase(r, Purchase.PurchaseType.PRIVATE);
        return pu;
    }

    public Category randomCat(Random rander) {
        List<String> categories = Category.getCategories();
        int randomNum = rander.nextInt(categories.size());
        Category category = Category.valueOf(categories.get(randomNum));
        return category;
    }

    @Test
    public void testSortPriceLowestTotalFirst() {
        purchases.sortByPrice();
        Collections.reverse(purchases);

        for (int i = 1; i < purchases.size(); i++) {
            if (purchases.get(i).getReceipt().getTotal() >= purchases.get(i - 1).getReceipt().getTotal()) {
                assertFalse(true);
            }
            assertTrue(true);
        }
    }

    @Test
    public void testSortPriceHighestTotalFirst() {
        purchases.sortByPrice();

        for (int i = 1; i < purchases.size(); i++) {
            if (purchases.get(i).getReceipt().getTotal() <= purchases.get(i - 1).getReceipt().getTotal()) {
                assertFalse(true);
            }
            assertTrue(true);
        }
    }
}
