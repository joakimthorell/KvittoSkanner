package corp.skaj.foretagskvitton.activities;

import android.os.Bundle;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.User;
import corp.skaj.foretagskvitton.services.DataHandler;

public class UserActivity extends AbstractActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_company_listing);
            initBottomBar(USER_ID, this);
    }
}
