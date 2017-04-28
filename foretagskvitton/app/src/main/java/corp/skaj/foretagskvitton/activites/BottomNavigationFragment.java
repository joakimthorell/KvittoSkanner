package corp.skaj.foretagskvitton.activites;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import corp.skaj.foretagskvitton.R;

public class BottomNavigationFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_navigation_fragment, container, false);
        return view;
    }

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
                        //TODO what happens here=
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
            case AddNewPost.STATE_FOR_BOTTOM_MENY:
                return R.id.action_add;
            default:
                return 0;
        }
    }
}
