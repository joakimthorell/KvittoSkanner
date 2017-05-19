package corp.skaj.foretagskvitton.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import java.util.List;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.controllers.IMain;
import corp.skaj.foretagskvitton.controllers.MainController;
import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.Supplier;
import corp.skaj.foretagskvitton.model.User;
import corp.skaj.foretagskvitton.view.ArchiveAdapter;
import corp.skaj.foretagskvitton.view.ListFragment;
import corp.skaj.foretagskvitton.view.SupplierAdapter;

public class MainActivity extends AbstractActivity implements IMain {
    public static final String COMPANY_KEY = "key_for_company_ofc";
    public static final String ARCHIVE_KEY = "key_for_archive_ofc";
    public static final String SUPPLIER_KEY = "key_for_supplier_ofc";
    private MainController mController;
    private FragmentManager mFragmentManger;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // If there is no user, create a default user
        getDataHandler().initDefaultUser();

        mController = new MainController(this);
        mController.initBottomBar();

        mFragmentManger = getSupportFragmentManager();

        buildArchiveFragment();
    }

    public void buildCompanyFragment() {
        List<Company> companies = getDataHandler().readData(User.class.getName(), User.class).getCompanies();
        //ListFragment lf = ListFragment.create(new CompanyAdapter(companies, mAdapterController));
        FragmentManager fm = getSupportFragmentManager();
    }

    public void buildArchiveFragment() {
        ArchiveAdapter aa = new ArchiveAdapter(R.layout.archive_list_item, getDataHandler().getPurchases(), getDataHandler());
        mController.setArchiveAdapterListener(aa, ArchiveReceiptActivity.class, ARCHIVE_KEY);
        ListFragment lf = ListFragment.create(aa);
        mFragmentManger.beginTransaction().replace(R.id.fragment_container, lf).commit();
    }

    public void buildSupplierFragment() {
        List<Supplier> suppliers = getDataHandler().readData(User.class.getName(), User.class).getSuppliers();
        SupplierAdapter sa = new SupplierAdapter(R.layout.supplier_list_item, suppliers);
        mController.setSupplierAdapterListener(sa, UserActivity.class, SUPPLIER_KEY);
        ListFragment lf = ListFragment.create(sa);
        mFragmentManger.beginTransaction().replace(R.id.fragment_container, lf).commit();
    }

    @Override
    public void goToActivity(Class<?> c, String key, String data) {
        Intent intent = new Intent(this, c);
        intent.putExtra(key, data);
        startActivity(intent);
    }
}
