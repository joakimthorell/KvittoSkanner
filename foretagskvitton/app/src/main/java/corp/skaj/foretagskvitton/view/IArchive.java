package corp.skaj.foretagskvitton.view;

import android.view.MenuItem;

import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.Employee;
import corp.skaj.foretagskvitton.model.User;

public interface IArchive {

    void bindEmployeeMenuItem(MenuItem item, final ArchiveAdapter adapter, final Employee employee);

    void bindCompanyMenuItem(MenuItem item, final ArchiveAdapter adapter, final Company company);
}
