package corp.skaj.foretagskvitton.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.view.CompanyFragment;

public class CompanyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);
        String companyName = getIntent().getStringExtra(MainActivity.COMPANY_KEY);
        CompanyFragment cf = CompanyFragment.create(companyName);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.company_fragment_container, cf).commit();

        //TODO CompanyController

    }
}
