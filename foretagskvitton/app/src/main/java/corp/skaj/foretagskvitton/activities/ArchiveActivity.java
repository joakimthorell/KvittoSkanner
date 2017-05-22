package corp.skaj.foretagskvitton.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.view.ArchiveFragment;

public class ArchiveActivity extends AbstractActivity {
    public static final String COMMENT_ID = "COMMENT_ID";
    public static final String ARCHIVE_BUNDLE = "PURCHASE_ID";
    public static final String PICTURE_ID = "PICTURE_ID";
    public static final String ACTIVITY_KEY = "ARCHIVE_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);


        String purchaseId= getIntent().getStringExtra(MainActivity.ARCHIVE_KEY);

        Bundle bundle = new Bundle();
        bundle.putString(ARCHIVE_BUNDLE, purchaseId);


        ArchiveFragment af = ArchiveFragment.create();
        af.setArguments(bundle);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.archive_fragment_container, af).commit();
    }

    /*
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

    public void onSaveClick(View view){
        mPur.getReceipt().setTotal(Double.valueOf(String.valueOf(cost.getText())));
        mPur.getReceipt().getProducts().get(0).getCategory();
        handler.writeData(User.class.getName(), user);
    }
    */
}