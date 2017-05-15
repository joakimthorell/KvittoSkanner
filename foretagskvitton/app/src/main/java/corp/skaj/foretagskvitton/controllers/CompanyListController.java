package corp.skaj.foretagskvitton.controllers;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.text.InputType;
import android.text.method.KeyListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.activities.AbstractActivity;
import corp.skaj.foretagskvitton.activities.CompanyActivity;
import corp.skaj.foretagskvitton.activities.CompanyListActivity;

public class CompanyListController <T>{
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

    public static void editButtonListener(final Button button, final TextView tv1, final TextView tv2, final TextView tv3) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //När vi klickar på denna knappen vill vi öppna upp för redigering
                //Byta namn på knappen till spara
                button.setText("Spara");

                tv1.setFocusable(true);
                tv1.setClickable(true);
                tv1.setFocusableInTouchMode(true);

                tv2.setFocusable(true);
                tv2.setClickable(true);
                tv2.setFocusableInTouchMode(true);

                tv3.setFocusable(true);
                tv3.setClickable(true);
                tv3.setFocusableInTouchMode(true);

            }
        });

    }

    public void createNewEmployeeListener (Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Här vill vi lägga till en ny anställd i listan av anställda
            }
        });

    }

    public void createNewCardListener (Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Här vill vi lägga till ett nytt kort i listan av kort
            }
        });

    }

    public void createNewCommentListener (Button button) {
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