package corp.skaj.foretagskvitton.view;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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
public class ArchiveListFragment extends ListFragment {

    private ArchiveAdapter mAdapter;
    private IArchive mArchiveBinder;

    public ArchiveListFragment() {
        super();
    }

    public static ArchiveListFragment create(ArchiveAdapter adapter, IArchive binder) {
        ArchiveListFragment fragment = new ArchiveListFragment();
        fragment.setAdapter(adapter);
        fragment.setBinder(binder);
        return fragment;
    }

    public static ArchiveListFragment create(ArchiveAdapter adapter, IFAB listener, IArchive binder) {
        ArchiveListFragment fragment = create(adapter, binder);
        fragment.setListener(listener);
        return fragment;
    }

    private void setAdapter(ArchiveAdapter adapter) {
        this.mAdapter = adapter;
    }

    private void setBinder(IArchive binder) {
        mArchiveBinder = binder;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        User user = getUser();
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.archive_toolbar, menu);

        //Fills the toolbar dynamically with company & employee
        Menu subMenu = menu.getItem(2).getSubMenu();
        List<Company> companies = user.getCompanies();
        for (Company c : companies) {
            String companyName = c.getName();
            mArchiveBinder.bindCompanyMenuItem(createCompanyItem(subMenu, companyName), c);
            for (Employee e : c.getEmployees()) {
                String employeeName = e.getName() + " - " + c.getName();
                mArchiveBinder.bindEmployeeMenuItem(createEmployeeItem(subMenu, employeeName), e);
            }
        }
    }

    private MenuItem createCompanyItem(Menu menu, String companyName) {
        return menu.getItem(2).getSubMenu().add(companyName);
    }

    private MenuItem createEmployeeItem(Menu menu, String employeeName) {
        return menu.getItem(3).getSubMenu().add(employeeName);
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
            case R.id.show_representation:
                mAdapter.showCategory(Category.REPRESENTATION);
                return true;
            case R.id.show_travel:
                mAdapter.showCategory(Category.RESOR);
                return true;
            case R.id.show_food:
                mAdapter.showCategory(Category.MAT);
                return true;
            case R.id.show_other:
                mAdapter.showCategory(Category.ÖVRIGT);
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