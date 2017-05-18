package corp.skaj.foretagskvitton.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Toast;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.IData;
import corp.skaj.foretagskvitton.services.DataHandler;
import corp.skaj.foretagskvitton.view.ArchiveAdapter;
import corp.skaj.foretagskvitton.view.ListFragment;
import corp.skaj.foretagskvitton.view.SupplierAdapter;

public class UserActivity extends AbstractActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_user);
            initBottomBar(USER_ID, this);
            IData dataHandler = (IData) getApplicationContext();

            ListFragment fragment = ListFragment.create(new SupplierAdapter(dataHandler));

            FragmentManager fm = getSupportFragmentManager();
            // Replaces the old layout with the new layout.
            fm.beginTransaction().replace(R.id.fragment_container, fragment).commit();


    }

    public void resetData(View view) {
        Toast.makeText(this, "You now have to restart the app", Toast.LENGTH_LONG).show();
        DataHandler handler = (DataHandler) getApplicationContext();
        handler.clearData();
    }
}
