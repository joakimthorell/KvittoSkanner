package corp.skaj.foretagskvitton.activities;

import android.os.Bundle;

import corp.skaj.foretagskvitton.R;

public class UserActivity extends AbstractActivity {
    public static final Integer BOTTOM_BAR_ID = R.id.action_user;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_company_listing);

            initBottomBar(BOTTOM_BAR_ID, this);
    }
}
