package corp.skaj.foretagskvitton.activities;

import android.content.Context;

import java.util.List;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.controllers.ArchiveListFABController;
import corp.skaj.foretagskvitton.controllers.ArchiveToolbarController;
import corp.skaj.foretagskvitton.controllers.CompanyListFABController;
import corp.skaj.foretagskvitton.controllers.SupplierListFABController;
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

    private ListFragmentFactory() {
    }

    public static ArchiveListFragment createArchiveList(Class<?> archiveActivity, Context context, PurchaseList purchases, IData dataHandler) {
        ArchiveAdapter aa = new ArchiveAdapter(R.layout.archive_list_item, purchases, dataHandler);
        ArchiveToolbarController atc = new ArchiveToolbarController(context, aa);
        atc.setListener(aa);
        ArchiveListFABController fabController = new ArchiveListFABController(context, archiveActivity);
        ArchiveListFragment fragment = ArchiveListFragment.create(aa, fabController, atc);
        return fragment;
    }

    public static CompanyListFragment createCompanyList(Context context, List<Company> companyList) {
        CompanyAdapter ca = new CompanyAdapter(R.layout.archive_list_item, companyList);
        CompanyListFABController fabController = new CompanyListFABController(context);
        CompanyListFragment fragment = CompanyListFragment.create(ca, fabController);
        return fragment;
    }

    public static SupplierListFragment createSupplierList(Context context, List<Supplier> suppliers) {
        SupplierAdapter sa = new SupplierAdapter(R.layout.archive_list_item, suppliers);
        SupplierListFABController fabController = new SupplierListFABController(context);
        SupplierListFragment fragment = SupplierListFragment.create(sa, fabController);
        return fragment;
    }
}