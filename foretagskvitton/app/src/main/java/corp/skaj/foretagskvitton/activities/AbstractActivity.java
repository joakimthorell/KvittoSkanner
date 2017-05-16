package corp.skaj.foretagskvitton.activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.IData;
import corp.skaj.foretagskvitton.model.User;
import corp.skaj.foretagskvitton.services.DataHandler;

public abstract class AbstractActivity extends AppCompatActivity {
    private SparseArray<Class<?extends AbstractActivity>> mBottomBarMap;
    protected final Integer ARCHIVE_ID = R.id.action_archive;
    protected final Integer CHARTS_ID = R.id.action_charts;
    protected final Integer ADD_RECEIPT_ID = R.id.action_add;
    protected final Integer COMPANY_ID = R.id.action_business;
    protected final Integer USER_ID = R.id.action_user;

    private void setBottomBarMap() {
        if (mBottomBarMap == null) {
            mBottomBarMap  = new SparseArray<>();
        }
        mBottomBarMap.put(ARCHIVE_ID, ArchiveActivity.class);
        mBottomBarMap.put(CHARTS_ID, GraphActivity.class);
        mBottomBarMap.put(ADD_RECEIPT_ID, AddReceiptActivity.class);
        mBottomBarMap.put(COMPANY_ID, CompanyListActivity.class);
        mBottomBarMap.put(USER_ID, UserActivity.class);
    }

    protected void initBottomBar(final Integer ID, final Context context) {
        setBottomBarMap();
        setDefaultTab(ID).setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId != ID) {
                    context.startActivity(new Intent(context, mBottomBarMap.get(tabId)));
                }
            }
        });
    }

    private BottomBar setDefaultTab(final  Integer ID) {
        final BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setDefaultTab(ID);
        return bottomBar;
    }

    protected User readUser() {
        return ((IData) getApplicationContext()).readData(User.class.getName(), User.class);
    }

    protected void writeUser(User user) {
        ((IData) getApplicationContext()).writeData(User.class.getName(), user);
    }
}
