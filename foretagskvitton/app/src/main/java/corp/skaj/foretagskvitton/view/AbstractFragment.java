package corp.skaj.foretagskvitton.view;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.Employee;
import corp.skaj.foretagskvitton.model.IData;
import corp.skaj.foretagskvitton.model.Purchase;
import corp.skaj.foretagskvitton.model.User;

public class AbstractFragment extends Fragment {

    protected ArrayAdapter<String> buildArrayAdapter(List<String> list) {
        return new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, list);
    }

    protected void setArrayAdapter(ArrayAdapter<String> adapter, Spinner spinner) {
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    protected List<String> getEmployees(String companyName, Purchase purchase) {
        List<String> list = new ArrayList<>();
        Company company = getCompany(companyName, purchase);
        for (Employee e : company.getEmployees()) {
            list.add(e.getName());
        }
        return list;
    }

    private Company getCompany(String companyName, Purchase purchase) {
        if (purchase == null) {
            return getUser().getCompany(companyName);
        }
        return getUser().getCompany(purchase);
    }

    protected IData getDataHandler() {
        return (IData) getContext().getApplicationContext();
    }

    protected User getUser() {
        return getDataHandler().getUser();
    }
}
