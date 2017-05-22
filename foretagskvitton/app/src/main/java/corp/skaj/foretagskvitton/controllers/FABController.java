package corp.skaj.foretagskvitton.controllers;

import android.content.Context;

public abstract class FABController {

    private Context mContext;
    private Class<?> mNextActivity;

    protected FABController(Context context, Class<?> nextActivity) {
        mContext = context;
        mNextActivity = nextActivity;
    }

    protected Context getContext() {
        return mContext;
    }

    protected Class<?> getNextActivity() {
        return mNextActivity;
    }
}
