package corp.skaj.foretagskvitton.controllers;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.widget.EditText;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.List;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.IData;
import corp.skaj.foretagskvitton.model.Supplier;
import corp.skaj.foretagskvitton.model.User;
import corp.skaj.foretagskvitton.view.SupplierListFragment;

public class SupplierFABController extends FABController {
    public static final String CREATE_NEW_ACTION = "create_new_action";

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
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());

        final EditText edittext = new EditText(getContext());
        edittext.setSingleLine(true);

        alert.setMessage("Skriv grossist namn:");
        alert.setTitle("Skapa ny");

        alert.setView(edittext);

        alert.setPositiveButton("Lägg till", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //What ever you want to do with the value
                Editable supplierName = edittext.getText();

                if(supplierName.toString().length() < 1){
                    return;
                }

                IData handler = ((IData) getContext().getApplicationContext());
                User user = handler.getUser();
                Supplier supplier = new Supplier(supplierName.toString());
                user.addSupplier(supplier);
                handler.saveUser();
                List<Supplier> suppliers = handler.getUser().getSuppliers();

                AppCompatActivity activity = (AppCompatActivity) getContext();
                SupplierListFragment fragment = (SupplierListFragment) activity.getSupportFragmentManager().findFragmentById(R.id.main_fragment_container);
                fragment.getAdapter().setNewData(suppliers);

                Toast.makeText(getContext(), "Lagt till ny grossist " + supplier.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        alert.setNegativeButton("Avbryt", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // nothing to do here
            }
        });

        alert.show();

    }
}