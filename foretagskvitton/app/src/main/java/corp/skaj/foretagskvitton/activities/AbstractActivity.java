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
import corp.skaj.foretagskvitton.services.DataHolder;
import corp.skaj.foretagskvitton.model.User;

public class AbstractActivity extends AppCompatActivity {
    private Map<Integer, Class<?extends AbstractActivity>> bottomBarMap = new HashMap<>();

    protected void writeData() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        DataHolder dataHolder = (DataHolder) getApplicationContext();
        SharedPreferences.Editor prefsEditor = sharedPref.edit();
        Gson gson = new Gson();
        String saveData = gson.toJson(dataHolder.getUser());
        prefsEditor.putString(User.class.getName(), saveData);
        prefsEditor.apply();
    }


    public void initBottomBarMap() {
        if (!bottomBarMap.isEmpty()) {
            return;
        }
        bottomBarMap.put(ArchiveActivity.BOTTOM_BAR_ID, ArchiveActivity.class);
        bottomBarMap.put(GraphActivity.BOTTOM_BAR_ID, GraphActivity.class);
        bottomBarMap.put(AddNewPostActivity.BOTTOM_BAR_ID, AddNewPostActivity.class);
        bottomBarMap.put(CompanyActivity.BOTTOM_BAR_ID, CompanyActivity.class);
        bottomBarMap.put(UserActivity.BOTTOM_BAR_ID, UserActivity.class);
    }

    protected void initBottomBar(final Integer ID, final Context context) {
        final BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setDefaultTab(ID);
        initBottomBarMap();
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId != ID) {
                    context.startActivity(new Intent(context, bottomBarMap.get(tabId)));
                }
            }
        });
    }
}
