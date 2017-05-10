package corp.skaj.foretagskvitton.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import corp.skaj.foretagskvitton.R;

import corp.skaj.foretagskvitton.model.DataHolder;
import corp.skaj.foretagskvitton.controllers.ReceiptAdapter;
import corp.skaj.foretagskvitton.model.Category;
import corp.skaj.foretagskvitton.model.Comment;
import corp.skaj.foretagskvitton.model.PrivatePurchase;
import corp.skaj.foretagskvitton.model.Product;
import corp.skaj.foretagskvitton.model.Receipt;
import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.Employee;
import corp.skaj.foretagskvitton.model.Purchase;
import corp.skaj.foretagskvitton.model.User;

public class ArchiveActivity extends AbstractActivity {
    public static final String STATE_FOR_BOTTOM_MENU = "ArchiveActivity";
    private List<Purchase> purchases;
    private RecyclerView recyclerView;
    private ReceiptAdapter mAdapter;
    DataHolder dataHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        purchases = new ArrayList<>();
        dataHolder = (DataHolder) getApplicationContext();;
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new ReceiptAdapter(purchases, dataHolder.getUser());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        initBottomBar(STATE_FOR_BOTTOM_MENU, this);
        //prepareReceiptData();
        if (dataHolder.getUser().getCompanies().size() < 1) {
            testAddPurchases();
        } else {
            purchases.addAll(dataHolder.getUser().getCompanies().get(0).getEmployees().get(0).getPurchases());
            mAdapter.notifyDataSetChanged();
        }

    }
/*
    private void prepareReceiptData() {
        for (int i = 0; i < purchases.size(); i++) {
            List<Employee> employees = dataHolder.getUser();
            for (int j = 0; j < employees.size(); i++) {
                List<Purchase> purchases = employees.get(j).getPurchases();
                for (int k = 0; k < purchases.size(); k++) {
                    purchases.get(k).getReceipt().getDate();
                }
            }
        }

        mAdapter.notifyDataSetChanged();
    }
    */

    private void testAddPurchases() {
        Product product = new Product("Aladob", Category.MAT, 50.0, 12.0);
        List<Product> products = new ArrayList<>();
          products.add(product);
     /*   products.add(product);
        products.add(product);
        products.add(product);
        products.add(product);
        */
        Calendar cal = Calendar.getInstance();
        Receipt receipt = new Receipt(products, cal, 500.00, null, Category.MAT);

        PrivatePurchase pur = new PrivatePurchase(receipt);
        purchases.add(pur);
      /*purchases.add(pur);
        purchases.add(pur);
        purchases.add(pur);
        purchases.add(pur);
*/
        Company com = new Company("Kevins onda foretag AB");
   /*     Company com1 = new Company("Kevins onda foretag A");
        Company com2 = new Company("Kevins onda foretag ");
        Company com3 = new Company("Kevins onda foreta");
        Company com4 = new Company("Kevins onda foret");
        Company com5 = new Company("Kevins onda for");
        Company com6 = new Company("Kevins onda fo");
        Company com7 = new Company("Kevins onda f");
        */

       /* dataHolder.getUser().addCompany(com2);
        dataHolder.getUser().addCompany(com3);
        dataHolder.getUser().addCompany(com4);
        dataHolder.getUser().addCompany(com5);
        dataHolder.getUser().addCompany(com6);
        dataHolder.getUser().addCompany(com7);
*/
        Employee emp = new Employee("Kevin1");
       /* Employee emp1 = new Employee("Kevin2");
        Employee emp2 = new Employee("Kevin3");
        Employee emp3 = new Employee("Kevin4");
        Employee emp4 = new Employee("Kevin5");
        Employee emp5 = new Employee("Kevin6");
        Employee emp6 = new Employee("Kevin7");
        Employee emp7 = new Employee("Kevin8");
        */
       emp.addPurchase(pur);
        com.addEmployee(emp);
       /* dataHolder.getUser().getCompanies().get(1).addEmployee(emp1);
        dataHolder.getUser().getCompanies().get(2).addEmployee(emp2);
        dataHolder.getUser().getCompanies().get(3).addEmployee(emp3);
        dataHolder.getUser().getCompanies().get(4).addEmployee(emp4);
        dataHolder.getUser().getCompanies().get(5).addEmployee(emp5);
        dataHolder.getUser().getCompanies().get(6).addEmployee(emp6);
        dataHolder.getUser().getCompanies().get(7).addEmployee(emp7);
        */
        dataHolder.getUser().addCompany(com);
        mAdapter.notifyDataSetChanged();
    }
}

