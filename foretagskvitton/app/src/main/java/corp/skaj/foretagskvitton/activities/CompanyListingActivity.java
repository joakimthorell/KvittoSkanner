package corp.skaj.foretagskvitton.activities;

import android.os.Bundle;
import android.content.Intent;

import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import corp.skaj.foretagskvitton.R;

import com.roughike.bottombar.BottomBar;

import android.widget.ListView;

import corp.skaj.foretagskvitton.controllers.BottomNavigationController;
import corp.skaj.foretagskvitton.controllers.ListViewController;

public class CompanyListingActivity extends AbstractActivity {
    public static final String STATE_FOR_BOTTOM_MENU = "CompanyListingActivity";
    public static final String CONTEXT_KEY = "CompanyListingKey";

    //This is possible in all Activities where we need to get our User, this is for when we do have a list of companies
    //DataHolder dataholder = (DataHolder)getApplicationContext();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_listing);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Företag");

        initBottomBar(STATE_FOR_BOTTOM_MENU, this);

        //The code hided below is for when we do have a list of companies
        /*List<Company> companies = dataholder.getUser().getCompanies();

        String[] companyNames = new String[companies.size()];
        for (int i = 0; i < companies.size(); i++) {
            companyNames[i] = companies.get(i).getName();*/

        //This is just an example
        String[] companyNames = {"IKEA", "SIBA", "ELGIGANTEN", "COOP", "WILLYS", "TELIA", "SKANSKA", "NORDEA", "PRESSBYRÅN"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,                       //context
                R.layout.company_list_view, //layout to use
                companyNames);              //items to be displayed

        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        ListViewController listViewController = new ListViewController();
        listViewController.initListViewListener(listView, this);


    }
}



