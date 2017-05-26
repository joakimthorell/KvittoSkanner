package corp.skaj.foretagskvitton.controllers;

public interface IActivity {

    void nextActivity(Class<?> c, String key, String data);

    void buildArchiveListFragment();

    void buildCompanyFragment();

    void buildSupplierFragment();
}