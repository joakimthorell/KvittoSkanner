package corp.skaj.foretagskvitton.model;

/**
 * Created by mattsson on 2017-04-05.
 */

public abstract class Purchase {
    private Receipt receipt;
    private Supplier supplier;
    private Employee employee;

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
