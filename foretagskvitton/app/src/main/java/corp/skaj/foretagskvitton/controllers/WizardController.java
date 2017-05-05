package corp.skaj.foretagskvitton.controllers;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.tech.freak.wizardpager.model.AbstractWizardModel;
import com.tech.freak.wizardpager.model.Page;
import com.tech.freak.wizardpager.ui.StepPagerStrip;

import java.util.List;

import corp.skaj.foretagskvitton.model.WizardModel;
import corp.skaj.foretagskvitton.view.MyPagerAdapter;
import corp.skaj.foretagskvitton.view.WizardLastStep;

public class WizardController implements IWizardController {
    private IWizardActivity wizardActivity;
    private WizardModel wizardModel;
    private boolean mEditingAfterReview;
    private boolean mConsumePageSelectedEvent;

    public WizardController(Context context, IWizardActivity iWizardActivity) {
        mEditingAfterReview = false;
        wizardActivity = iWizardActivity;
        wizardModel = new WizardModel(context);
    }

    public void initViewPagerListener(ViewPager mPager, final StepPagerStrip mStepPagerStrip) {
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // should do nothing i think
            }

            @Override
            public void onPageSelected(int position) {
                //TODO Figure out what this does.
                mStepPagerStrip.setCurrentPage(position);

                if (mConsumePageSelectedEvent) {
                    mConsumePageSelectedEvent = false;
                } else {
                    mEditingAfterReview = false;
                    wizardActivity.updateBottomBar();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // should do nothing i think
            }
        });
    }

    public void initNextButton(Button mNextButton, final ViewPager mPager,
                               final MyPagerAdapter mPagerAdapter, final FragmentManager fragmentManager) {
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If we are at last page
                int size = wizardModel.getCurrentPageSequence().size();
                if (mPager.getCurrentItem() == size) {
                    WizardLastStep wls = new WizardLastStep();
                    wls.show(fragmentManager, "confirm_receipt_dialog");
                } else {
                    if (mEditingAfterReview) {
                        mPager.setCurrentItem(mPagerAdapter.getCount() - 1);
                    } else {
                        mPager.setCurrentItem(mPager.getCurrentItem() + 1);
                    }
                }
            }
        });
    }

    public void initBackButton(Button mPrevButton, final ViewPager mPager) {
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPager.setCurrentItem(mPager.getCurrentItem() - 1);
            }
        });
    }

    public void initPagerStrip(StepPagerStrip mStepPagerStrip, final MyPagerAdapter mPagerAdapter, final ViewPager mPager) {
        mStepPagerStrip.setOnPageSelectedListener(new StepPagerStrip.OnPageSelectedListener() {
            @Override
            public void onPageStripSelected(int position) {
                position = Math.min(mPagerAdapter.getCount() - 1, position);
                if (mPager.getCurrentItem() != position) {
                    mPager.setCurrentItem(position);
                }
            }
        });
    }

    @Override
    public void updateConsumePageSelectedEvent(boolean state) {
        mConsumePageSelectedEvent = state;
    }

    @Override
    public void updateEditingAfterReview(boolean state) {
        mEditingAfterReview = state;
    }

    @Override
    public AbstractWizardModel getWizardModel() {
        return wizardModel;
    }

    @Override
    public List<Page> getCurrentPageSequence() {
        return wizardModel.getCurrentPageSequence();
    }
}
