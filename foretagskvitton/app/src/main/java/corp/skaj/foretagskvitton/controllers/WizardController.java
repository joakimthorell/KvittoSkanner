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

import corp.skaj.foretagskvitton.model.IObserver;
import corp.skaj.foretagskvitton.model.IData;
import corp.skaj.foretagskvitton.view.WizardView;
import corp.skaj.foretagskvitton.view.WriteDataFragment;

public class WizardController implements IObserver {
    private IUpdatable updater;
    private WizardView wizardView;
    private boolean mEditingAfterReview;
    private boolean mConsumePageSelectedEvent;

    public WizardController(Context context, IUpdatable updater) {
        mEditingAfterReview = false;
        this.updater = updater;
        wizardView = new WizardView(this, context);
    }

    public void initViewPagerListener(ViewPager mPager, final StepPagerStrip mStepPagerStrip) {
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Do nothing.
            }

            @Override
            public void onPageSelected(int position) {
                mStepPagerStrip.setCurrentPage(position);

                if (mConsumePageSelectedEvent) {
                    mConsumePageSelectedEvent = false;
                } else {
                    mEditingAfterReview = false;
                    updater.refreshBottomBar();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // Do nothing.
            }
        });
    }

    public void initNextButton(final IData dataHandler, Button mNextButton, final ViewPager mPager,
                               final MyPagerAdapter mPagerAdapter, final FragmentManager fragmentManager) {
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If we are at last page
                int size = wizardView.getWizardView().getCurrentPageSequence().size();
                if (mPager.getCurrentItem() == size) {
                    WriteDataFragment wls = new WriteDataFragment();
                    wls.setModel(wizardView.getWizardModel());
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

    public void initPrevButton(Button mPrevButton, final ViewPager mPager) {
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPager.setCurrentItem(mPager.getCurrentItem() - 1);
            }
        });
    }

    // Under construction...
    @Override
    public void onDataChange() {
        //TODO Save data from Wizard
    }

    public void updateConsumePageSelectedEvent(boolean state) {
        mConsumePageSelectedEvent = state;
    }

    public void updateEditingAfterReview(boolean state) {
        mEditingAfterReview = state;
    }

    public AbstractWizardModel getWizardView() {
        return wizardView.getWizardView();
    }

    public List<Page> getCurrentPageSequence() {
        return wizardView.getWizardView().getCurrentPageSequence();
    }
}
