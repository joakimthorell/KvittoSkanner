package corp.skaj.foretagskvitton.controllers;

import android.content.Context;

import com.getbase.floatingactionbutton.FloatingActionsMenu;

import corp.skaj.foretagskvitton.view.IFAB;

public abstract class AbstractFABController implements IFAB {
    private Context mContext;
    private Class<?> mNextActivity;

    protected AbstractFABController(Context context, Class<?> nextActivity) {
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