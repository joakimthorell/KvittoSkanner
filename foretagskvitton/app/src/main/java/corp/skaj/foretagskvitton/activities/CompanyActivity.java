package corp.skaj.foretagskvitton.activities;

import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.controllers.CompanyListController;

import static corp.skaj.foretagskvitton.controllers.CompanyListController.COMPANY_KEY;

public class CompanyActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);

        String companyName = getIntent().getExtras().get(COMPANY_KEY).toString();

        /*CompanyListController listViewController = new CompanyListController();
        Button button = (Button) findViewById(R.id.buttonForCompany);
        listViewController.initButtonListener(button, this);*/

        CompanyListController companyListController = new CompanyListController();

        Button button = (Button) findViewById(R.id.edit);
        TextView textView1 = (TextView) findViewById(R.id.editText);
        TextView textView2 = (TextView) findViewById(R.id.editText2);
        TextView textView3 = (TextView) findViewById(R.id.editText3);

        companyListController.editButtonListener(button, textView1, textView2, textView3);


        Toolbar toolbar = (Toolbar) findViewById(R.id.company_action_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(companyName);

        TextView textViewForToolbar = (TextView) findViewById(R.id.toolbar_title);
        textViewForToolbar.setText(companyName);

        //TODO vill ha en bottombar eller case i bottomnav för att kunna skicka med rätt context
    }
}
