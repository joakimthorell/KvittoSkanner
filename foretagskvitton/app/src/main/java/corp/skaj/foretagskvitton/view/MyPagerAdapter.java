package corp.skaj.foretagskvitton.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.tech.freak.wizardpager.model.AbstractWizardModel;
import com.tech.freak.wizardpager.model.Page;
import com.tech.freak.wizardpager.ui.ReviewFragment;

import java.util.List;

import corp.skaj.foretagskvitton.controllers.IWizardController;
import corp.skaj.foretagskvitton.model.WizardModel;

/**
 *
 */
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
        System.out.println("POSITIONEN SOM FÖRSÖKER HÄMTAS ÄR:   " + i);
        System.out.println("DENNA METODEN KÖRS NU");
        if (i >= wizardController.getCurrentPageSequence().size()) {
            return new ReviewFragment();
        }
        return wizardController.getCurrentPageSequence().get(i).createFragment();
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