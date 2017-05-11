package corp.skaj.foretagskvitton.activities;

import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import corp.skaj.foretagskvitton.R;


import static corp.skaj.foretagskvitton.controllers.ListViewController.COMPANY_KEY;

public class CompanyActivity extends AbstractActivity {
    public static final Integer BOTTOM_BAR_ID = R.id.action_business;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);

        String companyName = getIntent().getExtras().get(COMPANY_KEY).toString();

        /*ListViewController listViewController = new ListViewController();
        Button button = (Button) findViewById(R.id.buttonForCompany);
        listViewController.initButtonListener(button, this);*/

        Toolbar toolbar = (Toolbar) findViewById(R.id.company_action_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(companyName);

        TextView textView = (TextView) findViewById(R.id.toolbar_title);
        textView.setText(companyName);

        initBottomBar(BOTTOM_BAR_ID, this);

        //TODO vill ha en bottombar eller case i bottomnav för att kunna skicka med rätt context
    }
}
