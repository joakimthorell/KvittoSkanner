package corp.skaj.foretagskvitton.controllers;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import corp.skaj.foretagskvitton.R;

public class AddNewPost extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_post);

        // hides the actionbar and gives fullscreen feature
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // setting up the bottom navigation
        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setDefaultTab(R.id.action_add);
        setupBottomNavigationBar(bottomBar);
    }

    private void setupBottomNavigationBar(BottomBar bottomBar) {
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.action_add:
                        //TODO what happens here?
                        return;
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

}
