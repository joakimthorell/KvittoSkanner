package corp.skaj.foretagskvitton.activities;

import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.widget.Button;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.controllers.ListViewController;


import static corp.skaj.foretagskvitton.controllers.ListViewController.COMPANY_KEY;

public class CompanyActivity extends AbstractActivity {

    public static final String STATE_FOR_BOTTOM_MENU = "CompanyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);

        String companyName = getIntent().getExtras().get(COMPANY_KEY).toString();

        ListViewController listViewController = new ListViewController();
        Button button = (Button) findViewById(R.id.buttonForCompany);
        listViewController.initButtonListener(button, this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(companyName);

        //initBottomBar(STATE_FOR_BOTTOM_MENU, this);

        //TODO vill ha en bottombar eller case i bottomnav för att kunna skicka med rätt context
    }
}
