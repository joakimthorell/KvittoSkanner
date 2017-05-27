package corp.skaj.foretagskvitton.controllers;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.afollestad.materialcab.MaterialCab;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.Supplier;
import corp.skaj.foretagskvitton.model.User;
import corp.skaj.foretagskvitton.view.SupplierAdapter;

public class SupplierToolbarController extends AbstractToolbarController<Supplier> {

    public SupplierToolbarController(Context context, SupplierAdapter adapter) {
        super(context,
                new MaterialCab((AppCompatActivity) context, R.id.cab_stub)
                        .setBackgroundColorRes(R.color.colorPrimary)
                        .setMenu(R.menu.remove_menu));

        setListener(adapter);
    }

    @Override
    public boolean onCabItemClicked(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_remove:
                removeSelectedItems();
                return true;
            default:
                return false;
        }
    }

    @Override
    protected void removeItemFromUser(Supplier object, User user) {
        user.removeSupplier(object);
    }
}
