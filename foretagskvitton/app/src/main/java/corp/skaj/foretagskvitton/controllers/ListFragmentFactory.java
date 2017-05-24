package corp.skaj.foretagskvitton.controllers;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.afollestad.materialcab.MaterialCab;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.IData;
import corp.skaj.foretagskvitton.model.PurchaseList;
import corp.skaj.foretagskvitton.model.Supplier;
import corp.skaj.foretagskvitton.view.ArchiveAdapter;
import corp.skaj.foretagskvitton.view.ArchiveListFragment;
import corp.skaj.foretagskvitton.view.CompanyAdapter;
import corp.skaj.foretagskvitton.view.CompanyListFragment;
import corp.skaj.foretagskvitton.view.SupplierAdapter;
import corp.skaj.foretagskvitton.view.SupplierListFragment;

public class ListFragmentFactory {
    private Context mContext;
    private Class<?> mArchiveActivity;
    private Class<?> mSupplierActivity;
    private Class<?> mCompanyActivity;

    public ListFragmentFactory(Context context,
                               Class<?> archiveActivity,
                               Class<?> supplierActivity,
                               Class<?> companyActivity) {
        mContext = context;
        mArchiveActivity = archiveActivity;
        mSupplierActivity = supplierActivity;
        mCompanyActivity = companyActivity;
    }

    public ArchiveListFragment createArchiveList(PurchaseList purchases, IData dataHandler) {
        ArchiveAdapter aa = new ArchiveAdapter(R.layout.archive_list_item, purchases, dataHandler);
        ArchiveToolbarController atc = new ArchiveToolbarController(mContext, aa);
        atc.setListener(aa);


        ArchiveFABController fabController = new ArchiveFABController(mContext, mArchiveActivity);
        ArchiveListFragment fragment = ArchiveListFragment.create(aa, fabController);
        return fragment;
    }

    public CompanyListFragment createCompanyList(List<Company> companyList) {
        CompanyAdapter ca = new CompanyAdapter(R.layout.archive_list_item, companyList);
        CompanyFABController fabController = new CompanyFABController(mContext, mCompanyActivity);
        CompanyListFragment fragment = CompanyListFragment.create(ca, fabController);
        return fragment;
    }

    public SupplierListFragment createSupplierList(List<Supplier> suppliers) {
        SupplierAdapter sa = new SupplierAdapter(R.layout.archive_list_item, suppliers);
        SupplierFABController fabController = new SupplierFABController(mContext, mSupplierActivity);
        SupplierListFragment fragment = SupplierListFragment.create(sa, fabController);
        return fragment;
    }
}