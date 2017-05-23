package corp.skaj.foretagskvitton.controllers;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.afollestad.materialcab.MaterialCab;
import com.chad.library.adapter.base.BaseQuickAdapter;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.Employee;
import corp.skaj.foretagskvitton.model.IData;
import corp.skaj.foretagskvitton.model.Purchase;
import corp.skaj.foretagskvitton.model.User;
import corp.skaj.foretagskvitton.view.ArchiveAdapter;

public class ArchiveToolbarController implements MaterialCab.Callback {
    private SparseArray<View> selectedItems;
    private MaterialCab mc;
    private Context mContext;
    private ArchiveAdapter mAdapter;

    public ArchiveToolbarController(ArchiveAdapter adapter, Context context) {
        mAdapter = adapter;
        mContext = context;
        mc = new MaterialCab((AppCompatActivity) context, R.id.cab_stub)
                .setBackgroundColorRes(R.color.colorPrimary)
                .setMenu(R.menu.cab_menu);
        selectedItems = new SparseArray<>();
    }

    public void setListener(ArchiveAdapter aadapter) {
        aadapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                toggleItem(((View) view.getParent()), position);
                mc.start(ArchiveToolbarController.this);
            }
        });
    }

    private void toggleItem(View view, Integer position) {
        if (selectedItems.indexOfKey(position) >= 0) {
            setBackgroundColor(view, true);
            selectedItems.remove(position);

        } else {
            selectedItems.put(position, view);
            setBackgroundColor(view, false);
        }
    }

    @Override
    public boolean onCabCreated(MaterialCab cab, Menu menu) {
        cab.setTitle(selectedItems.size() + "");
        return selectedItems.size() > 0;
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
    public boolean onCabFinished(MaterialCab cab) {
        for (int i = 0; i < selectedItems.size(); i++) {
            Integer key = selectedItems.keyAt(i);
            View view = selectedItems.get(key);
            setBackgroundColor(view, true);
        }
        selectedItems.clear();
        return true;
    }

    /**
     * If selected true, changes to white background.
     * @param view
     * @param selected
     */
    private void setBackgroundColor(View view, boolean selected) {
        if (selected) {
            view.setBackgroundColor(mContext.getResources().getColor(android.R.color.white, null));
            return;
        }
        view.setBackgroundColor(mContext.getResources().getColor(R.color.itemBackgroundSelected, null));
    }

    private IData getDataHandler() {
        return ((IData)mContext.getApplicationContext());
    }

    private void removeSelectedItems() {
        User user = getDataHandler().readData(User.class.getName(), User.class);
        for (int i = 0; i < selectedItems.size(); i++) {
            Integer key = selectedItems.keyAt(i);
            Purchase purchase = mAdapter.getData().get(key);
            Company company = user.getCompany(purchase);
            Employee employee = company.getEmployee(purchase);
            System.out.println("THIS IS EMPLYEE " + employee);
            employee.removePurchase(purchase);
            //user.getCompany(purchase).getEmployee(purchase).removePurchase(purchase);
            mAdapter.remove(key);
        }
        selectedItems.clear();
        //mc.finish();
        getDataHandler().writeData(User.class.getName(), user);
    }
}
