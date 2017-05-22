package corp.skaj.foretagskvitton.controllers;

import corp.skaj.foretagskvitton.model.Category;
import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.IData;
import corp.skaj.foretagskvitton.model.Purchase;
import corp.skaj.foretagskvitton.model.PurchaseList;
import corp.skaj.foretagskvitton.model.Supplier;
import corp.skaj.foretagskvitton.model.User;
import corp.skaj.foretagskvitton.view.ArchiveFragment;

public class ArchiveController {
    private Purchase mPur;
    private PurchaseList purchaseList;
    private User user;
    private ArchiveFragment archiveFragment;
    private IData handler;

    public ArchiveController(IData dataHandler, String purId, ArchiveFragment archiveFragment) {
        this.mPur = purchaseList.getPurchase(purId);
        this.user = dataHandler.readData(User.class.getName(), User.class);
        this.archiveFragment = archiveFragment;
        this.handler = dataHandler;
    }

    private String purchaseType() {
        if (mPur.getPurchaseType() == mPur.getPurchaseType().PRIVATE) {
            return "Privatkort";
        }
        return "Företagskort";
    }

    public void updateReceiptData() {
        //Sets new..  

        // cost 
        mPur.getReceipt().setTotal(archiveFragment.getCost());

        // category 
        mPur.getReceipt().getProducts().get(0).setCategory(Category.valueOf(archiveFragment.getCategory().toUpperCase()));

        //tax 
        mPur.getReceipt().getProducts().get(0).setTax(archiveFragment.getTax());

        //Supplier
        Supplier updatedSupplier = new Supplier(archiveFragment.getSupplier());
        mPur.setSupplier(updatedSupplier);

        //payment method 
        mPur.setPurchaseType(selectCorrectPurchase());
        // company 
        Company updatedCompany = new Company(archiveFragment.getCompany());
        user.addCompany(updatedCompany);
        // Saves all changes 
        handler.writeData(User.class.getName(), user);
    }


    private Purchase.PurchaseType selectCorrectPurchase(){
        if (archiveFragment.getSupplier().equals("Företagskort")) {
            return Purchase.PurchaseType.COMPANY;
    }
        return Purchase.PurchaseType.PRIVATE;
    }
}