package corp.skaj.foretagskvitton.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.List;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.Category;
import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.Employee;
import corp.skaj.foretagskvitton.model.User;

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
        User user = getUser();
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.archive_toolbar, menu);
        
        //Fills the toolbar dynamically with..

        //Companies
        Menu m = menu.getItem(2).getSubMenu();
        List<Company> comps = user.getCompanies();
        for (int i = 0; i < comps.size(); i++) {
            m.getItem(2).getSubMenu().add(comps.get(i).getName());
        }

        //Employees
        for (int i = 0; i < comps.size(); i++) {
            for(int j = 0; j < comps.get(i).getEmployees().size(); j++) {
               m.getItem(3).getSubMenu().add(comps.get(j).getEmployees().get(j).getName());
            }
        }
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
                mAdapter.showCategory(Category.TRANSPORT);
                return true;
            case R.id.show_propellants:
                mAdapter.showCategory(Category.BENSIN);
                return true;
            case R.id.show_hotel:
                mAdapter.showCategory(Category.HOTELL);
                return true;
            case R.id.show_officeSupplies:
                mAdapter.showCategory(Category.KONTORSMATERIAL);
                return true;
            case R.id.show_postage:
                mAdapter.showCategory(Category.PORTO);
                return true;
            case R.id.show_representation:
                mAdapter.showCategory(Category.REPRESENTATION);
                return true;
            case R.id.show_travel:
                mAdapter.showCategory(Category.RESOR);
                return true;
            case R.id.show_food:
                mAdapter.showCategory(Category.MAT);
                return true;
            default:
                return false;
        }
    }

    public void fillCompanyMenu(Menu menu){
    }

    public ArchiveAdapter getAdapter() {
        return mAdapter;
    }

    protected BaseQuickAdapter getBaseAdapter() {
        return getAdapter();
    }
}