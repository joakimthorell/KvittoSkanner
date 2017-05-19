package corp.skaj.foretagskvitton.view;


import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.MultipleItem;

public class CompanyMutlipleItemAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {

    public CompanyMutlipleItemAdapter(List<MultipleItem> data) {
        super(data);
        int ll = R.layout.archive_list_item;
        addItemType(MultipleItem.CARD, ll);
        addItemType(MultipleItem.EMPLOYEE, ll);
        addItemType(MultipleItem.COMMENT, ll);

    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
        int id = R.id.ArchiveTitle;
        helper.setText(id, item.getContent());
    }
}
