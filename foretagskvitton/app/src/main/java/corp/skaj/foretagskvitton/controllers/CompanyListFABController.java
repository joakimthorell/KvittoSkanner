package corp.skaj.foretagskvitton.controllers;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.List;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.Employee;
import corp.skaj.foretagskvitton.model.IData;
import corp.skaj.foretagskvitton.model.Supplier;
import corp.skaj.foretagskvitton.view.CompanyListFragment;
import corp.skaj.foretagskvitton.view.MultiDialog;
import corp.skaj.foretagskvitton.view.SupplierListFragment;

public class CompanyListFABController extends FABController
        implements MultiDialog.Callback {

    public CompanyListFABController(Context context) {
        super(context, null);
    }

    @Override
    public void bindButton(FloatingActionsMenu button) {
        bindFAB(button);
    }

    private void bindFAB(final FloatingActionsMenu button) {
        button.setOnFloatingActionsMenuUpdateListener(
                new FloatingActionsMenu.OnFloatingActionsMenuUpdateListener() {
                    @Override
                    public void onMenuExpanded() {

                        new MultiDialog(getContext(),
                                CompanyListFABController.this,
                                MultiDialog.Type.CREATER,
                                getContext().getString(R.string.text_company))
                                .newDialog().show();
                        button.collapse();
                    }

                    @Override
                    public void onMenuCollapsed() {

                    }
                }
        );

    }

    @Override
    public void dialogData(String newData, String oldData, Bundle extras) {
        if (newData != null && newData.length() > 0) {
            IData handler = ((IData) getContext().getApplicationContext());
            Company c = new Company(newData);
            Employee e = new Employee(handler.getUser().getName());
            c.addEmployee(e);
            handler.getUser().addCompany(c);
            handler.saveUser();

            updateCompanyList(handler);

            Toast.makeText(getContext(), getContext().getString(R.string.text_created) + " " + c.getName(), Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(getContext(), getContext().getString(R.string.text_not_saved), Toast.LENGTH_SHORT).show();
    }

    private void updateCompanyList(IData handler) {
        List<Company> companies = handler.getUser().getCompanies();
        AppCompatActivity activity = (AppCompatActivity) getContext();
        CompanyListFragment fragment = (CompanyListFragment) activity.getSupportFragmentManager().findFragmentById(R.id.main_fragment_container);
        fragment.getAdapter().setNewData(companies);
    }
}