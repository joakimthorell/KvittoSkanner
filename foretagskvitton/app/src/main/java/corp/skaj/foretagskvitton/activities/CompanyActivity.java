package corp.skaj.foretagskvitton.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.controllers.CompanyController;
import corp.skaj.foretagskvitton.controllers.CompanyFABController;
import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.view.CompanyFragment;


public class CompanyActivity extends AbstractActivity {
    public static final String FROM_COMPANY_ACTIVITY = "company_activity_back_pressed";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);
        String companyName = getIntent().getStringExtra(MainActivity.COMPANY_KEY);
        //Setting the controller for company fragment
        CompanyFragment cf = CompanyFragment.create(companyName);
        Company selectedCompany = getDataHandler().getUser().getCompany(companyName);
        CompanyFABController fabController = new CompanyFABController(this, selectedCompany, cf);
        cf.setFabController(fabController);
        CompanyController companyController = new CompanyController(getDataHandler(), this, cf);
        cf.setListener(companyController);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.company_fragment_container, cf).commit();

    }

    @Override
    public void onBackPressed() {
        goBack();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                goBack();
                return true;
            default:
                return false;
        }
    }

    private boolean goBack() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setAction(FROM_COMPANY_ACTIVITY);
        startActivity(intent);
        return true;
    }
}
