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
import corp.skaj.foretagskvitton.view.IAdapterController;
import corp.skaj.foretagskvitton.view.ListFragment;
import corp.skaj.foretagskvitton.view.SupplierAdapter;

public class MainActivity extends AbstractActivity implements IMain {
    public static final String COMPANY_KEY = "key_for_company_ofc";
    public static final String ARCHIVE_KEY = "key_for_archive_ofc";
    private IAdapterController mAdapterController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getDataHandler().initDefaultUser();
        MainController mc = new MainController(this, this, getDataHandler());
        mc.initBottomBar();
        buildArchiveFragment();
    }

    public void buildCompanyFragment () {
        List<Company> companies = getDataHandler().readData(User.class.getName(), User.class).getCompanies();
        //ListFragment lf = ListFragment.create(new CompanyAdapter(companies, mAdapterController));
        FragmentManager fm = getSupportFragmentManager();
        //fm.beginTransaction().replace(R.id.fragment_container, lf).commit();
    }

    public void buildArchiveFragment () {
        ArchiveAdapter aa = new ArchiveAdapter(R.layout.archive_list_item, getDataHandler().getPurchases(), getDataHandler());
        ListFragment lf = ListFragment.create(aa);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.fragment_container, lf).commit();
    }

    public void buildSupplierFragment () {
        List<Supplier> suppliers = getDataHandler().readData(User.class.getName(), User.class).getSuppliers();
        SupplierAdapter sa = new SupplierAdapter(R.layout.supplier_list_item, suppliers);
        ListFragment lf = ListFragment.create(sa);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.fragment_container, lf).commit();
    }

    @Override
    public void goToCompany(String s) {
        Intent intent = new Intent(this, CompanyActivity.class);
        intent.putExtra(COMPANY_KEY, s);
        startActivity(intent);
    }

    @Override
    public void goToPurchase(String s) {
        Intent intent = new Intent(this, ArchiveReceiptActivity.class);
        intent.putExtra(ARCHIVE_KEY, s);
        startActivity(intent);
    }

    @Override
    public void goToSupplier(String s) {
    }
}
