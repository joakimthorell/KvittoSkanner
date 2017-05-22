package corp.skaj.foretagskvitton.view;


import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.Company;

public class CompanyAdapter extends BaseQuickAdapter<Company, BaseViewHolder> {


    public CompanyAdapter(int layoutResId, List<Company> companies) {
        super(layoutResId, companies);
    }

    @Override
    protected void convert(BaseViewHolder helper, Company item) {

        TextDrawable image = TextDrawable.builder()
                .buildRect(item.getName().substring(0, 1), ColorGenerator.MATERIAL.getRandomColor());

        helper.setText(R.id.list_item_archive_company_name, item.getName())
                .setImageDrawable(R.id.list_item_archive_circular_image, image);


    }

}
