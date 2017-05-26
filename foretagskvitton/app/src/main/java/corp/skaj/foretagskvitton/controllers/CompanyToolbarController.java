package corp.skaj.foretagskvitton.controllers;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.afollestad.materialcab.MaterialCab;
import com.chad.library.adapter.base.BaseQuickAdapter;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.User;
import corp.skaj.foretagskvitton.view.CompanyAdapter;
import corp.skaj.foretagskvitton.view.CompanyListFragment;

public class CompanyToolbarController extends ToolbarController<Company> {

    private IActivity mListener;
    private CompanyListFragment mFragment;

    public CompanyToolbarController(Context context, CompanyAdapter adapter, IActivity listener, CompanyListFragment fragment) {
        super(context,
                new MaterialCab((AppCompatActivity)context, R.id.cab_stub)
        .setBackgroundColorRes(R.color.colorPrimary)
        .setMenu(R.menu.remove_menu));

        mFragment = fragment;
        mListener = listener;
        setListener(adapter);
    }

    @Override
    public boolean onCabItemClicked(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_remove:
                if (anyCompanyLeft()) {
                    removeSelectedItems();
                    mListener.reloadUI(mFragment);
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
