package corp.skaj.foretagskvitton.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import corp.skaj.foretagskvitton.R;

/**
 * A simple {@link Fragment} subclass.
 * This fragment is showing a {@link RecyclerView} including a
 * {@link FloatingActionsMenu} that can either be used as a direct button
 * or add more buttons into it.
 */
public class ArchiveListFragment extends ListFragment{

    private ArchiveAdapter mAdapter;

    public ArchiveListFragment() {
        super();
    }

    public static ArchiveListFragment create(ArchiveAdapter adapter) {
        ArchiveListFragment fragment = new ArchiveListFragment();
        fragment.setAdapter(adapter);
        return fragment;
    }

    public static ArchiveListFragment create(ArchiveAdapter adapter, FABCallback listener) {
        ArchiveListFragment fragment = create(adapter);
        fragment.setListener(listener);
        return fragment;
    }

    private void setAdapter(ArchiveAdapter adapter) {
        this.mAdapter = adapter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.archive_toolbar, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sort_date_newest_first:
                mAdapter.sortListByDateNewstFirst();
                return true;
            case R.id.action_sort_date_oldest_first:
                mAdapter.sortListByDateOldestFirst();
                return true;
            case R.id.action_sort_price_down:
                mAdapter.sortListByPriceDecending();
                return true;
            case R.id.action_sort_price_up:
                mAdapter.sortListByPriceAcceding();
                return true;
            case R.id.show_all:
                mAdapter.showAll();
                return true;
            case R.id.show_transport:
                mAdapter.showTransport();
                return true;
            case R.id.show_propellants:
                mAdapter.showPropellant();
                return true;
            case R.id.show_hotel:
                mAdapter.showHotell();
                return true;
            case R.id.show_officeSupplies:
                mAdapter.showOfficeSupplies();
                return true;
            case R.id.show_postage:
                mAdapter.showPostage();
                return true;
            case R.id.show_representation:
                mAdapter.showRepresentation();
                return true;
            case R.id.show_travel:
                mAdapter.showTravels();
                return true;
            case R.id.show_food:
                mAdapter.showFood();
                return true;
            default:
                return false;
        }
    }

    public ArchiveAdapter getAdapter() {
        return mAdapter;
    }

    protected BaseQuickAdapter getBaseAdapter() {
        return getAdapter();
    }
}