package corp.skaj.foretagskvitton.controllers;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import corp.skaj.foretagskvitton.activities.CompanyActivity;
import corp.skaj.foretagskvitton.activities.CompanyListActivity;

public class CompanyListController <T> {
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
}

    /*public void initButtonListener (Button button, final Activity activity) {

        public void initButtonListener (Button button,final Activity activity){
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    activity.finish();
                }
            });
        }
    }
}*/
