package corp.skaj.foretagskvitton.controllers;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import corp.skaj.foretagskvitton.activities.CompanyActivity;
import corp.skaj.foretagskvitton.activities.CompanyListingActivity;

/**
 * Created by annekeller on 2017-05-08.
 */

public class ListViewController {

    public ListViewController () {

    }

    public void initListViewListener (final ListView listView, final Context context) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, CompanyActivity.class);
                intent.putExtra("Företag", listView.getItemAtPosition(position).toString());
                context.startActivity(intent);
            }

        });
    }



}
