package corp.skaj.foretagskvitton.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.controllers.CompanyListController;
import corp.skaj.foretagskvitton.model.User;

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

        User user = getDataHandler().readData(User.class.getName(), User.class);

        //The code hided below is for when we do have a list of companies

        CompanyListController controller = new CompanyListController();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.company_list_view, controller.getCompanyNames(user));
        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        controller.initListViewListener(listView, CompanyActivity.class, this);
        initBottomBar(COMPANY_ID, this);
    }
}



