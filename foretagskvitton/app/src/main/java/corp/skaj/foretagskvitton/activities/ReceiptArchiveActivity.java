package corp.skaj.foretagskvitton.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.controllers.BottomNavigationController;
import corp.skaj.foretagskvitton.view.CustomList;

public class ReceiptArchiveActivity extends AbstractActivity {
    private ListView list;

    public static final String STATE_FOR_BOTTOM_MENY = "corp.skaj.foretagskvitton.ARCHIVE";

    // Array of strings...
    String[] web = {"Android", "IPhone", "WindowsMobile", "Blackberry",
            "WebOS", "Ubuntu", "Windows7", "Max OS X"};

    Integer[] imageId = {
            R.drawable.ic_camera,
            R.drawable.ic_camera,
            R.drawable.ic_camera,
            R.drawable.ic_camera,
            R.drawable.ic_camera,
            R.drawable.ic_camera,
            R.drawable.ic_camera,
            R.drawable.ic_camera,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_archive);

        CustomList adapter = new
                CustomList(ReceiptArchiveActivity.this, web, imageId);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ReceiptArchiveActivity.this, "You Clicked at " + web[+position], Toast.LENGTH_SHORT).show();
            }
        });

        //TODO ...

        //ListView listView = (ListView) findViewById(R.id.mobile_list);
        //listView.setAdapter(adapter);

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        BottomNavigationController.setupBottomNavBar(bottomBar, STATE_FOR_BOTTOM_MENY, this);
    }

}
