package corp.skaj.foretagskvitton.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.roughike.bottombar.BottomBar;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.controllers.BottomNavigationController;

public class CompanyActivity extends AbstractActivity {

    public static final String STATE_FOR_BOTTOM_MENU = "CompanyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Företag");

        initBottomBar(STATE_FOR_BOTTOM_MENU);
    }
}
