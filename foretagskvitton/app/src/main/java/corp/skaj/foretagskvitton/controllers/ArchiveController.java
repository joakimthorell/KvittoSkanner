package corp.skaj.foretagskvitton.controllers;

import android.content.Context;
import android.content.Intent;
import android.widget.Button;

import corp.skaj.foretagskvitton.model.IData;
import corp.skaj.foretagskvitton.model.Purchase;
import corp.skaj.foretagskvitton.model.PurchaseList;
import corp.skaj.foretagskvitton.model.User;

public class ArchiveController<T> {
    public static final String RECEIPT_ID = "RECEIPT_ID";
    private Context mContext;
    private Class<T> mNextActivityToStart;
    private Purchase mPur;
    private PurchaseList purchaseList;
    private User user;

    public ArchiveController(IData dataHandler, String purId, Button saveButton ) {
        this.mPur = purchaseList.getPurchase(purId);
        this.user = dataHandler.readData(User.class.getName(),User.class);
    }

    public void onItemClicked(String itemId) {
        Intent intent = new Intent(mContext, mNextActivityToStart);
        intent.putExtra(RECEIPT_ID, itemId);
        mContext.startActivity(intent);
    }

    private String checkSupplier(){
        try {
            mPur.getSupplier().getName();
        } catch(NullPointerException e) {
            return "Supplier not specified";
        }
        return mPur.getSupplier().getName();
    }

    private String  purchaseType() {
        if (mPur.getPurchaseType() == mPur.getPurchaseType().PRIVATE) {
            return "Privatkort";
        }
        return "Företagskort";
    }

    public void onCommentClick (View view){
        Intent intent = new Intent(this, ArchiveReceiptComments.class);
        intent.putExtra(COMMENT_ID, mPur.getId());
        startActivity(intent);
    }

    public void onReceiptClick(View view){
        Intent intent = new Intent(this, ArchiveReceiptPicture.class);
        intent.putExtra("image", mPur.getReceipt().getPictureAdress());
        startActivity(intent);
    }

    public void updateReceiptData(){ 
        //Sets new..  

        // cost 
        mPur.getReceipt().setTotal(Double.valueOf(String.valueOf(g)));  

        // category 
        mPur.getReceipt().getProducts().get(0).setCategory(getCorrectCategory());  

        //tax 
        // mPur.getReceipt().getProducts().get(0).setTax(Double.valueOf(String.valueOf(tax.getText())));  

        // date TODO - Get calender pop-up for correct entries  
        Calendar newCalendar = Calendar.getInstance();  
        mDatePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener()
        {  
            @Override 
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) { 
                Calendar newDate = Calendar.getInstance(); 
                newDate.set(year, monthOfYear, dayOfMonth); 
                String formattedDate = DatePage.dateFormatter.format(newDate.getTime());  
                date.setText(formattedDate); 
                mPage.getData().putString(DatePage.SIMPLE_DATA_KEY, formattedDate); 
                mPage.notifyDataChanged(); 
        }  

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));  

        //Supplier - WHY BOOLEAN? 
        mPur.setSupplier(user.addSupplier(new Supplier("hej"))));  

        //payment method 
        mPur.setPurchaseType(setPurchaseType());  

        // company 
        user.addCompany(new Company(company.getSelectedItem().toString()));  

        // Saves all changes 
        handler.writeData(User.class.getName(), user); 
    }  

        // Needed because of how the spinner works. 
        private Category getCorrectCategory(){ 
        Category[] catStrings = Category.categoriesInArr(); 
        return catStrings[category.getSelectedItemPosition()]; 
        }  


}