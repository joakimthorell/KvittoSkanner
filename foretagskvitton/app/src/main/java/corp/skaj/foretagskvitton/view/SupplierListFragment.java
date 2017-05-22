package corp.skaj.foretagskvitton.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import corp.skaj.foretagskvitton.R;

/**
 * A simple {@link Fragment} subclass.
 * This fragment is showing a {@link RecyclerView} including a
 * {@link FloatingActionsMenu} that can either be used as a direct button
 * or add more buttons into it.
 */
public class SupplierListFragment extends ListFragment {

    private SupplierAdapter mAdapter;

    public SupplierListFragment() {
        super();
    }

    public static SupplierListFragment create(SupplierAdapter adapter) {
        SupplierListFragment fragment = new SupplierListFragment();
        fragment.setAdapter(adapter);
        return fragment;
    }

    public static SupplierListFragment create(SupplierAdapter adapter, Callback listener) {
        SupplierListFragment fragment = create(adapter);
        fragment.setListener(listener);
        return fragment;
    }

    private void setAdapter(SupplierAdapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public SupplierAdapter getAdapter() {
        return mAdapter;
    }

    protected BaseQuickAdapter getBaseAdapter() {
        return getAdapter();
    }
}