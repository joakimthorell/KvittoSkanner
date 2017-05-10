package corp.skaj.foretagskvitton.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;

import java.util.ArrayList;
import java.util.List;
import corp.skaj.foretagskvitton.R;

import corp.skaj.foretagskvitton.controllers.BottomNavigationController;
import corp.skaj.foretagskvitton.controllers.DataHolder;
import corp.skaj.foretagskvitton.controllers.ReceiptAdapter;
import corp.skaj.foretagskvitton.model.Receipt;
import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.Employee;
import corp.skaj.foretagskvitton.model.Purchase;
import corp.skaj.foretagskvitton.controllers.BottomNavigationController;

public class ArchiveActivity extends AbstractActivity {
    private List<Receipt> receiptsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ReceiptAdapter mAdapter;
    protected Context mContext;
    public static final String STATE_FOR_BOTTOM_MENU = "ArchiveActivity";

    DataHolder dataHolder = (DataHolder)mContext.getApplicationContext();
    List<Company> companiesList = dataHolder.getUser().getCompanies();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.archive_activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        BottomNavigationController.setupBottomNavBar(bottomBar, STATE_FOR_BOTTOM_MENU, this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new ReceiptAdapter(receiptsList, companiesList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        initBottomBar(STATE_FOR_BOTTOM_MENU, this);
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