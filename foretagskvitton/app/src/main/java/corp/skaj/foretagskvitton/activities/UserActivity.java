package corp.skaj.foretagskvitton.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.roughike.bottombar.BottomBar;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.controllers.BottomNavigationController;

public class UserActivity extends AppCompatActivity {

        public static final String STATE_FOR_BOTTOM_MENY = "UserActivity";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_company);

            BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
            BottomNavigationController.setupBottomNavBar(bottomBar, STATE_FOR_BOTTOM_MENY, this);
    }
}
