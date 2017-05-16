package corp.skaj.foretagskvitton.controllers;

import android.content.Context;
import android.content.Intent;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import corp.skaj.foretagskvitton.model.Card;
import corp.skaj.foretagskvitton.model.Comment;
import corp.skaj.foretagskvitton.model.Employee;
import corp.skaj.foretagskvitton.model.User;


public class CompanyListController <T>{
    public static final String COMPANY_KEY = "CompanyKey";

    public CompanyListController() {

    }


    /**
     *
     * @param listView
     * @param nextActivityToStart
     * @param context
     */
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

    public static void editButtonListener(final Button button, final List<TextView> tv) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    button.setText("Spara");

                    for(int i = 0; i < tv.size() - 1; i++) {
                        tv.get(i).setFocusable(true);
                        tv.get(i).setClickable(true);
                        tv.get(i).setFocusableInTouchMode(true);

                    }

                //Spara undan det som man editerat, var sparar vi det? Hur kollar vi vad som är editerat?
            }
        });
    }


    public void createNewEmployeeListener (ImageButton button, final User user, final String company) {
        button.setOnClickListener(new View.OnClickListener() {
            //List<Employee> employees = user.getCompany(company).getEmployees();
            @Override
            public void onClick(View v) {

                //Här vill vi lägga till en ny anställd i listan av anställda
                //Skapa en ny text view?

                //employees.add(new Employee("ny anställd")); //Här har vi någon form av input, den inputen ska in i setText
                //TextView textView = new TextView();
                //textView.setText();

            }
        });

    }

    public void createNewCardListener (ImageButton button, final User user, final String company) {
        button.setOnClickListener(new View.OnClickListener() {
            //List<Card> cards = user.getCompany(company).getCards();
            @Override
            public void onClick(View v) {
                //Här vill vi lägga till ett nytt kort i listan av kort
                //cards.add(new Card(1234));

            }
        });

    }

    public void createNewCommentListener (ImageButton button, final User user, final String company) {
        button.setOnClickListener(new View.OnClickListener() {
            //List<Comment> comments = user.getCompany(company).getComments();
            @Override
             public void onClick(View v) {
             //Här vill vi lägga till en ny kommentar i listan av kommentarer
                //comments.add(new Comment("information"));
         }
     });
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