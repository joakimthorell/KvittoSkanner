package corp.skaj.foretagskvitton.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class holds information about the purchased item.
 */
public class Product {
    public static final String ALL_PRODUCTS = "all_products";// Registers a single product for a Receipt.
    private List<Comment> comments;
    private Category category;
    private String name;
    private double price;
    private double vat;

    public Product(String name, Category category, double price, double vat) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.vat = vat;
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

    public void setTax(double vat) {
        this.vat = vat;
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

    public double getVat() {
        return vat;
    }

    public List<Comment> getComments() {
        return comments;
    }
}