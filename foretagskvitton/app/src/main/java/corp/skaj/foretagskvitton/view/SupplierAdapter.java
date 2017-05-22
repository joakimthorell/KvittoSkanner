package corp.skaj.foretagskvitton.view;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.Supplier;

public class SupplierAdapter extends BaseQuickAdapter<Supplier, BaseViewHolder> {

    public SupplierAdapter(int layoutResId, List<Supplier> suppliers) {
        super(layoutResId, suppliers);
    }

    @Override
    protected void convert(BaseViewHolder helper, Supplier supplier) {

        helper.setText(R.id.list_item_archive_company_name, supplier.getName());
    }
}