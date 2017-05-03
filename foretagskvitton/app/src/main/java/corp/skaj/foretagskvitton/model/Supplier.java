package corp.skaj.foretagskvitton.model;

import java.io.Serializable;

/**
 *
 */
public class Supplier implements Serializable {
    private String name;

    public Supplier(String name) {
        this.name = name;
    }

    /**
     *
     * @param newName
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }
}
