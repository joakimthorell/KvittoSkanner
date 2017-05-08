package corp.skaj.foretagskvitton.activities;

import android.os.Bundle;

import corp.skaj.foretagskvitton.R;

import com.roughike.bottombar.BottomBar;
import corp.skaj.foretagskvitton.controllers.BottomNavigationController;

public class CompanyActivity extends AbstractActivity {

    public static final String STATE_FOR_BOTTOM_MENY = "CompanyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        BottomNavigationController.setupBottomNavBar(bottomBar, STATE_FOR_BOTTOM_MENY, this);
    }
}
