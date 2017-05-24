package corp.skaj.foretagskvitton.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.controllers.ArchiveController;
import corp.skaj.foretagskvitton.view.ArchiveFragment;
import corp.skaj.foretagskvitton.view.ArchiveListener;
import corp.skaj.foretagskvitton.view.ImageFragment;

public class ArchiveActivity extends AbstractActivity
    implements ArchiveListener {

    private boolean isShowingImage;
    private ArchiveFragment mArchiveFragment;
    private String mPurchaseId;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);
        mFragmentManager = getSupportFragmentManager();
        mPurchaseId = getIntent().getStringExtra(MainActivity.ARCHIVE_KEY);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        isShowingImage = false;
        changeFragment(createArchiveFragment());
    }

    private ArchiveFragment createArchiveFragment() {
        ArchiveFragment af = ArchiveFragment.create(mPurchaseId);
        ArchiveController controller = new ArchiveController(getDataHandler(), mPurchaseId, af);
        af.setFabListener(controller);
        af.setImageListener(this);
        return af;
    }

    @Override
    public void setImagePressed(Uri address) {
        String s = address.toString();
        ImageFragment fragment = ImageFragment.create(s);
        isShowingImage = true;
        changeFragment(fragment);
    }

    private void changeFragment(Fragment fragment) {
        String backStateName = fragment.getClass().getName();
        mFragmentManager = getSupportFragmentManager();
        boolean fragmentPopped = mFragmentManager.popBackStackImmediate (backStateName, 0);

        if (!fragmentPopped){ //fragment not in back stack, create it.
            FragmentTransaction ft = mFragmentManager.beginTransaction();
            ft.replace(R.id.archive_fragment_container, fragment);
            ft.addToBackStack(backStateName);
            ft.commit();
        }
    }

    @Override
    public void onBackPressed() {
        goBack();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                return goBack();
            default:
                return false;
        }
    }

    private boolean goBack() {
        if (isShowingImage) {
            isShowingImage = false;
            changeFragment(createArchiveFragment());
            return true;
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        return true;
    }
}