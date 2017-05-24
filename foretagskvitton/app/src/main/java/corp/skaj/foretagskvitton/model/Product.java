package corp.skaj.foretagskvitton.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds information about the purchased item
 */

public class Product {
    public static final String ALL_PRODUCTS = "ALL_PRODUCTS";
    private List<Comment> comments;
    private Category category;
    private String name;
    private double price;
    private double tax;

    public Product(String name, Category category, double price, double tax) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.tax = tax;
        comments = new ArrayList<>();
    }

    public void addComment(Comment c) {
        comments.add(c);
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

    public double getPrice() {
        return price;
    }

    public double getTax() {
        return tax;
    }

    public List<Comment> getComments() {
        return comments;
    }
}
