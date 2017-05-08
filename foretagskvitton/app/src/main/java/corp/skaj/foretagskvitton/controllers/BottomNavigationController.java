package corp.skaj.foretagskvitton.controllers;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.activities.AddNewPostActivity;
import corp.skaj.foretagskvitton.activities.GraphActivity;
import corp.skaj.foretagskvitton.activities.CompanyActivity;


public class BottomNavigationController {

    // Setting up the BottomNavigationBar for each activity. Call this onCreate in each activity using BottomBar.
    public static void setupBottomNavBar(final BottomBar bottomBar, String state, final Context context) {
        final int activeState = getActiveTab(state);
        bottomBar.setDefaultTab(activeState);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {

            @Override
            public void onTabSelected(@IdRes int tabId) {
                Intent intent;
                switch (tabId) {
                    case R.id.action_add:
                        if (activeState == R.id.action_add) {
                            return;
                        }
                        intent = new Intent(context, AddNewPostActivity.class);
                        context.startActivity(intent);
                    case R.id.action_archive:
                        //TODO what happens here?
                        return;
                    case R.id.action_business:
                        if (activeState == R.id.action_business) {
                            return;
                        }
                        intent = new Intent(context, CompanyActivity.class);
                        context.startActivity(intent);
                        return;
                    case R.id.action_charts:
                        if (activeState == R.id.action_charts) {
                            return;
                        }
                        intent = new Intent(context, GraphActivity.class);
                        context.startActivity(intent);
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
            case GraphActivity.STATE_FOR_BOTTOM_MENY:
                return R.id.action_charts;
            case CompanyActivity.STATE_FOR_BOTTOM_MENY:
                return R.id.action_business;
            default:
                return 0;
        }
    }
}
