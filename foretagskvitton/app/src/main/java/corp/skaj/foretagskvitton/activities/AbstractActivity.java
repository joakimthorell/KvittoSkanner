package corp.skaj.foretagskvitton.activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.HashMap;
import java.util.Map;

import corp.skaj.foretagskvitton.R;

public class AbstractActivity extends AppCompatActivity {
    private Map<Integer, Class<?extends AbstractActivity>> mBottomBarMap;

    private void setBottomBarMap() {
        if (mBottomBarMap == null) {
            mBottomBarMap  = new HashMap<>();
        }
        mBottomBarMap.put(ArchiveActivity.BOTTOM_BAR_ID, ArchiveActivity.class);
        mBottomBarMap.put(GraphActivity.BOTTOM_BAR_ID, GraphActivity.class);
        mBottomBarMap.put(AddReceiptActivity.BOTTOM_BAR_ID, AddReceiptActivity.class);
        mBottomBarMap.put(CompanyActivity.BOTTOM_BAR_ID, CompanyListActivity.class);
        mBottomBarMap.put(UserActivity.BOTTOM_BAR_ID, UserActivity.class);
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
}
