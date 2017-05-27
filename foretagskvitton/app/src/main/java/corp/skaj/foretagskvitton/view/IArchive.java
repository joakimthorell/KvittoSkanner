package corp.skaj.foretagskvitton.view;

import android.view.MenuItem;

import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.Employee;

public interface IArchive {

    void bindEmployeeMenuItem(MenuItem item, final Employee employee);

    void bindCompanyMenuItem(MenuItem item, final Company company);
}