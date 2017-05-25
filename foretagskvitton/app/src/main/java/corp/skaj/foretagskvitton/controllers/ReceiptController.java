package corp.skaj.foretagskvitton.controllers;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.ArrayList;
import java.util.List;

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
import corp.skaj.foretagskvitton.view.AbstractFragment;
import corp.skaj.foretagskvitton.view.IReceipt;
import corp.skaj.foretagskvitton.view.ILinkReceiptListener;
import corp.skaj.foretagskvitton.view.ReceiptFragment;

public class ReceiptController implements ILinkReceiptListener {
    private ReceiptFragment fragment;
    private String purchaseId;
    private IData dataHandler;
    private IReceipt mListener;

    public ReceiptController(IData dataHandler, String purId, ReceiptFragment fragment, IReceipt listener) {
        this.dataHandler = dataHandler;
        purchaseId = purId;
        this.fragment = fragment;
        mListener = listener;
    }

    public void updateReceiptData() {
        User user = dataHandler.getUser();
        Purchase purchase = dataHandler.getPurchases().getPurchase(purchaseId);
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

    @Override
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

    @Override
    public void bindImage(ImageView clickableImage, final Uri URI) {
        clickableImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.setImagePressed(URI);
            }
        });
    }

    @Override
    public void bindSpinner(final Spinner spinnerToBind, final AbstractFragment af, final Spinner changingSpinner) {
        spinnerToBind.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Company c = dataHandler.getUser().getCompany(spinnerToBind.getAdapter().getItem(position).toString());
                List<String> employeeNames = new ArrayList<String>();

                for (Employee e : c.getEmployees()) {
                    employeeNames.add(e.getName());
                }

                ArrayAdapter<String> employeeAdapter = af.buildArrayAdapter(employeeNames);
                af.setArrayAdapter(employeeAdapter, changingSpinner);

                //If we want a standard user, fix code below
                //Employee e = getUser().getCompanies().get(position).getEmployee(mPurchase);
                //mEmployees.setSelection(getEmployees().indexOf(e));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Empty on purpose
            }
        });
    }
}