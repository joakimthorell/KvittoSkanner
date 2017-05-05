package corp.skaj.foretagskvitton.model;


public class Supplier {
    private String name;

    public Supplier(String name) {
        this.name = name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getName() {
        return name;
    }
}
