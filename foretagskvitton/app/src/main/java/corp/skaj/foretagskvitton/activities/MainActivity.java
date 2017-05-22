package corp.skaj.foretagskvitton.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.roughike.bottombar.BottomBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.controllers.AddReceiptController;
import corp.skaj.foretagskvitton.controllers.IView;
import corp.skaj.foretagskvitton.controllers.MainController;
import corp.skaj.foretagskvitton.model.*;
import corp.skaj.foretagskvitton.view.*;

public class MainActivity extends AbstractActivity
        implements IView,  ListFragment.Callback {
    public static final String COMPANY_KEY = "COMPANY_KEY";
    public static final String ARCHIVE_KEY = "ARCHIVE_KEY";
    public static final String SUPPLIER_KEY = "SUPPLIER_KEY";
    private MainController mController;
    private FragmentManager mFragmentManger;
    private AddReceiptController mFloatingController;
    private ListFragment mActiveFragment;
    private Map<MainController.State, ListFragment> mFragmentMap;
    private MainController.State mState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a default user if there is no user
        getDataHandler().initDefaultUser();

        // Initiate main controller and bottom bar
        mController = new MainController(this);
        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        mController.initBottomBar(bottomBar);

        mFragmentManger = getSupportFragmentManager();
        // TODO denna kanske är onödig, tror de finns någon inbyggd som går att använda istället, ska kolla upp detta.
        // Den sparar gamla värden, så går man tillbaka till ett tidigare fragment är datan kvar.
        mFragmentMap = new HashMap<>();

        //TODO Designa en start vy istället?
        buildArchiveFragment();
    }

    @Override
    public void buildCompanyFragment() {
        ListFragment fragment;
        if (!mFragmentMap.containsKey(MainController.State.COMPANY)) {
            CompanyAdapter ca = buildCompanyAdapter(); // Todo create xml for company
            mController.setCompanyAdapterListener(ca, null, COMPANY_KEY); // todo null here
            fragment = buildFragmentList(ca);
            mFragmentMap.put(MainController.State.COMPANY, fragment);
        } else {
            fragment = mFragmentMap.get(MainController.State.COMPANY);
        }
        updateFragmentState(fragment,  MainController.State.COMPANY);
        initFragment(fragment);
    }

    @Override
    public void buildArchiveFragment() {
        ListFragment fragment;
        if (!mFragmentMap.containsKey(MainController.State.ARCHIVE)) {
            ArchiveAdapter aa = buildArchiveAdapter();
            fragment = buildFragmentList(aa);
            mController.setArchiveAdapterListener(aa, ArchiveActivity.class, ARCHIVE_KEY);
            mFragmentMap.put(MainController.State.ARCHIVE, fragment);
        } else {
            fragment = mFragmentMap.get(MainController.State.ARCHIVE);
        }
        updateFragmentState(fragment,  MainController.State.ARCHIVE);
        initFragment(fragment);
    }

    @Override
    public void buildSupplierFragment() {
        ListFragment fragment;
        if (!mFragmentMap.containsKey(MainController.State.SUPPLIER)) {
            SupplierAdapter sa = buildSupplierAdapter(getSuppliers());
            fragment = buildFragmentList(sa);
            mController.setSupplierAdapterListener(sa, SupplierActivity.class, SUPPLIER_KEY);
            mFragmentMap.put(MainController.State.SUPPLIER, fragment);
        } else {
            fragment = mFragmentMap.get(MainController.State.SUPPLIER);
        }
        updateFragmentState(fragment,  MainController.State.SUPPLIER);
        initFragment(fragment);
    }

    @Override
    public void nextActivity(Class<?> c, String key, String data) {
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
        initFragment(lf);
    }

    @Override
    public void onListCreated() {
        if (mFloatingController == null) {
            mFloatingController = new AddReceiptController(this,
                    AddReceiptActivity.class, null, null);
        }
        mFloatingController.setButton(mActiveFragment.getButton())
                .setListener(mState);
    }

    private ArchiveAdapter buildArchiveAdapter() {
        return new ArchiveAdapter(R.layout.archive_list_item, getPurchases(), getDataHandler());
    }

    private CompanyAdapter buildCompanyAdapter() {
        return new CompanyAdapter(R.layout.archive_list_item, getCompanies());
    }

    private SupplierAdapter buildSupplierAdapter(List<Supplier> suppliers) {
        return new SupplierAdapter(R.layout.archive_list_item, suppliers);
    }

    private User getUser() {
        return getDataHandler().readData(User.class.getName(), User.class);
    }

    private List<Company> getCompanies() {
        return getUser().getCompanies();
    }

    private List<Supplier> getSuppliers() {
        return getDataHandler().readData(User.class.getName(), User.class).getSuppliers();
    }

    private PurchaseList getPurchases() {
        return getDataHandler().getPurchases(getUser());
    }

    private ListFragment buildFragmentList(BaseQuickAdapter bqa) {
        ListFragment fragment = ListFragment.create(bqa, this);
        return fragment;
    }

    private void updateFragmentState(ListFragment fragment, MainController.State state) {
        mActiveFragment = fragment;
        mState = state;
    }

    private void initFragment(ListFragment fragment) {
        mFragmentManger.beginTransaction().replace(R.id.main_fragment_container, fragment).commit();
    }
}