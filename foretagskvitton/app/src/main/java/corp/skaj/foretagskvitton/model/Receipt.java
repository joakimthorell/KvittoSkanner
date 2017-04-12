package corp.skaj.foretagskvitton.model;

import java.util.Date;
import java.util.List;

/**
 * Created by lasanjin on 4/5/17.
 */

public class Receipt {
    private List<Product> listOfProducts;
    private Date date;
    private Double total;

    public Receipt(List<Product> listOfProducts, Date date, Double total) {
        this.listOfProducts = listOfProducts;
        this.date = date;
        this.total = total;
    }

    public List<Product> getListOfProducts() {
        return listOfProducts;
    }

    public Date getDate() {
        return date;
    }

    public Double getTotal() {
        return total;
    }

    public void setListOfProducts(List<Product> listOfProducts) {
        this.listOfProducts = listOfProducts;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}

