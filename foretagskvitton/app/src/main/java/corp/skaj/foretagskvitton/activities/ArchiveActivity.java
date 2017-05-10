package corp.skaj.foretagskvitton.activities;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import corp.skaj.foretagskvitton.R;

import corp.skaj.foretagskvitton.controllers.DataHolder;
import corp.skaj.foretagskvitton.controllers.ReceiptAdapter;
import corp.skaj.foretagskvitton.model.Receipt;
import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.Employee;
import corp.skaj.foretagskvitton.model.Purchase;

public class ArchiveActivity extends AbstractActivity {
    public static final String STATE_FOR_BOTTOM_MENU = "ArchiveActivity";
    private List<Receipt> receiptsList;
    private RecyclerView recyclerView;
    private ReceiptAdapter mAdapter;

    DataHolder dataHolder;
    List<Company> companiesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        receiptsList = new ArrayList<>();
        dataHolder = (DataHolder) getApplicationContext();
        companiesList = dataHolder.getUser().getCompanies();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new ReceiptAdapter(receiptsList, companiesList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareReceiptData();
    }

    private void prepareReceiptData() {
        for (int i = 0; i < companiesList.size(); i++) {
            List<Employee> employees = companiesList.get(i).getEmployees();
            for (int j = 0; j < employees.size(); i++) {
                List<Purchase> purchases = employees.get(j).getPurchases();
                for (int k = 0; k < purchases.size(); k++) {
                    purchases.get(k).getReceipt().getDate();
                }
            }
        }

        mAdapter.notifyDataSetChanged();
    }
}