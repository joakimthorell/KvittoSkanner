package corp.skaj.foretagskvitton.controllers;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.afollestad.materialcab.MaterialCab;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.User;
import corp.skaj.foretagskvitton.view.CompanyAdapter;

public class CompanyToolbarController extends AbstractToolbarController<Company> {

    public CompanyToolbarController(Context context, CompanyAdapter adapter) {
        super(context,
                new MaterialCab((AppCompatActivity)context, R.id.cab_stub)
        .setBackgroundColorRes(R.color.colorPrimary)
        .setMenu(R.menu.remove_menu));

        setListener(adapter);
    }

    @Override
    public boolean onCabItemClicked(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_remove:
                if (anyCompanyLeft()) {
                    removeSelectedItems();
                    return true;
                }
                Toast.makeText(getContext(), getContext().getString(R.string.specific_cant_remove_company), Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }
    }

    @Override
    protected void removeItemFromUser(Company object, User user) {
        user.removeCompany(object);
    }

    private boolean anyCompanyLeft() {
        int numberOfCompanies = getDataHandler().getUser().getCompanies().size();
        if (numberOfCompanies <= getSizeSelectedItems()) {
            return false;
        }
        return true;
    }
}
