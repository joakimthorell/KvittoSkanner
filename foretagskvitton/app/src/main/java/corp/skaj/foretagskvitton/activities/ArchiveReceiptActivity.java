package corp.skaj.foretagskvitton.activities;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.Category;
import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.IData;
import corp.skaj.foretagskvitton.model.Purchase;
import corp.skaj.foretagskvitton.model.PurchaseList;
import corp.skaj.foretagskvitton.model.Supplier;
import corp.skaj.foretagskvitton.model.User;

public class ArchiveReceiptActivity extends AbstractActivity {

    Purchase mPur;
    public static final String COMMENT_ID = "COMMENT_ID";
    public static final String PICTURE_ID = "PICTURE_ID";
    TextView cost;
    TextView tax;
    TextView date;
    TextView supplier;
    TextView payment_method;
    Spinner company;
    Spinner category;
    IData handler;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive_receipt);

        handler = getDataHandler();
        user = handler.readData(User.class.getName(), User.class);

        //User needed below to synchronize object pointers.
        PurchaseList list = handler.getPurchases(user);

        String purchaseId= getIntent().getStringExtra(MainActivity.ARCHIVE_KEY);
        mPur = list.getPurchase(purchaseId);

        cost = (TextView) findViewById(R.id.cost);
        tax = (TextView) findViewById(R.id.Moms);
        date = (TextView) findViewById(R.id.date);
        supplier = (TextView) findViewById(R.id.Supplier);
        payment_method = (TextView) findViewById(R.id.payment_method);

        // Cost and tax can only be numbers.
        cost.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        tax.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        //Category and company info is set with spinners to avoid incorrect input.
        category = (Spinner) findViewById(R.id.spinnerCategories);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,
                Category.getCategoriesArray());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(adapter);

        company = (Spinner) findViewById(R.id.spinnerCompany);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,
                user.getCompaniesArray());
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        company.setAdapter(arrayAdapter);

        //Fills the textviews
        cost.setText(String.valueOf(mPur.getReceipt().getTotal()));
        tax.setText(String.valueOf(+ mPur.getReceipt().getProducts().get(0).getTax()));
        supplier.setText(checkSupplier());
        payment_method.setText(purchaseType());

        //Gives date a correct format.
        SimpleDateFormat dateRaw = new SimpleDateFormat("yyyy-MM-dd");
        String receiptDate = dateRaw.format(mPur.getReceipt().getDate().getTime());

        date.setText(receiptDate);

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
        intent.putExtra(PICTURE_ID, mPur.getId());
        startActivity(intent);
    }

    public void onSaveClick(View view){
        //Sets new..

        //cost
        mPur.getReceipt().setTotal(Double.valueOf(String.valueOf(cost.getText())));

        //category
        mPur.getReceipt().getProducts().get(0).setCategory(getCorrectCategory());

        //tax
        mPur.getReceipt().getProducts().get(0).setTax(Double.valueOf(String.valueOf(tax.getText())));

        // date TODO - Get calender pop-up for correct entries

        //Supplier - WHY BOOLEAN?
        //mPur.setSupplier(user.addSupplier(new Supplier("hej"))));

        //payment method
        mPur.setPurchaseType(setPurchaseType());

        //company
        user.addCompany(new Company(company.getSelectedItem().toString()));

        //Saves all changes
        handler.writeData(User.class.getName(), user);
    }

    // Needed because of how the spinner works.
    private Category getCorrectCategory(){
     Category[] catStrings = Category.categoriesInArr();
      return catStrings[category.getSelectedItemPosition()];
    }

    private Purchase.PurchaseType setPurchaseType () {
        if (payment_method.getText().equals("Företagskort")){
            return Purchase.PurchaseType.COMPANY;
        }
        return Purchase.PurchaseType.PRIVATE;
    }
}

