package corp.skaj.foretagskvitton.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.controllers.CompanyListController;

public class CompanyListActivity extends AbstractActivity {
    public static final Integer BOTTOM_BAR_ID = R.id.action_business;
    public static final String COMPANYLIST_KEY = "COMPANYLIST_KEY";

    //This is possible in all Activities where we need to get our User, this is for when we do have a list of companies
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_listing);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Företag");

        //The code hided below is for when we do have a list of companies

        //This is just an example
        String[] companyNames = {"IKEA", "SIBA", "ELGIGANTEN", "COOP", "WILLYS", "TELIA", "SKANSKA", "NORDEA", "PRESSBYRÅN"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.company_list_view, companyNames);

        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        CompanyListController cLController = new CompanyListController();
        cLController.initListViewListener(listView, this);

        initBottomBar(BOTTOM_BAR_ID, this);
    }
}



