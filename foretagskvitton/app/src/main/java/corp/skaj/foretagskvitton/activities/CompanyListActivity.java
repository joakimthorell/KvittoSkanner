package corp.skaj.foretagskvitton.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.controllers.CompanyListController;

public class CompanyListActivity extends AbstractActivity {
    public static final String COMPANYLIST_KEY = "COMPANYLIST_KEY";

    //This is possible in all Activities where we need to get our User, this is for when we do have a list of companies
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_listing);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Företag");

        //The code hided below is for when we do have a list of companies

        //The code hided below is for when we do have a list of companies
        /*IData iData = (DataHandler) getApplicationContext();
        User user = iData.readData(User.class.getName(), User.class);
        List<Company> companies = user.getCompanies();
        String[] companyNames = new String[companies.size()];
        for (int i = 0; i < companies.size(); i++) {
            companyNames[i] = companies.get(i).getName();*/

        //This is just an example
        String[] companyNames = {"IKEA", "SIBA", "ELGIGANTEN", "COOP", "WILLYS", "TELIA", "SKANSKA", "NORDEA", "PRESSBYRÅN"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.company_list_view, companyNames);

        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        new CompanyListController().initListViewListener(listView, CompanyActivity.class, this);

        //CompanyListController cLController = new CompanyListController();
        //cLController.initListViewListener(listView, this);

        initBottomBar(COMPANY_ID, this);
    }
}



