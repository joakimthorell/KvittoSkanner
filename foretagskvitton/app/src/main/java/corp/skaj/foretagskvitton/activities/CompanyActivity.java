package corp.skaj.foretagskvitton.activities;

import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.controllers.CompanyListController;
import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.Employee;
import corp.skaj.foretagskvitton.model.IData;
import corp.skaj.foretagskvitton.model.User;
import corp.skaj.foretagskvitton.services.DataHandler;

import static corp.skaj.foretagskvitton.controllers.CompanyListController.COMPANY_KEY;

public class CompanyActivity extends AbstractActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);

        String companyName = getIntent().getExtras().get(COMPANY_KEY).toString();

        IData iData = (DataHandler) getApplicationContext();
        User user = iData.readData(User.class.getName(), User.class);
        List<Company> companies = user.getCompanies();
        

        /*CompanyListController listViewController = new CompanyListController();
        Button button = (Button) findViewById(R.id.buttonForCompany);
        listViewController.initButtonListener(button, this);*/


        CompanyListController companyListController = new CompanyListController();

        //Creating new TextViews and Buttons and connecting them
        Button button = (Button) findViewById(R.id.edit);
        TextView textView1 = (TextView) findViewById(R.id.editText);
        TextView textView2 = (TextView) findViewById(R.id.editText2);
        TextView textView3 = (TextView) findViewById(R.id.editText3);

        companyListController.editButtonListener(button, textView1, textView2, textView3);

        ImageButton addEmployeeButton = (ImageButton) findViewById(R.id.addNewEmployee);
        companyListController.createNewEmployeeListener(addEmployeeButton);

        ImageButton addCardButton = (ImageButton) findViewById(R.id.addNewCard);
        companyListController.createNewCardListener(addCardButton);

        ImageButton addCommentButton = (ImageButton) findViewById(R.id.addNewComment);
        companyListController.createNewCommentListener(addCommentButton);


        //Code for the tool-and actionbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.company_action_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(companyName);

        TextView textViewForToolbar = (TextView) findViewById(R.id.toolbar_title);
        textViewForToolbar.setText(companyName);

    }
}
