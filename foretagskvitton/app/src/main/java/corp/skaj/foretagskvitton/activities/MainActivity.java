package corp.skaj.foretagskvitton.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentController;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;

import com.afollestad.materialcab.MaterialCab;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.roughike.bottombar.BottomBar;

import java.util.List;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.controllers.IMain;
import corp.skaj.foretagskvitton.controllers.ListFragmentController;
import corp.skaj.foretagskvitton.controllers.MainController;
import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.Supplier;
import corp.skaj.foretagskvitton.model.User;
import corp.skaj.foretagskvitton.view.ArchiveAdapter;
import corp.skaj.foretagskvitton.view.CompanyAdapter;
import corp.skaj.foretagskvitton.view.ListFragment;
import corp.skaj.foretagskvitton.view.SupplierAdapter;

public class MainActivity extends AbstractActivity implements IMain, MaterialCab.Callback {
    public static final String COMPANY_KEY = "key_for_company_ofc";
    public static final String ARCHIVE_KEY = "key_for_archive_ofc";
    public static final String SUPPLIER_KEY = "key_for_supplier_ofc";
    private MainController mController;
    private FragmentManager mFragmentManger;
    private ListFragmentController mListFragmentController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // If there is no user, create a default user
        getDataHandler().initDefaultUser();
        mController = new MainController(this);
        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        mController.initBottomBar(bottomBar);

        mFragmentManger = getSupportFragmentManager();

        //mListFragmentController = new ListFragmentController(this);

        buildArchiveFragment();
    }

    @Override
    public boolean onCabCreated(MaterialCab cab, Menu menu) {
        return true;
    }

    @Override
    public boolean onCabItemClicked(MenuItem item) {
        return false;
    }

    @Override
    public boolean onCabFinished(MaterialCab cab) {
        return false;
    }

    public void buildCompanyFragment() {
        List<Company> companies = getDataHandler().readData(User.class.getName(), User.class).getCompanies();
        CompanyAdapter ca = new CompanyAdapter(R.layout.company_list_view, companies);
        mController.setCompanyAdapterListener(ca, CompanyActivity.class, COMPANY_KEY);
        mFragmentManger.beginTransaction().replace(R.id.fragment_container, buildFragmentList(ca)).commit();
    }

    public void buildArchiveFragment() {
        ArchiveAdapter aa = new ArchiveAdapter(R.layout.archive_list_item, getDataHandler().getPurchases(), getDataHandler());
        mController.setArchiveAdapterListener(aa, ArchiveReceiptActivity.class, ARCHIVE_KEY);
        mFragmentManger.beginTransaction().replace(R.id.fragment_container, buildFragmentList(aa)).commit();
    }

    public void buildSupplierFragment() {
        List<Supplier> suppliers = getDataHandler().readData(User.class.getName(), User.class).getSuppliers();
        SupplierAdapter sa = new SupplierAdapter(R.layout.supplier_list_item, suppliers);
        mController.setSupplierAdapterListener(sa, UserActivity.class, SUPPLIER_KEY);
        mFragmentManger.beginTransaction().replace(R.id.fragment_container, buildFragmentList(sa)).commit();
    }

    private ListFragment buildFragmentList(BaseQuickAdapter bqa) {
        return  ListFragment.create(bqa);
    }

    @Override
    public void goToActivity(Class<?> c, String key, String data) {
        Intent intent = new Intent(this, c);
        intent.putExtra(key, data);
        startActivity(intent);
    }


}