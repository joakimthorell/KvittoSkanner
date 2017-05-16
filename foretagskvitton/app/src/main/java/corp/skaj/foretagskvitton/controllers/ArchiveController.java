package corp.skaj.foretagskvitton.controllers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import corp.skaj.foretagskvitton.model.Purchase;
import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.Employee;
import corp.skaj.foretagskvitton.model.Receipt;

public class ArchiveController<T> {
    public static final String ITEM_ID = "receipt_id";

    private Context mContext;
    private Class<T> mNextActivityToStart;

    public ArchiveController(Context context, Class<T> nextActivityToStart) {
        mContext = context;
        mNextActivityToStart = nextActivityToStart;
    }

    public void onItemClicked(int itemId) {
        Intent intent = new Intent(mContext, mNextActivityToStart);
        intent.putExtra(ITEM_ID, itemId);

        System.out.println("Going to itemID   :   " + itemId);

        mContext.startActivity(intent);
    }

}