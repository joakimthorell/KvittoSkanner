package corp.skaj.foretagskvitton.controllers;

import android.content.Context;
import android.view.MenuItem;

import com.afollestad.materialcab.MaterialCab;

import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.User;

public class CompanyToolbarController extends ToolbarController<Company> {

    public CompanyToolbarController(Context context, MaterialCab mc) {
        super(context, mc);
    }

    @Override
    public boolean onCabItemClicked(MenuItem item) {
        return false;
    }

    @Override
    protected void removeItemFromUser(Company object, User user) {

    }
}
