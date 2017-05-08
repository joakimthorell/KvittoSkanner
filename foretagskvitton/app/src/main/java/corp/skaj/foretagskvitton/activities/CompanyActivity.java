package corp.skaj.foretagskvitton.activities;

import android.os.Bundle;

import android.widget.ArrayAdapter;

import corp.skaj.foretagskvitton.R;

import com.roughike.bottombar.BottomBar;

import android.widget.ListView;

import java.util.List;

import corp.skaj.foretagskvitton.controllers.BottomNavigationController;
import corp.skaj.foretagskvitton.controllers.DataHolder;
import corp.skaj.foretagskvitton.model.Company;

public class CompanyActivity extends AbstractActivity {
    public static final String STATE_FOR_BOTTOM_MENY = "CompanyActivity";

    //This is possible in all Activities where we need to get our User, this is for when we do have a list of companies
    //DataHolder dataholder = (DataHolder)getApplicationContext();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        BottomNavigationController.setupBottomNavBar(bottomBar, STATE_FOR_BOTTOM_MENY, this);

        populateListView();
        //registerForClicks();
    }

    private void populateListView() {
        //The code hided below is for when we do have a list of companies
        /*List<Company> companies = dataholder.getUser().getCompanies();

        String[] companyNames = new String[companies.size()];
        for (int i = 0; i < companies.size(); i++) {
            companyNames[i] = companies.get(i).getName();*/

            //This is just an example
            String[] companyNames = {"IKEA", "SIBA", "ELGIGANTEN", "COOP", "WILLYS"};

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    this,               //context
                    R.layout.list_view, //layout to use
                    companyNames);      //items to be displayed

            ListView list = (ListView) findViewById(R.id.listView);
            list.setAdapter(adapter);
        }


    private void registerForClicks() {

        //TODO Needs a method that registers what company we do click on


    }


}
