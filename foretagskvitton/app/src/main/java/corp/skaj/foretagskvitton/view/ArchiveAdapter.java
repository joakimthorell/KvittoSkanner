package corp.skaj.foretagskvitton.view;

import android.graphics.Color;

import com.amulyakhare.textdrawable.TextDrawable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.Collections;

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

    private User getUser() {
        return dataHandler.readData(User.class.getName(), User.class);
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

    public void sortListByCategoryAccending(){

    }

    public void sortListByDateDecending(){

    }

    public void sortListByDateAccending(){

    }

    public void showAll() {
        PurchaseList purchases = dataHandler.getPurchases(getUser());
        setNewData(purchases);
    }

    public void set(Category c){
        PurchaseList purchases = dataHandler.getPurchases(getUser()).getPurchases(c);
        setNewData(purchases);
    }

    public void showTransport() {
        Category c = Category.TRANSPORT;
        set(c);
    }

    public void showPropellant(){
        Category c = Category.BENSIN;
        set(c);
    }

    public void showHotell(){
        Category c = Category.HOTELL;
        set(c);
    }

    public void showOfficeSupplies(){
        Category c = Category.KONTORSMATERIAL;
        set(c);
    }

    public void showFood(){
        Category c = Category.MAT;
        set(c);
    }

    public void showPostage(){
        Category c = Category.PORTO;
        set(c);
    }

    public void showRepresentation(){
        Category c = Category.REPRESENTATION;
        set(c);
    }

    public void showTravels(){
        Category c = Category.RESOR;
        set(c);
    }

}
