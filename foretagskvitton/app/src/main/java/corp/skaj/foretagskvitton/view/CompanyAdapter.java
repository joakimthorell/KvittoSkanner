package corp.skaj.foretagskvitton.view;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.Company;

public class CompanyAdapter extends BaseQuickAdapter<Company, BaseViewHolder> {


    public CompanyAdapter (int layoutResId, List<Company> companies) {
        super(layoutResId, companies);
    }
    @Override
    protected void convert(BaseViewHolder helper, Company item) {
        helper.setText(R.id.company_textview, item.getName());

    }

}
