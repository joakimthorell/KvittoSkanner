package corp.skaj.foretagskvitton.controllers;

public interface IMain {

    void goToCompany(String s);

    void goToPurchase(String s);

    void goToSupplier(String s);

    void buildArchiveFragment();

    void buildCompanyFragment();

    void buildSupplierFragment();
}
