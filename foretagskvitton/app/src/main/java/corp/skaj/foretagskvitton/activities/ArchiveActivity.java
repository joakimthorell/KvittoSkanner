package corp.skaj.foretagskvitton.activities;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
import corp.skaj.foretagskvitton.model.User;
import corp.skaj.foretagskvitton.model.IData;
import corp.skaj.foretagskvitton.services.DataHandler;

public class ArchiveActivity extends AbstractActivity {
    private ReceiptAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);
        mAdapter = new ReceiptAdapter(getApplicationContext());
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        testAddFirstPurchases();
        initBottomBar(ARCHIVE_ID, this);
        mAdapter.notifyDataSetChanged();
    }

    private void testAddFirstPurchases() {
        //Skapar en produkt
        Product product = new Product("Aladob", 50.0, 12.0);

        // Skapar ett kvitto och lägger till produkten i kvittot
        Calendar cal = Calendar.getInstance();
        Receipt receipt = new Receipt(product, cal, 500.00, null);
        receipt.setCategory(Category.MAT);
        PrivatePurchase pur = new PrivatePurchase(receipt);

        //Skapar en anställd och ger den företag och kvitto med produkt
        IData dataHandler = (DataHandler) getApplicationContext();
        User user = dataHandler.readData(User.class.getName(), User.class);
        user.getCompany(new Company("DEFAULT COMPANY")).getEmployee(user.getName()).addPurchase(pur);
        dataHandler.writeData(User.class.getName(), user);
        mAdapter.notifyDataSetChanged();
    }

    private void collectAllPurchases() {
        IData dataHandler = (IData) getApplicationContext();
        User user = dataHandler.readData(User.class.getName(), User.class);
        List<Company> company = user.getCompanies();

        for (int i = 0; i < company.size(); i++) {
            List<Employee> emps = user.getCompanies().get(i).getEmployees();
        }
    }
}

