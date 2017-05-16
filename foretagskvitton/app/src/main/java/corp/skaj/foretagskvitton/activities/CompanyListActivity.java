package corp.skaj.foretagskvitton.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.controllers.CompanyListController;
import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.IData;
import corp.skaj.foretagskvitton.model.User;
import corp.skaj.foretagskvitton.services.DataHandler;

public class CompanyListActivity extends AbstractActivity {
    public static final String COMPANYLIST_KEY = "COMPANYLIST_KEY";

    //This is just an example
    //String[] companyNames = {"IKEA", "SIBA", "ELGIGANTEN", "COOP", "WILLYS", "TELIA", "SKANSKA", "NORDEA", "PRESSBYRÅN"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_listing);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Företag");

        //The code hided below is for when we do have a list of companies

        User user = getUser();
        List<Company> companies = user.getCompanies();
        String[] companyNames = new String[companies.size()];
        for (int i = 0; i < companies.size(); i++) {
            companyNames[i] = companies.get(i).getName();

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.company_list_view, companyNames);
            final ListView listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);

            new CompanyListController().initListViewListener(listView, CompanyActivity.class, this);

            initBottomBar(COMPANY_ID, this);
        }

    }
}



