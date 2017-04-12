package corp.skaj.foretagskvitton.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lasanjin on 4/5/17.
 */

public class Product {
    private String name;
    private Category category;
    private List<Comment> listOfComments;
    private double price;
    private double tax;

    public Product(String name, Category category, List<Comment> listOfComments, Double price, Double tax) {
        this.name = name;
        this.category = category;
        this.listOfComments = listOfComments;
        this.price = price;
        this.tax = tax;
    }

    public Product(String name, double price, double tax) {
        this.name = name;
        this.price = price;
        this.tax = tax;
        listOfComments = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setLstOfComments(List<Comment> lstOfComments) {
        this.listOfComments = lstOfComments;
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

    public List<Comment> getLstOfComments() {
        return listOfComments;
    }

    public double getPrice() {
        return price;
    }

    public double getTax() {
        return tax;
    }
}
