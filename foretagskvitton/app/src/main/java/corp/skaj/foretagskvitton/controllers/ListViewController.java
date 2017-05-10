package corp.skaj.foretagskvitton.controllers;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import corp.skaj.foretagskvitton.activities.AbstractActivity;
import corp.skaj.foretagskvitton.activities.CompanyActivity;

/**
 * Created by annekeller on 2017-05-08.
 */

public class ListViewController {

    public static final String COMPANY_KEY = "CompanyKey";

    public ListViewController() {

    }

    public void initListViewListener(final ListView listView, final Context context) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, CompanyActivity.class);
                intent.putExtra(COMPANY_KEY, listView.getItemAtPosition(position).toString());
                context.startActivity(intent);
            }

        });
    }

    public void initButtonListener (Button button, final CompanyActivity companyActivity) {
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                companyActivity.finish();
            }
        });
    }



}
