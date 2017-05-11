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
import java.util.Map;

import corp.skaj.foretagskvitton.activities.IWizardActivity;
import corp.skaj.foretagskvitton.model.WizardModel;
import corp.skaj.foretagskvitton.services.DataHolder;
import corp.skaj.foretagskvitton.view.WriteDataFragment;

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
                // Do nothing.
            }

            @Override
            public void onPageSelected(int position) {
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
                // Do nothing.
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
                    WriteDataFragment wls = new WriteDataFragment();
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
    public void updateUser(DataHolder dataHolder) {
        Map<String, String> data = wizardModel.collectData();
        for (String s : data.keySet()) {
            System.out.println("KEY: " + s + "  DATA: " + data.get(s));
        }

        //TODO Save data in User from WizardModel data hashmap.

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
