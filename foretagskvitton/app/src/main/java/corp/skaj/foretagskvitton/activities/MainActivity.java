package corp.skaj.foretagskvitton.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.roughike.bottombar.BottomBar;

import java.util.ArrayList;
import java.util.List;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.controllers.IMain;
import corp.skaj.foretagskvitton.controllers.MainController;
import corp.skaj.foretagskvitton.model.Card;
import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.Employee;
import corp.skaj.foretagskvitton.model.MultipleItem;
import corp.skaj.foretagskvitton.model.Supplier;
import corp.skaj.foretagskvitton.model.User;
import corp.skaj.foretagskvitton.view.ArchiveAdapter;
import corp.skaj.foretagskvitton.view.CompanyAdapter;
import corp.skaj.foretagskvitton.view.CompanyMutlipleItemAdapter;
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


        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        mController.initBottomBar(bottomBar);

        mFragmentManger = getSupportFragmentManager();

        buildArchiveFragment();
    }

    public void buildCompanyFragment() {
        List<Company> companies = getDataHandler().readData(User.class.getName(), User.class).getCompanies();
        CompanyAdapter ca = new CompanyAdapter(R.layout.company_list_view, companies);
        mController.setCompanyAdapterListener(ca, CompanyActivity.class, COMPANY_KEY);
        ListFragment fragment = ListFragment.create(ca);
        mFragmentManger.beginTransaction().replace(R.id.fragment_container, fragment).commit();
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

    @Override
    public void buildCompanyInfoFragment() {


        /* ------ DEMO ------- */

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Sanjin :)"));
        employees.add(new Employee("Jocke :)"));

        List<Card> cards = new ArrayList<>();
        cards.add(new Card(1234));
        cards.add(new Card(7890));

        List<MultipleItem> list = new ArrayList<>();
        MultipleItem m = new MultipleItem(MultipleItem.EMPLOYEE, "Sanjin");
        MultipleItem m1 = new MultipleItem(MultipleItem.EMPLOYEE, "Joakim");
        MultipleItem m2 = new MultipleItem(MultipleItem.CARD, String.valueOf(1234));
        MultipleItem m3 = new MultipleItem(MultipleItem.CARD, String.valueOf(5630));
        MultipleItem m4 = new MultipleItem(MultipleItem.COMMENT, "Hej jag är ganska duktig");
        MultipleItem m5 = new MultipleItem(MultipleItem.COMMENT, "Detta kanske fungerar");

        list.add(m);
        list.add(m1);
        list.add(m2);
        list.add(m3);
        list.add(m4);
        list.add(m5);

        CompanyMutlipleItemAdapter cia = new CompanyMutlipleItemAdapter(list);

        ListFragment lf = ListFragment.create(cia);
        mFragmentManger.beginTransaction().replace(R.id.fragment_container, lf).commit();
    }





}
