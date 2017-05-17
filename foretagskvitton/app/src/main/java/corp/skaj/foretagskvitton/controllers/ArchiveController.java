package corp.skaj.foretagskvitton.controllers;

import android.content.Context;
import android.content.Intent;

public class ArchiveController<T> {
    public static final String ITEM_ID = "RECEIPT_ID";
    private Context mContext;
    private Class<T> mNextActivityToStart;

    public ArchiveController(Context context, Class<T> nextActivityToStart) {
        mContext = context;
        mNextActivityToStart = nextActivityToStart;
    }

    public void onItemClicked(String itemId) {
        Intent intent = new Intent(mContext, mNextActivityToStart);
        intent.putExtra(ITEM_ID, itemId);
        mContext.startActivity(intent);
    }

}