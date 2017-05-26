package corp.skaj.foretagskvitton.controllers;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public interface IActivity {

    void buildArchiveListFragment();

    void buildCompanyFragment();

    void buildSupplierFragment();

    void startNewActivityForResult(Class<?> nextActivity,
                                   int requestCode,
                                   String action,
                                   String data,
                                   String key);

    void reloadUI(Fragment fragment);

}