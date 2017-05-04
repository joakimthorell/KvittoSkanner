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
    private List<Product> listOfProducts;
    private Calendar date;
    private double total;
    private final Uri pictureAdress;

    public Receipt(List<Product> listOfProducts, Calendar date, double total, final Uri pictureAdress) {
        this.listOfProducts = listOfProducts;
        this.date = date;
        this.total = total;
        this.pictureAdress = pictureAdress;
    }

    public Receipt(Product product, Calendar date, double total, final Uri pictureAdress) {
        listOfProducts = new ArrayList<>();
        listOfProducts.add(product);
        this.date = date;
        this.total = total;
        this.pictureAdress = pictureAdress;
    }

    public void removeProduct(Product product) throws IllegalArgumentException {
        for (Product p : listOfProducts) {
            if (product == p) {
                listOfProducts.remove(p);
                return;
            }
        }
        throw new IllegalArgumentException("That product does not exist");
    }

    public void addProduct(Product product) {
        listOfProducts.add(product);
    }

    /**
     *
     * @return listOfProducts
     */
    public List<Product> getListOfProducts() {
        return listOfProducts;
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
     * @param listOfProducts
     */
    public void setListOfProducts(List<Product> listOfProducts) {
        this.listOfProducts = listOfProducts;
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
        return listOfProducts.size();
    }

    public Uri getPictureAdress() {
        return pictureAdress;
    }
}

