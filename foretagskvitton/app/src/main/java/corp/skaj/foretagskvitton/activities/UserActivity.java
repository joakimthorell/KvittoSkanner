package corp.skaj.foretagskvitton.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.services.DataHandler;

public class UserActivity extends AbstractActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_user);
            initBottomBar(USER_ID, this);
    }

    public void resetData(View view) {
        Toast.makeText(this, "You now have to restart the app", Toast.LENGTH_LONG).show();
        DataHandler handler = (DataHandler) getApplicationContext();
        handler.clearData();
    }
}
