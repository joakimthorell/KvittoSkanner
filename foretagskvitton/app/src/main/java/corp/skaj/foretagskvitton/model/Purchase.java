package corp.skaj.foretagskvitton.model;

/**
 * 
 */
public abstract class Purchase {
    private Receipt receipt;
    private Supplier supplier;
    private Employee employee;


    protected Purchase(Receipt receipt, Supplier supplier, Employee employee) {
        this(receipt, employee);
        this.supplier = supplier;
    }

    protected Purchase(Receipt receipt, Employee employee) {
        this.receipt = receipt;
        this.employee = employee;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
