package corp.skaj.foretagskvitton.model;

import android.net.Uri;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 */
public class Receipt {
    private List<Product> products;
    private Calendar date;
    private double total;
    private final Uri pictureAdress;

    public Receipt(List<Product> products, Calendar date, double total, final Uri pictureAdress) {
        this.products = products;
        this.date = date;
        this.total = total;
        this.pictureAdress = pictureAdress;
    }

    public Receipt(Product product, Calendar date, double total, final Uri pictureAdress) {
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

    /**
     *
     * @return listOfProducts
     */
    public List<Product> getListOfProducts() {
        return products;
    }

    /**
     *
     * @return date
     */
    public Calendar getDate() {
        return date;
    }

    /**
     *
     * @return total
     */
    public double getTotal() {
        return total;
    }

    /**
     *
     * @param products
     */
    public void setListOfProducts(List<Product> products) {
        this.products = products;
    }

    /**
     *
     * @param date
     */
    public void setDate(Calendar date) {
        this.date = date;
    }

    /**
     *
     * @param total
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     *
     * @return amount of products in listOfProducts
     */
    public int getAmountOfProducts (){
        return products.size();
    }

    public Uri getPictureAdress() {
        return pictureAdress;
    }
}

