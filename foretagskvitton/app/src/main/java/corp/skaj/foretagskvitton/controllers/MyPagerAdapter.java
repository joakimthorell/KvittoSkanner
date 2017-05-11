package corp.skaj.foretagskvitton.controllers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.tech.freak.wizardpager.ui.ReviewFragment;

import corp.skaj.foretagskvitton.activities.IWizardController;

public class MyPagerAdapter extends FragmentStatePagerAdapter {
    private IWizardController wizardController;
    private Fragment mPrimaryItem;
    private int mCutOffPage;

    public MyPagerAdapter(FragmentManager fm, IWizardController wizardController) {
        super(fm);
        this.wizardController = wizardController;
    }

    @Override
    public Fragment getItem(int i) {
        if (i >= wizardController.getCurrentPageSequence().size()) {
            return new ReviewFragment();
        }
        return wizardController.getCurrentPageSequence().get(i).createFragment();
    }

    @Override
    public int getItemPosition(Object object) {
        if (object == mPrimaryItem) {
            return POSITION_UNCHANGED;
        }
        return POSITION_NONE;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        mPrimaryItem = (Fragment) object;
    }

    @Override
    public int getCount() {
        return Math.min(mCutOffPage + 1, wizardController.getCurrentPageSequence() == null ? 1
                : wizardController.getCurrentPageSequence().size() + 1);
    }

    public void setCutOffPage(int cutOffPage) {
        if (cutOffPage < 0) {
            cutOffPage = Integer.MAX_VALUE;
        }
        mCutOffPage = cutOffPage;
    }

    public int getCutOffPage() {
        return mCutOffPage;
    }
}