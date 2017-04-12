package corp.skaj.foretagskvitton.model;

import java.util.List;

/**
 * Created by lasanjin on 4/5/17.
 */

public class Product {
    private String name;
    private Category category;
    private List<Comment> lstOfComments;
    private Double price;
    private Double tax;


    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public List<Comment> getLstOfComments() {
        return lstOfComments;
    }

    public Double getPrice() {
        return price;
    }

    public Double getTax() {
        return tax;
    }
}
