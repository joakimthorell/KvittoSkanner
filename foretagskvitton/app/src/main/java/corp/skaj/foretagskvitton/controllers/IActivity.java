package corp.skaj.foretagskvitton.controllers;

public interface IActivity {

    void buildArchiveListFragment();

    void buildCompanyFragment();

    void buildSupplierFragment();

    void startNewActivityForResult(Class<?> nextActivity,
                                   int requestCode,
                                   String action,
                                   String data,
                                   String key);
}