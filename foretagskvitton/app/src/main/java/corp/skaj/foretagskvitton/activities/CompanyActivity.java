package corp.skaj.foretagskvitton.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.controllers.CompanyController;
import corp.skaj.foretagskvitton.view.CompanyFragment;

public class CompanyActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);
        String companyName = getIntent().getStringExtra(MainActivity.COMPANY_KEY);
        //Setting the controller for company fragment
        CompanyFragment cf = CompanyFragment.create(companyName);
        CompanyController companyController = new CompanyController(getDataHandler(), this, cf);
        cf.setListener(companyController);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.company_fragment_container, cf).commit();

        //TODO CompanyController


    }
}
