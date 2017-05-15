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
import corp.skaj.foretagskvitton.model.IData;

public class ArchiveActivity extends AbstractActivity {
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

        IData dataHandler = (IData)getApplicationContext();
        User user = dataHandler.readData(User.class.getName(), User.class);

        mAdapter = new ReceiptAdapter(purchases,user);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        initBottomBar(ARCHIVE_ID, this);
        //prepareReceiptData();

            testAddFirstPurchases();
            //purchases.addAll(user.getCompanies().get(0).getEmployees().get(0).getPurchases());
            mAdapter.notifyDataSetChanged();
    }

    private void testAddFirstPurchases(){
        IData dataHandler = (IData)getApplicationContext();

        //Skapar en produkt
        Product product = new Product("Aladob", Category.MAT, 50.0, 12.0);

        // Skapar ett kvitto och lägger till produkten i kvittot
        Calendar cal = Calendar.getInstance();
        Receipt receipt = new Receipt(product, cal, 500.00, null);
        PrivatePurchase pur = new PrivatePurchase(receipt);
        purchases.add(pur);

        //Skapar en anställd och ger den företag och kvitto med produkt
        User user = (User) dataHandler.readData(User.class.getName(), User.class);
        System.out.println(user.getName() + "HAHAHAHHAHAHAHAHHAHAH");
        //Employee emp = new Employee("Gunde");
        //emp.addPurchase(pur);
        //user.getCompanies().get(0).addEmployee(emp);

        //first input write is the key for saving changes
        dataHandler.writeData(User.class.getName(), user);
        mAdapter.notifyDataSetChanged();
    }

//.get(i).getEmployees().get(i).getPurchases().get(i);
    private void collectAllPurchases(){
        IData dataHandler = (IData)getApplicationContext();
        User user = (User) dataHandler.readData(User.class.getName(), User.class);

            List<Company> company = user.getCompanies();
        for(int i = 0; i < company.size(); i++){
            List<Employee> emps = user.getCompanies().get(i).getEmployees();
        }
    }
}

