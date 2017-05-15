package corp.skaj.foretagskvitton.model;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

public class ReceiptTest {

    Receipt receipt;

    @Before
    public void setup() {
        Product product = new Product("Appelsin Juice", 15.90, 25);
        Calendar c = Calendar.getInstance();
        c.set(2017, 04, 10);
        double totalPrice = 15.90;
        receipt = new Receipt(product, c, totalPrice, null);
    }

    @Test
    public void testPrice() {
        double price = receipt.getTotal();

        Assert.assertEquals(15.90, price);
    }

    @Test
    public void testProductName() {
        String productName = receipt.getProducts().get(0).getName();

        Assert.assertEquals("Appelsin Juice", productName);
    }

    @Test
    public void testTax() {
        double tax = receipt.getProducts().get(0).getTax();

        Assert.assertEquals(25.0, tax);
    }

    @Test
    public void testRemoveProduct() {
        Product product = new Product("Banan paj", 25.00, 12);
        receipt.addProduct(product);
        receipt.setTotal(receipt.getTotal() + product.getPrice());

        receipt.removeProduct(receipt.getProducts().get(0));

        Assert.assertEquals(1, receipt.getProducts().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveProductException() {
        Product product = new Product("Banan", 15, 12);

        receipt.removeProduct(product);
    }
}