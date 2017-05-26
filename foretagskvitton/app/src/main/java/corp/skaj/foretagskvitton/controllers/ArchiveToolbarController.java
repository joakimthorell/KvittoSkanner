package corp.skaj.foretagskvitton.controllers;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.afollestad.materialcab.MaterialCab;
import com.chad.library.adapter.base.BaseQuickAdapter;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.Employee;
import corp.skaj.foretagskvitton.model.Purchase;
import corp.skaj.foretagskvitton.model.User;
import corp.skaj.foretagskvitton.view.ArchiveAdapter;
import corp.skaj.foretagskvitton.view.IArchive;

public class ArchiveToolbarController extends ToolbarController<Purchase>
    implements IArchive {

    private ArchiveAdapter mAdapter;

    public ArchiveToolbarController(Context context, ArchiveAdapter adapter) {
        super(context,
                new MaterialCab((AppCompatActivity) context, R.id.cab_stub)
                        .setBackgroundColorRes(R.color.colorPrimary)
                        .setMenu(R.menu.cab_menu));

        mAdapter = adapter;
        setListener(adapter);
    }

    @Override
    public boolean onCabItemClicked(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_remove:
                removeSelectedItems();
                return true;
            case R.id.action_share:
                return true;
            default:
                return false;
        }
    }

    @Override
    protected void removeItemFromUser(Purchase object, User user) {
        Company company = user.getCompany(object);
        Employee employee = company.getEmployee(object);
        employee.removePurchase(object);
    }

    @Override
    public void bindEmployeeMenuItem(MenuItem item, final Employee employee) {
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                mAdapter.showEmployee(employee);
                return true;
            }
        });
    }

    @Override
    public void bindCompanyMenuItem(MenuItem item, final Company company) {
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                mAdapter.showCompany(company);
                return true;
            }
        });
    }
}