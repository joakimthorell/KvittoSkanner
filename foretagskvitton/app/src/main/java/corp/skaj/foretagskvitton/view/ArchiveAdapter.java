package corp.skaj.foretagskvitton.view;

import android.graphics.Color;

import com.amulyakhare.textdrawable.TextDrawable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.Collections;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.Category;
import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.Employee;
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
        User user = dataHandler.getUser();
        String companyName = user.getCompany(item) == null ? null : user.getCompany(item).getName();
        Category category = item.getReceipt().getProducts().get(0).getCategory();
        int nComments = item.getComments().size();
        double total = item.getReceipt().getTotal();

        String fLetterCat = category.name().substring(0, 1);
        TextDrawable circleImage = TextDrawable.builder()
                .buildRound(fLetterCat, Color.parseColor(category.getColor()));

        helper.setText(R.id.list_item_archive_company_name, companyName)
                .setText(R.id.list_item_archive_total, String.valueOf(total))
                .setText(R.id.list_item_archive_num_of_comments, String.valueOf(nComments))
                .setImageDrawable(R.id.list_item_archive_circular_image, circleImage)
                .addOnClickListener(R.id.list_item_archive_circular_image);
    }

    private User getUser() {
        return dataHandler.getUser();
    }

    public void sortListByPriceAcceding() {
        PurchaseList purchases = PurchaseList.convert(getData(), getUser());
        purchases.sortByPrice();
        setNewData(purchases);
    }

    public void sortListByPriceDecending() {
        PurchaseList purchases = PurchaseList.convert(getData(), getUser());
        purchases.sortByPrice();
        Collections.reverse(purchases);
        setNewData(purchases);
    }

    public void sortListByDateOldestFirst(){
        PurchaseList purchases = PurchaseList.convert(getData(), getUser());
        purchases.sortByDate();
        setNewData(purchases);
    }

    public void sortListByDateNewstFirst(){
        PurchaseList purchases = PurchaseList.convert(getData(), getUser());
        purchases.sortByDate();
        Collections.reverse(purchases);
        setNewData(purchases);
    }

    public void showAll() {
        PurchaseList purchases = dataHandler.getPurchases();
        setNewData(purchases);
    }

    public void showCategory(Category c){
        PurchaseList purchases = dataHandler.getPurchases().getPurchases(c);
        setNewData(purchases);
    }

    public void showCompany(Company c) {
        PurchaseList purchases = dataHandler.getPurchases().getPurchases(c);
        setNewData(purchases);
    }

    public void showEmployee(Employee e) {
        PurchaseList purchases = dataHandler.getPurchases().getPurchases(e);
        setNewData(purchases);
    }

}
