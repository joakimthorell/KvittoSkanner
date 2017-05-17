package corp.skaj.foretagskvitton.model;

import android.net.Uri;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Receipt {
    private List<Product> products;
    private Calendar date;
    private final String pictureAdress; // This is URI as String
    private double total;

    public Receipt(List<Product> products, Calendar date, double total, final String pictureAdress) {
        this.products = products;
        this.date = date;
        this.total = total;
        this.pictureAdress = pictureAdress;
    }

    public Receipt(Product product, Calendar date, double total, final String pictureAdress) {
        products = new ArrayList<>();
        products.add(product);
        this.date = date;
        this.total = total;
        this.pictureAdress = pictureAdress;
    }

    public void removeProduct(Product product) throws IllegalArgumentException {
        for (Product p : products) {
            if (product == p) {
                products.remove(p);
                return;
            }
        }
        throw new IllegalArgumentException("That product does not exist");
    }

    public void addProduct(Product product) {
        products.add(product);
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
        return pictureAdress;
    }
}

