package corp.skaj.foretagskvitton.activities;

/**
 * Created by annekeller on 2017-05-06.
 */

import com.roughike.bottombar.BottomBar;

import corp.skaj.foretagskvitton.R;
import android.os.Bundle;
import corp.skaj.foretagskvitton.controllers.BottomNavigationController;


public class CompanyActivity extends AbstractActivity {

    public static final String STATE_FOR_BOTTOM_MENY = "corp.skaj.foretagskvitton.COMPANY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        BottomNavigationController.setupBottomNavBar(bottomBar, STATE_FOR_BOTTOM_MENY, this);

    }
}
