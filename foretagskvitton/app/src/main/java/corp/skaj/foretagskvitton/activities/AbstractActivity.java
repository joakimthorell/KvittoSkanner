package corp.skaj.foretagskvitton.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.HashMap;
import java.util.Map;

import corp.skaj.foretagskvitton.R;

public class AbstractActivity extends AppCompatActivity {
    private Map<Integer, Class<?extends AbstractActivity>> bottomBarMap;

    private void setupBottomBarMap() {
        if (bottomBarMap == null) {
            bottomBarMap  = new HashMap<>();
        }
        bottomBarMap.put(ArchiveActivity.BOTTOM_BAR_ID, ArchiveActivity.class);
        bottomBarMap.put(GraphActivity.BOTTOM_BAR_ID, GraphActivity.class);
        bottomBarMap.put(AddNewPostActivity.BOTTOM_BAR_ID, AddNewPostActivity.class);
        bottomBarMap.put(CompanyActivity.BOTTOM_BAR_ID, CompanyListingActivity.class);
        bottomBarMap.put(UserActivity.BOTTOM_BAR_ID, UserActivity.class);
    }

    protected void initBottomBar(final Integer ID, final Context context) {
        setupBottomBarMap();
        setuDefaultTab(ID).setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId != ID) {
                    context.startActivity(new Intent(context, bottomBarMap.get(tabId)));
                }
            }
        });
    }

    private BottomBar setuDefaultTab(final  Integer ID) {
        final BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setDefaultTab(ID);
        return bottomBar;
    }
}
