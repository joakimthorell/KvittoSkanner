package corp.skaj.foretagskvitton.controllers;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.List;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.IData;
import corp.skaj.foretagskvitton.model.Supplier;
import corp.skaj.foretagskvitton.model.User;
import corp.skaj.foretagskvitton.view.MultiDialog;
import corp.skaj.foretagskvitton.view.SupplierListFragment;

public class SupplierFABController extends FABController
    implements MultiDialog.Callback {

    public SupplierFABController(Context context) {
        super(context, null);
    }

    @Override
    public void bindButton(FloatingActionsMenu button) {
        bindFAB(button);
    }

    private void bindFAB(final FloatingActionsMenu button) {
        button.setOnFloatingActionsMenuUpdateListener(new FloatingActionsMenu.OnFloatingActionsMenuUpdateListener() {
            @Override
            public void onMenuExpanded() {
                button.collapse();
                showAddNewDialog();
            }

            @Override
            public void onMenuCollapsed() {

            }
        });
    }

    private void showAddNewDialog() {
        new MultiDialog(getContext(),
                this,
                MultiDialog.Type.CREATER,
                getContext().getString(R.string.text_supplier))
                .newDialog().show();
    }

    @Override
    public void dialogData(String newData, String oldData) {
        if (newData != null && newData.length() > 0) {
            Supplier s = new Supplier(newData);
            IData handler = ((IData) getContext().getApplicationContext());
            User user = handler.getUser();
            user.addSupplier(s);
            handler.saveUser();

            List<Supplier> suppliers = handler.getUser().getSuppliers();
            AppCompatActivity activity = (AppCompatActivity) getContext();
            SupplierListFragment fragment = (SupplierListFragment) activity.getSupportFragmentManager().findFragmentById(R.id.main_fragment_container);
            fragment.getAdapter().setNewData(suppliers);

            Toast.makeText(getContext(), (getContext().getString(R.string.text_created) + " " + newData), Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(getContext(), getContext().getString(R.string.text_not_saved), Toast.LENGTH_SHORT).show();
    }
}