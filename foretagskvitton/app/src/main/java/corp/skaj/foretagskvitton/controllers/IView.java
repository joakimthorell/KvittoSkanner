package corp.skaj.foretagskvitton.controllers;

public interface IView {

    void nextActivity(Class<?> c, String key, String data);

    void buildArchiveFragment();

    void buildCompanyFragment();

    void buildSupplierFragment();

    void buildCompanyInfoFragment();
}
