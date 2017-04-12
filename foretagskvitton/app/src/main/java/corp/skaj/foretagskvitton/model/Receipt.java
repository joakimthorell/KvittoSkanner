package corp.skaj.foretagskvitton.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lasanjin on 4/5/17.
 */

public class Receipt {
    private List<Product> listOfProducts;
    private Date date;
    private double total;

    public Receipt(List<Product> listOfProducts, Date date, double total) {
        this.listOfProducts = listOfProducts;
        this.date = date;
        this.total = total;
    }

    public Receipt(Product product, Date date, double total) {
        listOfProducts = new ArrayList<>();
        listOfProducts.add(product);
        this.date = date;
        this.total = total;
    }

    public List<Product> getListOfProducts() {
        return listOfProducts;
    }

    public Date getDate() {
        return date;
    }

    public double getTotal() {
        return total;
    }

    public void setListOfProducts(List<Product> listOfProducts) {
        this.listOfProducts = listOfProducts;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}

