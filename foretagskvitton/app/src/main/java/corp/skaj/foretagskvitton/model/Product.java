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

    public Product(String name, Category category, List<Comment> lstOfComments, Double price, Double tax) {
        this.name = name;
        this.category = category;
        this.lstOfComments = lstOfComments;
        this.price = price;
        this.tax = tax;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setLstOfComments(List<Comment> lstOfComments) {
        this.lstOfComments = lstOfComments;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

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
