package corp.skaj.foretagskvitton.activities;

import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.controllers.CompanyListController;
import corp.skaj.foretagskvitton.model.IData;
import corp.skaj.foretagskvitton.model.User;

import static corp.skaj.foretagskvitton.controllers.CompanyListController.COMPANY_KEY;

public class CompanyActivity extends AbstractActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);

        String companyName = getIntent().getExtras().get(COMPANY_KEY).toString();

        //Getting hold of the user
        IData iData = (IData) getApplicationContext();
        User user = iData.readData(User.class.getName(), User.class);

        //Code for the tool-and actionbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.company_action_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(companyName);

        TextView textViewForToolbar = (TextView) findViewById(R.id.toolbar_title);
        textViewForToolbar.setText(companyName);

        /*CompanyListController listViewController = new CompanyListController();
        Button button = (Button) findViewById(R.id.buttonForCompany);
        listViewController.initButtonListener(button, this);*/

        CompanyListController companyListController = new CompanyListController();

        //Creating new TextViews and Buttons and connecting them
        Button button = (Button) findViewById(R.id.edit);
        TextView textView1 = (TextView) findViewById(R.id.editText);
        TextView textView2 = (TextView) findViewById(R.id.editText2);
        TextView textView3 = (TextView) findViewById(R.id.editText3);

        //Creating a List to hold att the textViews
        List<TextView> textViews = new ArrayList<>();
            textViews.add(textView1);
            textViews.add(textView2);
            textViews.add(textView3);

        companyListController.editButtonListener(button, textViews);

        //Creating connecting the xml with the java code for the image buttons
        ImageButton addEmployeeButton = (ImageButton) findViewById(R.id.addNewEmployee);
        companyListController.createNewEmployeeListener(addEmployeeButton, user, companyName);

        ImageButton addCardButton = (ImageButton) findViewById(R.id.addNewCard);
        companyListController.createNewCardListener(addCardButton, user, companyName);

        ImageButton addCommentButton = (ImageButton) findViewById(R.id.addNewComment);
        companyListController.createNewCommentListener(addCommentButton, user, companyName);

        //Delete button for deleting an entire company
        Button deleteButton = (Button) findViewById(R.id.radera);
        companyListController.deleteCompanyListener(deleteButton, user, companyName);

    }
}
