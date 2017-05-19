package corp.skaj.foretagskvitton.activities;

import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.controllers.CompanyListController;
import corp.skaj.foretagskvitton.model.Card;
import corp.skaj.foretagskvitton.model.Comment;
import corp.skaj.foretagskvitton.model.Employee;
import corp.skaj.foretagskvitton.model.User;

public class CompanyActivity extends AbstractActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);

        String companyName = getIntent().getStringExtra(MainActivity.COMPANY_KEY);

        //Getting hold of the user
        User user = getDataHandler().readData(User.class.getName(), User.class);

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
        EditText employeeView = (EditText) findViewById(R.id.employeEditText);
        EditText cardView = (EditText) findViewById(R.id.cardEditText);
        EditText commentView = (EditText) findViewById(R.id.commentEditText);

        //Creating a List to hold att the textViews
        List<TextView> textViews = new ArrayList<>();
        textViews.add(employeeView);
        textViews.add(cardView);
        textViews.add(commentView);

        companyListController.editButtonListener(button, textViews, employeeView, user, companyName);

        companyListController.employeeTextViewListener(employeeView, user, companyName);

        //Displaying employees
        List<Employee> employees = user.getCompany(companyName).getEmployees();
        displayEmployees(employees, employeeView);


        //Displaying cards
        List<Card> cards = user.getCompany(companyName).getCards();
        displayCards(cards, cardView);

        //Displaying comments
        List<Comment> comments = user.getCompany(companyName).getComments();
        displayComments(comments, commentView);

        //Creating connecting the xml with the java code for the image buttons
        ImageButton addEmployeeButton = (ImageButton) findViewById(R.id.addNewEmployee);
        companyListController.createNewEmployeeListener(addEmployeeButton, user, companyName, this, employeeView);

        ImageButton addCardButton = (ImageButton) findViewById(R.id.addNewCard);
        companyListController.createNewCardListener(addCardButton, user, companyName, cardView);

        ImageButton addCommentButton = (ImageButton) findViewById(R.id.addNewComment);
        companyListController.createNewCommentListener(addCommentButton, user, companyName, commentView);

        //Delete button for deleting an entire company
        Button deleteButton = (Button) findViewById(R.id.radera);
        companyListController.deleteCompanyListener(deleteButton, user, companyName, this);

    }

    public void displayEmployees (List<Employee> employees, TextView employeeView) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < employees.size(); i++) {
            builder.append(employees.get(i).getName() + "\n");
        }
        employeeView.setText(builder.toString());
    }

    public void displayCards (List<Card> cards, TextView cardView) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < cards.size(); i++) {
            builder.append(cards.get(i).getCard() + "\n");
        }
        cardView.setText(builder.toString());
    }

    public void displayComments (List<Comment> comments, TextView commentView) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < comments.size(); i++) {
            builder.append(comments.get(i).getComment() + "\n");
        }
        commentView.setText(builder.toString());
    }
}



