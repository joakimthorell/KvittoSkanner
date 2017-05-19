package corp.skaj.foretagskvitton.view;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.text.SimpleDateFormat;

import corp.skaj.foretagskvitton.R;
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
        helper.setText(R.id.ArchiveTitle, user.getCompany(item).getName());
        helper.setText(R.id.ArchiveCategory, item.getReceipt().getProducts().get(0).getCategory().name());
        SimpleDateFormat dateRaw = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateRaw.format(item.getReceipt().getDate().getTime());
        helper.setText(R.id.ArchiveCreationDate, date);
    }
}
