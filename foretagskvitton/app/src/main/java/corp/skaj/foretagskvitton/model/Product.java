package corp.skaj.foretagskvitton.model;

import java.util.ArrayList;
import java.util.List;


public class Product {
    private String name;
    private Category category;
    private List<Comment> comments;
    private double price;
    private double tax;

    public Product(String name, Category category, List<Comment> comments, Double price, Double tax) {
        this.name = name;
        this.category = category;
        this.comments = comments;
        this.price = price;
        this.tax = tax;
    }

    public Product(String name, double price, double tax) {
        this.name = name;
        this.price = price;
        this.tax = tax;
        comments = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public double getPrice() {
        return price;
    }

    public double getTax() {
        return tax;
    }

}
