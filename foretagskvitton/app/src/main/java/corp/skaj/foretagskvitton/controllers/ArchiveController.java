package corp.skaj.foretagskvitton.controllers;

import android.support.design.widget.FloatingActionButton;
import android.view.View;

import corp.skaj.foretagskvitton.model.Category;
import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.IData;
import corp.skaj.foretagskvitton.model.Purchase;
import corp.skaj.foretagskvitton.model.Supplier;
import corp.skaj.foretagskvitton.model.User;
import corp.skaj.foretagskvitton.view.ArchiveFragment;

public class ArchiveController {
    private ArchiveFragment fragment;
    private String purchaseId;
    private IData dataHandler;

    public ArchiveController(IData dataHandler, String purId, ArchiveFragment fragment) {
        this.dataHandler = dataHandler;
        purchaseId = purId;
        this.fragment = fragment;
    }

    public void setListerOnSaveButton(FloatingActionButton saveButton){
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("This works ");

                //TODO Save edited data

                //updateReceiptData();
            }
        });
    }

    public void updateReceiptData() {
        User user = dataHandler.readData(User.class.getName(), User.class);
        Purchase purchase = dataHandler.getPurchases(user).getPurchase(purchaseId);
        // price 
        purchase.getReceipt().setTotal(fragment.getPrice());
        // category 
        purchase.getReceipt().getProducts().get(0).setCategory(Category.valueOf(fragment.getCategory().toUpperCase()));
        // tax 
        purchase.getReceipt().getProducts().get(0).setTax(fragment.getTax());
        // supplier
        Supplier updatedSupplier = new Supplier(fragment.getSupplier(purchaseId));
        purchase.setSupplier(updatedSupplier);
        // payment method 
        purchase.setPurchaseType(selectCorrectPurchase());
        // company 
        Company updatedCompany = new Company(fragment.getCompany());
        user.addCompany(updatedCompany);
        // saves all changes 
        dataHandler.writeData(User.class.getName(), user);
    }

    private Purchase.PurchaseType selectCorrectPurchase() {
        return fragment.getPurchaseType().equals("Företagskort") ? Purchase.PurchaseType.COMPANY : Purchase.PurchaseType.PRIVATE;
    }
}