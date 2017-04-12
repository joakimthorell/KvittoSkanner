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

    public List<Product> getListOfProducts() {
        return listOfProducts;
    }

    public Date getDate() {
        return date;
    }

    public Double getTotal() {
        return total;
    }
}

