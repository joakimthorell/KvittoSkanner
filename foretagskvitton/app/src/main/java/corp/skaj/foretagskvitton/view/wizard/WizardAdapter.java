package corp.skaj.foretagskvitton.view.wizard;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.tech.freak.wizardpager.ui.ReviewFragment;

public class WizardAdapter extends FragmentStatePagerAdapter {
    private WizardView mWizardView;
    private Fragment mPrimaryItem;
    private int mCutOffPage;

    public WizardAdapter(FragmentManager fm, WizardView wizardView) {
        super(fm);
        mWizardView = wizardView;
    }

    @Override
    public Fragment getItem(int i) {
        if (i >= mWizardView.getCurrentPageSequence().size()) {
            return new ReviewFragment();
        }
        return mWizardView.getCurrentPageSequence().get(i).createFragment();
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
        return Math.min(mCutOffPage + 1, mWizardView.getCurrentPageSequence() == null ? 1
                : mWizardView.getCurrentPageSequence().size() + 1);
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