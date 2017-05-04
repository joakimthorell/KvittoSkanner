package corp.skaj.foretagskvitton.controllers;

import android.content.Context;
import android.support.annotation.IdRes;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.activities.AddNewPostActivity;

public class BottomNavigationController {
    /**
     * Setting up the BottomNavigationBar for each activity. Call this onCreate in each activity using BottomBar
     * @param bottomBar
     * @param state ClassName.STATE_FOR_BOTTOM_MENY supposed to be a String
     * @param context
     */
    public static void setupBottomNavBar(final BottomBar bottomBar, String state, Context context) {
        final int activeState = getActiveTab(state);
        bottomBar.setDefaultTab(activeState);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.action_add:
                        if (activeState == R.id.action_add) {
                            return;
                        } else {
                            // TODO go to addNewPost
                        }
                    case R.id.action_archive:
                        //TODO what happens here?
                        return;
                    case R.id.action_business:
                        //TODO what happens here?
                        return;
                    case R.id.action_charts:
                        //TODO what happens here?
                        return;
                    case R.id.action_user:
                        //TODO what happens here?
                        return;
                    default:
                        return;
                }
            }
        });
    }

    private static int getActiveTab(String state) {
        switch (state) {
            case AddNewPostActivity.STATE_FOR_BOTTOM_MENY:
                return R.id.action_add;
            default:
                return 0;
        }
    }
}
