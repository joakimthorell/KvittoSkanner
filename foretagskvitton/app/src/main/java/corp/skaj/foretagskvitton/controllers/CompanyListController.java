package corp.skaj.foretagskvitton.controllers;

import android.content.Context;
import android.content.Intent;

import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import corp.skaj.foretagskvitton.activities.CompanyActivity;
import corp.skaj.foretagskvitton.model.Card;
import corp.skaj.foretagskvitton.model.Comment;
import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.Employee;
import corp.skaj.foretagskvitton.model.User;


public class CompanyListController {
    public static final String COMPANY_KEY = "CompanyKey";

    public CompanyListController() {
    }

    public void initListViewListener(final ListView listView, final Class<?> nextActivityToStart, final Context context) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, nextActivityToStart);
                intent.putExtra(COMPANY_KEY, listView.getItemAtPosition(position).toString());
                context.startActivity(intent);
            }
        });
    }

    public void editButtonListener(final Button button, final List<TextView> tv, final EditText editText, final User user, final String company) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggle(button, tv);

                if (button.getText() == "Spara") {
                    employeeTextViewListener(editText, user, company);
                    cardTextViewListener(editText,user,company);
                    commentTextViewListener(editText,user,company);
                }
            }
        });
    }

    public void createNewEmployeeListener(ImageButton button, final User user, final String company, final Context context, final EditText editText) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

    }

    //Saves the input in the textview
    public void employeeTextViewListener(final EditText editText, User user, final String company) {
        final List<Employee> employees = user.getCompany(company).getEmployees();
        if (employees.equals(null)) {
            employees.add(new Employee("Anställd"));
        }
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = editText.getText().toString();
                saveEmployee(input, employees);

            }
        });
    }

    //Saves the employee to the employee list
    public void saveEmployee (String employeeName, List<Employee> employees) {
        Employee employee = new Employee(employeeName);
        employees.add(employee);
    }

    public void createNewCardListener(ImageButton button, final User user, final String company, final EditText editText) {
        button.setOnClickListener(new View.OnClickListener() {
            List<Card> cards = user.getCompany(company).getCards();
            @Override
            public void onClick(View v) {


            }
        });

    }

    public void cardTextViewListener (final EditText editText, User user, final String company) {
        final List<Card> cards = user.getCompany(company).getCards();
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int input = Integer.parseInt(editText.getText().toString());
                saveCard(input, cards);
            }
        });

    }

    public void saveCard (int input, List<Card> cards) {
        Card card = new Card(input);
        cards.add(card);
    }

    public void createNewCommentListener(ImageButton button, final User user, final String company, final EditText editText) {
        button.setOnClickListener(new View.OnClickListener() {
            //List<Comment> comments = user.getCompany(company).getComments();
            @Override
            public void onClick(View v) {


            }
        });
    }

    public void commentTextViewListener (final EditText editText, User user, final String company) {
        final List<Comment> comments = user.getCompany(company).getComments();
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = editText.getText().toString();
                saveComment(input, comments);
            }
        });
    }

    public void saveComment (String input, List<Comment> comments) {
        Comment comment = new Comment(input);
        comments.add(comment);
    }

    public void deleteCompanyListener(final Button button, final User user, final String companyName, final Context context) {
        final List<Company> companies = user.getCompanies();
        final ConstraintLayout mainLayout = new ConstraintLayout(context);
        final PopupWindow popUpWindow = new PopupWindow();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (companies.size() == 1) {
                    Toast.makeText(context, "Du kan inte radera ditt enda företag",
                            Toast.LENGTH_SHORT).show();
                } else {
                    //TODO här ska vi ha en popup "är du säker på att du vill radera företaget"

                    Company company = user.getCompany(companyName);
                    companies.remove(company);
                }
            }
        });

    }


    public void toggle (Button button, List<TextView> tv) {
        if(button.getText() == "Editera") {
            button.setText("Spara");
            for (int i = 0; i < tv.size(); i++) {
                tv.get(i).setFocusable(true);
                tv.get(i).setClickable(true);
                tv.get(i).setFocusableInTouchMode(true);
            }
        } else {
            button.setText("Editera");
            for (int i = 0; i < tv.size(); i++) {
                tv.get(i).setFocusable(false);
                tv.get(i).setClickable(false);
                tv.get(i).setFocusableInTouchMode(false);
            }
        }
    }

    public String[] getCompanyNames(User user) {
        List<Company> companies = user.getCompanies();
        String[] companyNames = new String[companies.size()];
        for (int i = 0; i < companies.size(); i++) {
            companyNames[i] = companies.get(i).getName();
        }
        return companyNames;
    }


}

    /*public void initButtonListener (Button button, final Activity activity) {

        public void initButtonListener (Button button,final Activity activity){
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.finish();
                }
            }
        }
    }
}
*/