package corp.skaj.foretagskvitton.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.roughike.bottombar.BottomBar;

import java.util.List;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.controllers.IActivity;
import corp.skaj.foretagskvitton.controllers.MainController;
import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.Purchase;
import corp.skaj.foretagskvitton.model.PurchaseList;
import corp.skaj.foretagskvitton.model.Supplier;
import corp.skaj.foretagskvitton.model.User;
import corp.skaj.foretagskvitton.view.ArchiveListFragment;
import corp.skaj.foretagskvitton.view.CompanyListFragment;
import corp.skaj.foretagskvitton.view.SupplierListFragment;

public class MainActivity extends AbstractActivity
        implements IActivity {
    public static final String COMPANY_KEY = "COMPANY_KEY";
    public static final String ARCHIVE_KEY = "ARCHIVE_KEY";
    public static final String SUPPLIER_KEY = "SUPPLIER_KEY";

    private MainController mController;
    private FragmentManager mFragmentManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getDataHandler().clearData(); // Clear all data

        // Create a default user if there is no user
        if (getDataHandler().initDefaultUser()) {
            Intent intent = new Intent(this, IntroActivity.class);
            startActivity(intent);
        }

        // Initiate main controller and bottom bar
        mController = new MainController(this, this);
        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        mController.initBottomBar(bottomBar);

        mFragmentManger = getSupportFragmentManager();

        buildArchiveListFragment();
    }

    @Override
    public void buildCompanyFragment() {
        CompanyListFragment fragment = ListFragmentFactory.createCompanyList(this, getCompanies());
        mController.setCompanyAdapterListener(fragment.getAdapter(), CompanyActivity.class, COMPANY_KEY);
        replaceFragment(fragment);
    }

    @Override
    public void buildArchiveListFragment() {
        ArchiveListFragment fragment = ListFragmentFactory.createArchiveList(AddReceiptActivity.class, this, getPurchases(), getDataHandler());
        mController.setArchiveAdapterListener(fragment.getAdapter(), ReceiptActivity.class, ARCHIVE_KEY);
        replaceFragment(fragment);
    }

    @Override
    public void buildSupplierFragment() {
        SupplierListFragment fragment = ListFragmentFactory.createSupplierList(this, getSuppliers());
        mController.setSupplierAdapterListener(fragment.getAdapter(), SUPPLIER_KEY);
        replaceFragment(fragment);
    }

    @Override
    public void onBackPressed() {
        finish();
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
        return getDataHandler().getPurchases();
    }

    private void replaceFragment(Fragment fragment) {
        String backStateName = fragment.getClass().getName();
        mFragmentManger = getSupportFragmentManager();
        boolean fragmentPopped = mFragmentManger.popBackStackImmediate (backStateName, 0);

        if (!fragmentPopped) { //fragment not in back stack, set it it.
            FragmentTransaction ft = mFragmentManger.beginTransaction();
            ft.replace(R.id.main_fragment_container, fragment);
            ft.addToBackStack(backStateName);
            ft.commit();
        }
    }

    @Override
    public void startNewActivityForResult(Class<?> activity,
                                          int requestCode,
                                          String action,
                                          String data,
                                          String key) {
        Intent intent = new Intent(this, activity);
        intent.setAction(action);
        intent.putExtra(key, data);
        startActivityForResult(intent, requestCode);
    }

}