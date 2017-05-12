package corp.skaj.foretagskvitton.activities;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import corp.skaj.foretagskvitton.R;

import corp.skaj.foretagskvitton.controllers.ReceiptAdapter;
import corp.skaj.foretagskvitton.model.Category;
import corp.skaj.foretagskvitton.model.PrivatePurchase;
import corp.skaj.foretagskvitton.model.Product;
import corp.skaj.foretagskvitton.model.Receipt;
import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.Employee;
import corp.skaj.foretagskvitton.model.Purchase;
import corp.skaj.foretagskvitton.model.User;

public class ArchiveActivity extends AbstractActivity {
    public static final Integer BOTTOM_BAR_ID = R.id.action_archive;
    private List<Purchase> purchases;
    private RecyclerView recyclerView;
    private ReceiptAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        purchases = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new ReceiptAdapter(purchases, (User) readData(User.class.getName(), User.class));
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        initBottomBar(BOTTOM_BAR_ID, this);
        //prepareReceiptData();
        User user = (User) readData(User.class.getName(), User.class);
        if (user.getCompanies().size() < 1) {
            testAddPurchases();
        } else {
            testAddFirstPurchases();
            purchases.addAll(user.getCompanies().get(0).getEmployees().get(0).getPurchases());
            mAdapter.notifyDataSetChanged();
        }

    }

    private void testAddFirstPurchases(){
        Product product = new Product("Aladob", Category.MAT, 50.0, 12.0);
        List<Product> products = new ArrayList<>();
        products.add(product);
        Calendar cal = Calendar.getInstance();
        Receipt receipt = new Receipt(products, cal, 500.00, null, Category.MAT);

        PrivatePurchase pur = new PrivatePurchase(receipt);
        purchases.add(pur);
        ((User)readData(User.class.getName(), User.class)).getCompanies().get(0).getEmployees().get(0).addPurchase(pur);
        mAdapter.notifyDataSetChanged();
    }

//.get(i).getEmployees().get(i).getPurchases().get(i);
    private void collectAllPurchases(){
            List<Company> company = ((User)readData(User.class.getName(), User.class)).getCompanies();
        for(int i = 0; i < company.size(); i++){
            List<Employee> emps = ((User)readData(User.class.getName(), User.class)).getCompanies().get(i).getEmployees();
        }
    }

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
        ((User)readData(User.class.getName(), User.class)).getCompanies().get(0).getEmployees().get(0).addPurchase(pur);
        mAdapter.notifyDataSetChanged();
    }
}

