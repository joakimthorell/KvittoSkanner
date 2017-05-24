package corp.skaj.foretagskvitton.controllers;

import android.content.Context;

import com.getbase.floatingactionbutton.FloatingActionsMenu;

import corp.skaj.foretagskvitton.view.FABCallback;

public abstract class FABController implements FABCallback {

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

    public abstract void bindButton(FloatingActionsMenu button);
}