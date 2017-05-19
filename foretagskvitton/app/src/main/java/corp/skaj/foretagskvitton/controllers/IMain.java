package corp.skaj.foretagskvitton.controllers;

public interface IMain {

    void goToActivity(Class<?> c, String key, String data);

    void buildArchiveFragment();

    void buildCompanyFragment();

    void buildSupplierFragment();

    void buildCompanyInfoFragment();
}
