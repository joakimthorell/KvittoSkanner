package corp.skaj.foretagskvitton.view;


import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.Employee;

public class CompanyAdapter extends BaseQuickAdapter<Company, BaseViewHolder> {


    public CompanyAdapter(int layoutResId, List<Company> companies) {
        super(layoutResId, companies);
    }

    @Override
    protected void convert(BaseViewHolder helper, Company item) {

        TextDrawable image = TextDrawable.builder()
                .buildRect(item.getName().substring(0, 1), ColorGenerator.MATERIAL.getRandomColor());

        helper.setText(R.id.list_item_company_company_name, item.getName())
                .setText(R.id.list_item_company_num_of_purchases, String.valueOf(numOfPurchases(item)))
                .setImageDrawable(R.id.list_item_company_circular_image, image)
                .addOnClickListener(R.id.list_item_company_circular_image);


    }

    private int numOfPurchases(Company company) {
        int i = 0;
        for (Employee e : company.getEmployees()) {
            i += e.getPurchases().size();
        }
        return i;
    }

}
