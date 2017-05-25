package corp.skaj.foretagskvitton.controllers;

public interface IView {

    void nextActivity(Class<?> c, String key, String data);

    void buildArchiveListFragment();

    void buildCompanyFragment();

    void buildSupplierFragment();
}