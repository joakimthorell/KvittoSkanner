package corp.skaj.foretagskvitton.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.controllers.ArchiveController;
import corp.skaj.foretagskvitton.model.Purchase;
import corp.skaj.foretagskvitton.model.User;

public class ArchiveReceiptActivity<T> extends AbstractActivity {
    private Purchase mPur = new Purchase(null, null);
    private Class<T> mNextActivityToStart;
    public static final String COMMENT_ID = "COMMENT_ID";
    public static final String PICTURE_ID = "PICTURE_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive_receipt);

        //Id is used for getting the correct purchase
        Intent intent = getIntent();
        String purId = intent.getStringExtra(ArchiveController.ITEM_ID);

        User user = readUser();
        TextView cost = (TextView) findViewById(R.id.cost);
        TextView category = (TextView) findViewById(R.id.Category);
        TextView moms = (TextView) findViewById(R.id.Moms);
        TextView date = (TextView) findViewById(R.id.date);
        TextView supplier = (TextView) findViewById(R.id.Supplier);
        TextView payment_method = (TextView) findViewById(R.id.payment_method);
        TextView company = (TextView) findViewById(R.id.company);

        cost.setText(String.valueOf(mPur.getReceipt().getTotal()));
        category.setText(mPur.getReceipt().getProducts().get(0).getName());
        moms.setText(String.valueOf(mPur.getReceipt().getProducts().get(0).getTax()));

        SimpleDateFormat dateRaw = new SimpleDateFormat("yyyy-MM-dd");
        String receiptDate = dateRaw.format(mPur.getReceipt().getDate().getTime());

        date.setText(receiptDate);
        supplier.setText(mPur.getSupplier().getName());
        payment_method.setText(purchaseType());
        company.setText(user.getCompany(mPur).getName());
    }

    private String  purchaseType() {
        if (mPur.getPurchaseType() == mPur.getPurchaseType().PRIVATE) {
            return "Privatkort";
        }
        return "Företagskort";
    }

    public void onCommentClick (View view){
        Intent intent = new Intent(this, mNextActivityToStart);
        //intent.putExtra(COMMENT_ID, itemId);
        startActivity(intent);
    }

    public void onReceiptClick(View view){
        Intent intent = new Intent(this, mNextActivityToStart);
        //intent.putExtra(PICTURE_ID, itemId);
        startActivity(intent);
    }
}
