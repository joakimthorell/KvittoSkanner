package corp.skaj.foretagskvitton.controllers;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.afollestad.materialcab.MaterialCab;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.Employee;
import corp.skaj.foretagskvitton.model.IData;
import corp.skaj.foretagskvitton.model.Purchase;
import corp.skaj.foretagskvitton.model.User;
import corp.skaj.foretagskvitton.view.ArchiveAdapter;

public class ArchiveToolbarController extends ToolbarController<Purchase> {

    public ArchiveToolbarController(Context context, BaseQuickAdapter adapter) {
        super(context,
                new MaterialCab((AppCompatActivity) context, R.id.cab_stub)
                        .setBackgroundColorRes(R.color.colorPrimary)
                        .setMenu(R.menu.cab_menu));

        setListener(adapter);
    }


    @Override
    public boolean onCabItemClicked(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_remove:
                removeSelectedItems();
        }
        return true;
    }



    @Override
    protected void removeItemFromUser(Purchase object, User user) {
        Company company = user.getCompany(object);
        Employee employee = company.getEmployee(object);
        employee.removePurchase(object);
    }
}
