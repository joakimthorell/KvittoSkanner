package corp.skaj.foretagskvitton.activities;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.IData;
import corp.skaj.foretagskvitton.model.Purchase;
import corp.skaj.foretagskvitton.model.PurchaseList;
import corp.skaj.foretagskvitton.model.User;

import static corp.skaj.foretagskvitton.controllers.ArchiveController.RECEIPT_ID;

public class ArchiveReceiptActivity<T> extends AbstractActivity {

    Purchase mPur;
    private Class<T> mNextActivityToStart;
    public static final String COMMENT_ID = "COMMENT_ID";
    public static final String PICTURE_ID = "PICTURE_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive_receipt);

        IData handler = (IData) getApplicationContext();
        PurchaseList list = handler.getPurchases();

        String purchaseId= getIntent().getExtras().get(RECEIPT_ID).toString();
        mPur = list.getPurchase(purchaseId);

        User user = readUser();
        TextView cost = (TextView) findViewById(R.id.cost);
        TextView category = (TextView) findViewById(R.id.Category);
        TextView moms = (TextView) findViewById(R.id.Moms);
        TextView date = (TextView) findViewById(R.id.date);
        TextView supplier = (TextView) findViewById(R.id.Supplier);
        TextView payment_method = (TextView) findViewById(R.id.payment_method);
        TextView company = (TextView) findViewById(R.id.Company);

        cost.setText(String.valueOf(mPur.getReceipt().getTotal()) + "SEK");
        category.setText(mPur.getReceipt().getProducts().get(0).getCategory().name());
        moms.setText(String.valueOf("Moms: " + mPur.getReceipt().getProducts().get(0).getTax()) +"%");
        supplier.setText(checkSupplier());
        payment_method.setText(purchaseType());

        // TODO - Find out what goes wrong with company fetch.
        company.setText(user.getCompany(mPur).getName());

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
}

