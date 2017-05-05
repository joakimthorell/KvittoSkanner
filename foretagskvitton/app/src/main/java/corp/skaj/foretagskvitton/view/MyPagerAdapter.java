package corp.skaj.foretagskvitton.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.tech.freak.wizardpager.model.Page;
import com.tech.freak.wizardpager.ui.ReviewFragment;

import java.util.List;

/**
 *
 */
public class MyPagerAdapter extends FragmentStatePagerAdapter {
    private List<Page> mCurrentPageSequence;
    private Fragment mPrimaryItem;
    private int mCutOffPage;

    public MyPagerAdapter(FragmentManager fm, List<Page> mCurrentPageSequence) {
        super(fm);
        this.mCurrentPageSequence = mCurrentPageSequence;
    }

    @Override
    public Fragment getItem(int i) {
        if (i >= mCurrentPageSequence.size()) {
            return new ReviewFragment();
        }
        return mCurrentPageSequence.get(i).createFragment();
    }

    @Override
    public int getItemPosition(Object object) {
        // TODO: be smarter about this
        if (object == mPrimaryItem) {
            // Re-use the current fragment (its position never changes)
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
        return Math.min(mCutOffPage + 1, mCurrentPageSequence == null ? 1
                : mCurrentPageSequence.size() + 1);
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