package corp.skaj.foretagskvitton.view;

import android.graphics.Color;

import com.amulyakhare.textdrawable.TextDrawable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.text.SimpleDateFormat;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.Category;
import corp.skaj.foretagskvitton.model.IData;
import corp.skaj.foretagskvitton.model.Purchase;
import corp.skaj.foretagskvitton.model.PurchaseList;
import corp.skaj.foretagskvitton.model.User;

public class ArchiveAdapter extends BaseQuickAdapter<Purchase, BaseViewHolder> {
    private IData dataHandler;

    public ArchiveAdapter(int layoutId, PurchaseList purchases, IData dataHandler) {
        super(layoutId, purchases);
        this.dataHandler = dataHandler;
    }

    @Override
    protected void convert(BaseViewHolder helper, Purchase item) {

        User user = dataHandler.readData(User.class.getName(), User.class);
        String companyName = user.getCompany(item).getName();
        Category category = item.getReceipt().getProducts().get(0).getCategory();
        int nComments = item.getComments().size();
        double total = item.getReceipt().getTotal();

        String fLetterCat = category.name().substring(0, 1);
        TextDrawable circleImage = TextDrawable.builder()
                .buildRound(fLetterCat, Color.parseColor(category.getColor()));

        helper.setText(R.id.list_item_archive_company_name, companyName)
                .setText(R.id.list_item_archive_total, String.valueOf(total))
                .setText(R.id.list_item_archive_num_of_comments, String.valueOf(nComments))
                .setImageDrawable(R.id.list_item_archive_circular_image, circleImage);

    }
}
