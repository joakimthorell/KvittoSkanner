package corp.skaj.foretagskvitton.controllers;

import android.content.Context;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialcab.MaterialCab;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.HashMap;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.IData;
import corp.skaj.foretagskvitton.model.User;

public abstract class ToolbarController<T> implements MaterialCab.Callback {
    private SparseArray<View> selectedItems;
    private MaterialCab mc;
    private Context mContext;
    private BaseQuickAdapter<T, BaseViewHolder> mAdapter;

    protected ToolbarController(Context context, MaterialCab mc) {
        mContext = context;
        this.mc = mc;
        selectedItems = new SparseArray<>();
    }

    public void setListener(BaseQuickAdapter<T, BaseViewHolder> adapter) {
        mAdapter = adapter;
        setListenerOnAdapter();
    }

    private void setListenerOnAdapter() {
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                toggleItem(((View) view.getParent()), position);
                if (selectedItems.size() > 0) {
                    mc.start(ToolbarController.this);
                } else {
                    mc.finish();
                }
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
    public abstract boolean onCabItemClicked(MenuItem item);

    @Override
    public boolean onCabFinished(MaterialCab cab) {
        for (int i = 0; i < selectedItems.size(); i++) {
            Integer key = selectedItems.indexOfKey(i);
            View view = selectedItems.get(key);
            setBackgroundColor(view, true);
        }
        selectedItems.clear();
        return true;
    }

    /**
     * If selected true, changes to white background.
     *
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
        return ((IData) mContext.getApplicationContext());
    }

    protected void removeSelectedItems() {
        User user = getDataHandler().getUser();
        for (int i = selectedItems.size() - 1; i >= 0; i--) {
            Integer key = selectedItems.keyAt(i);
            T object = mAdapter.getData().get(key);
            removeItemFromUser(object, user);
            setBackgroundColor(selectedItems.get(key), true);
            mAdapter.remove(key);
        }
        selectedItems.clear();
        mc.finish();
        getDataHandler().saveUser();
    }

    protected abstract void removeItemFromUser(T object, User user);


}
