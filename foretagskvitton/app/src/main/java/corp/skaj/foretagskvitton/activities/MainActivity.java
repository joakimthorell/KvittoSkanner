package corp.skaj.foretagskvitton.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.roughike.bottombar.BottomBar;

import java.util.List;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.controllers.IView;
import corp.skaj.foretagskvitton.controllers.ListFragmentFactory;
import corp.skaj.foretagskvitton.controllers.MainController;
import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.PurchaseList;
import corp.skaj.foretagskvitton.model.Supplier;
import corp.skaj.foretagskvitton.model.User;
import corp.skaj.foretagskvitton.view.ArchiveListFragment;
import corp.skaj.foretagskvitton.view.CompanyListFragment;
import corp.skaj.foretagskvitton.view.SupplierListFragment;

public class MainActivity extends AbstractActivity
        implements IView {
    public static final String COMPANY_KEY = "COMPANY_KEY";
    public static final String ARCHIVE_KEY = "ARCHIVE_KEY";
    public static final String SUPPLIER_KEY = "SUPPLIER_KEY";
    private MainController mController;
    private FragmentManager mFragmentManger;
    private ListFragmentFactory mFragmentFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getDataHandler().clearData(); // Clear all data

        // Create a default user if there is no user

        /*if (getDataHandler().initDefaultUser()) {
            Intent intent = new Intent(this, IntroActivity.class);
            startActivity(intent);
        }*/

        // Initiate main controller and bottom bar
        mController = new MainController(this, this);
        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        mController.initBottomBar(bottomBar);

        mFragmentManger = getSupportFragmentManager();
        mFragmentFactory = new ListFragmentFactory(this,
                AddReceiptActivity.class,
                SupplierActivity.class,
                CompanyActivity.class);

        buildArchiveFragment();

        Intent intent = new Intent(this, IntroActivity.class);
        startActivity(intent);

    }

    @Override
    public void buildCompanyFragment() {
        CompanyListFragment fragment = mFragmentFactory.createCompanyList(getCompanies());
        mController.setCompanyAdapterListener(fragment.getAdapter(), CompanyActivity.class, COMPANY_KEY);
        replaceFragment(fragment);
    }

    @Override
    public void buildArchiveFragment() {
        ArchiveListFragment fragment = mFragmentFactory.createArchiveList(getPurchases(), getDataHandler());
        mController.setArchiveAdapterListener(fragment.getAdapter(), ArchiveActivity.class, ARCHIVE_KEY);
        replaceFragment(fragment);
    }

    @Override
    public void buildSupplierFragment() {
        SupplierListFragment fragment = mFragmentFactory.createSupplierList(getSuppliers());
        mController.setSupplierAdapterListener(fragment.getAdapter(), SupplierActivity.class, SUPPLIER_KEY);
        replaceFragment(fragment);
    }

    @Override
    public void nextActivity(Class<?> c, String key, String data) {
        Intent intent = new Intent(this, c);
        intent.putExtra(key, data);
        startActivity(intent);
    }

    private User getUser() {
        return getDataHandler().getUser();
    }

    private List<Company> getCompanies() {
        return getUser().getCompanies();
    }

    private List<Supplier> getSuppliers() {
        return getDataHandler().getUser().getSuppliers();
    }

    private PurchaseList getPurchases() {
        return getDataHandler().getPurchases(getUser());
    }

    private void replaceFragment(Fragment fragment) {
        String backStateName = fragment.getClass().getName();
        mFragmentManger = getSupportFragmentManager();
        boolean fragmentPopped = mFragmentManger.popBackStackImmediate (backStateName, 0);

        if (!fragmentPopped){ //fragment not in back stack, create it.
            FragmentTransaction ft = mFragmentManger.beginTransaction();
            ft.replace(R.id.main_fragment_container, fragment);
            ft.addToBackStack(backStateName);
            ft.commit();
        }
    }
}