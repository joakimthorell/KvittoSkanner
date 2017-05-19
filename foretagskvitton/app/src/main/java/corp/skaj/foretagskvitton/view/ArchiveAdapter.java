package corp.skaj.foretagskvitton.view;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.text.SimpleDateFormat;
import java.util.List;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.Purchase;
import corp.skaj.foretagskvitton.model.PurchaseList;
import corp.skaj.foretagskvitton.model.User;

public class ArchiveAdapter extends BaseQuickAdapter<Purchase, BaseViewHolder> {
    private User user;

    public ArchiveAdapter(int layoutId, PurchaseList purchases, User user) {
        super(layoutId, purchases);
        this.user = user;
    }


    @Override
    protected void convert(BaseViewHolder helper, Purchase item) {
        helper.setText(R.id.ArchiveTitle, user.getCompany(item).getName());
        helper.setText(R.id.ArchiveCategory, item.getReceipt().getProducts().get(0).getCategory().name());

        SimpleDateFormat dateRaw = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateRaw.format(item.getReceipt().getDate().getTime());
        helper.setText(R.id.ArchiveCreationDate, date);
    }
}
