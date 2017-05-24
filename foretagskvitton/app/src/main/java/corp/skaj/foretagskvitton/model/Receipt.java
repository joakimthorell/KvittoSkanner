package corp.skaj.foretagskvitton.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * This class holds products, date and the total amount of a receipt which has been collected
 */

public class Receipt {
    private List<Product> products;
    private Calendar date;
    private final String imageAddress; // This is URI as String
    private double total;

    public Receipt(List<Product> products, Calendar date, double total, final String imageAdress) {
        this.products = products;
        this.date = date;
        this.total = total;
        this.imageAddress = imageAdress;
    }

    public Receipt(Product product, Calendar date, double total, final String imageAdress) {
        products = new ArrayList<>();
        products.add(product);
        this.date = date;
        this.total = total;
        this.imageAddress = imageAdress;
    }

    public boolean removeProduct(Product product) {
        return products.size() > 1 && products.remove(product);
    }

    public boolean addProduct(Product product) {
        return products.add(product);
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Calendar getDate() {
        return date;
    }

    public double getTotal() {
        return total;
    }

    public void getProducts(List<Product> products) {
        this.products = products;
    }

    /**
     * This is Uri as String
     */
    public String getPictureAdress() {
        return imageAddress;
    }
}

