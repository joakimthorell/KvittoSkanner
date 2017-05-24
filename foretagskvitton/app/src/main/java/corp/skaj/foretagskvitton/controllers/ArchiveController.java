package corp.skaj.foretagskvitton.controllers;

import android.content.Intent;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionsMenu;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.activities.MainActivity;
import corp.skaj.foretagskvitton.model.Category;
import corp.skaj.foretagskvitton.model.Comment;
import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.Employee;
import corp.skaj.foretagskvitton.model.IData;
import corp.skaj.foretagskvitton.model.Purchase;
import corp.skaj.foretagskvitton.model.Supplier;
import corp.skaj.foretagskvitton.model.User;
import corp.skaj.foretagskvitton.view.ArchiveFragment;
import corp.skaj.foretagskvitton.view.FABCallback;

public class ArchiveController implements FABCallback {
    private ArchiveFragment fragment;
    private String purchaseId;
    private IData dataHandler;

    public ArchiveController(IData dataHandler, String purId, ArchiveFragment fragment) {
        this.dataHandler = dataHandler;
        purchaseId = purId;
        this.fragment = fragment;
    }

    public void updateReceiptData() {
        User user = dataHandler.getUser();
        Purchase purchase = dataHandler.getPurchases(user).getPurchase(purchaseId);
        // price 
        purchase.getReceipt().setTotal(fragment.getPrice());
        // category 
        purchase.getReceipt().getProducts().get(0).setCategory(Category.valueOf(fragment.getCategory().toUpperCase()));
        // tax 
        purchase.getReceipt().getProducts().get(0).setTax(fragment.getTax());
        // supplier
        Supplier updatedSupplier = user.getSupplier(fragment.getSupplier());
        purchase.setSupplier(updatedSupplier);
        // payment method 
        purchase.setPurchaseType(selectCorrectPurchase());
        // company 
        Company updatedCompany = user.getCompany(fragment.getCompany());
        //user.addCo mpany(updatedCompany);
        //comments
        if (purchase.getComments().size() < 1) {
            if (fragment.getComment().length() > 0) {
                Comment c = new Comment(fragment.getComment());
                purchase.addComment(c);
            }
        } else {
            purchase.getComments().get(0).setComment(fragment.getComment());
        }
        //date
        /*
        DateFormat newDate = new SimpleDateFormat("yyyy-mm-dd");
        fragment.getDate();
*/
        Employee oldPurchaseOwner = user.getCompany(purchase).getEmployee(purchase);
        Employee newPurchaseOwner = updatedCompany.getEmployee(fragment.getEmployee());

        newPurchaseOwner.addPurchase(purchase);
        oldPurchaseOwner.removePurchase(purchase);
        user.getCompany(purchase).removeEmployee(oldPurchaseOwner);

        dataHandler.saveUser();
    }

    private Purchase.PurchaseType selectCorrectPurchase() {
        return fragment.getPurchaseType().equals("Företagskort") ? Purchase.PurchaseType.COMPANY : Purchase.PurchaseType.PRIVATE;
    }

    public void bindButton(final FloatingActionsMenu button) {
        button.setOnFloatingActionsMenuUpdateListener(new FloatingActionsMenu.OnFloatingActionsMenuUpdateListener() {
            @Override
            public void onMenuExpanded() {
                updateReceiptData();
                button.collapse();
                Toast.makeText(fragment.getContext(), fragment.getContext().getString(R.string.archive_save), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(fragment.getContext(), MainActivity.class);
                fragment.getContext().startActivity(intent);
            }
            @Override
            public void onMenuCollapsed() {

            }
        });
    }
}