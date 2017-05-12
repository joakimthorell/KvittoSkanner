package corp.skaj.foretagskvitton.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.controllers.ListViewController;

public class CompanyListingActivity extends AbstractActivity {
    public static final Integer BOTTOM_BAR_ID = R.id.action_business;
    public static final String CONTEXT_KEY = "CompanyListingKey";

    //This is possible in all Activities where we need to get our User, this is for when we do have a list of companies
    //DataHolder dataholder = (DataHolder)getApplicationContext();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_listing);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Företag");

        //initBottomBar(BOTTOM_BAR_ID, this);

        //The code hided below is for when we do have a list of companies
        /*List<Company> companies = dataholder.getUser().getCompanies();

        String[] companyNames = new String[companies.size()];
        for (int i = 0; i < companies.size(); i++) {
            companyNames[i] = companies.get(i).getName();*/

        //This is just an example
        String[] companyNames = {"IKEA", "SIBA", "ELGIGANTEN", "COOP", "WILLYS", "TELIA", "SKANSKA", "NORDEA", "PRESSBYRÅN"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.company_list_view, companyNames);

        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        ListViewController listViewController = new ListViewController();
        listViewController.initListViewListener(listView, this);

        initBottomBar(BOTTOM_BAR_ID, this);
    }
}



