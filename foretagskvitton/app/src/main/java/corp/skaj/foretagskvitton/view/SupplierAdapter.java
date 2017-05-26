package corp.skaj.foretagskvitton.view;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
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
        String fLetter = supplier.getName().substring(0, 2);
        TextDrawable circleImage = TextDrawable.builder()
                .buildRoundRect(fLetter, ColorGenerator.MATERIAL.getRandomColor(), 10);

        helper.setText(R.id.list_item_supplier_company_name, supplier.getName())
        .setImageDrawable(R.id.list_item_supplier_circular_image, circleImage)
        .addOnClickListener(R.id.list_item_supplier_circular_image);
    }
}