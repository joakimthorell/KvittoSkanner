package corp.skaj.foretagskvitton.controllers;

import android.content.Context;
import android.content.Intent;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;


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

    public static void editButtonListener(final Button button, final TextView tv1, final TextView tv2, final TextView tv3) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    button.setText("Spara");

                    //Nedan kan nog lösas på ett snyggare sätt...
                    tv1.setFocusable(true);
                    tv1.setClickable(true);
                    tv1.setFocusableInTouchMode(true);

                    tv2.setFocusable(true);
                    tv2.setClickable(true);
                    tv2.setFocusableInTouchMode(true);

                    tv3.setFocusable(true);
                    tv3.setClickable(true);
                    tv3.setFocusableInTouchMode(true);

                //Spara undan det som man editerat
            }
        });
    }


    public void createNewEmployeeListener (ImageButton button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Här vill vi lägga till en ny anställd i listan av anställda
                //Skapa en ny text view?
            }
        });

    }


    public void createNewCardListener (ImageButton button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Här vill vi lägga till ett nytt kort i listan av kort
            }
        });

    }

    public void createNewCommentListener (ImageButton button) {
        button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
             //Här vill vi lägga till en ny kommentar i listan av kommentarer
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