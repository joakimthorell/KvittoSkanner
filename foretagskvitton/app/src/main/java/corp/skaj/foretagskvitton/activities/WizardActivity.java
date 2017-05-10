
/*
 * Copyright 2012 Roman Nurik
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package corp.skaj.foretagskvitton.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.tech.freak.wizardpager.model.AbstractWizardModel;
import com.tech.freak.wizardpager.model.ModelCallbacks;
import com.tech.freak.wizardpager.model.Page;
import com.tech.freak.wizardpager.ui.PageFragmentCallbacks;
import com.tech.freak.wizardpager.ui.ReviewFragment;
import com.tech.freak.wizardpager.ui.StepPagerStrip;

import java.util.List;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.controllers.IWizardActivity;
import corp.skaj.foretagskvitton.model.DataHolder;
import corp.skaj.foretagskvitton.model.IUpdateUser;
import corp.skaj.foretagskvitton.controllers.WizardController;
import corp.skaj.foretagskvitton.controllers.MyPagerAdapter;

public class WizardActivity extends AbstractActivity implements
        PageFragmentCallbacks, ReviewFragment.Callbacks, ModelCallbacks, IWizardActivity, IUpdateUser {

    private IWizardController wizardController;
    private AbstractWizardModel mWizardModel;
    private MyPagerAdapter mPagerAdapter;
    private ViewPager mPager;
    private StepPagerStrip mStepPagerStrip;
    private Button mNextButton;
    private Button mPrevButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wizard);

        // Set instances
        mNextButton = (Button) findViewById(R.id.wizardNextButton);
        mPrevButton = (Button) findViewById(R.id.wizardBackButton);
        mPager = (ViewPager) findViewById(R.id.pager);
        mStepPagerStrip = (StepPagerStrip) findViewById(R.id.wizard_strip);
        WizardController wizardController = new WizardController(this, this);
        this.wizardController = wizardController;
        this.mWizardModel = wizardController.getWizardModel();
        mPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), wizardController);
        mPager.setAdapter(mPagerAdapter);

        // Set listeners
        wizardController.initNextButton(mNextButton, mPager, mPagerAdapter, getSupportFragmentManager());
        wizardController.initPrevButton(mPrevButton, mPager);
        wizardController.initViewPagerListener(mPager, mStepPagerStrip);
        mWizardModel.registerListener(this);

        // Set the normal actionbar to custom toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.wizard_action_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_close);
        actionBar.setDisplayHomeAsUpEnabled(true);

        onPageTreeChanged();
        updateBottomBar();
    }

    @Override
    public void onPageTreeChanged() {
        recalculateCutOffPage();
        int size = mWizardModel.getCurrentPageSequence().size() + 1;
        mStepPagerStrip.setPageCount(size); // + 1 = review, step
        mPagerAdapter.notifyDataSetChanged();
        updateBottomBar();
    }

    public void updateBottomBar() {
        mPrevButton.setVisibility(View.VISIBLE);
        int position = mPager.getCurrentItem();

        if (position == mWizardModel.getCurrentPageSequence().size()) {
            mNextButton.setText(R.string.wizard_complete);
        } else if (position <= 0) {
            mPrevButton.setVisibility(View.GONE);

            // TODO set next button as bigger if possible

        } else {
            mNextButton.setText(R.string.nextButtonText);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWizardModel.unregisterListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle("model", mWizardModel.save());
    }

    @Override
    public AbstractWizardModel onGetModel() {
        return mWizardModel;
    }

    @Override
    public void onEditScreenAfterReview(String key) {
        List<Page> currentPageSequenceList = mWizardModel.getCurrentPageSequence();
        int size = currentPageSequenceList.size() - 1;
        for (int i = size; i >= 0; i--) {
            if (currentPageSequenceList.get(i).getKey().equals(key)) {
                wizardController.updateConsumePageSelectedEvent(true);
                wizardController.updateEditingAfterReview(true);
                mPager.setCurrentItem(i);
                updateBottomBar();
                break;
            }
        }
    }

    @Override
    public void onPageDataChanged(Page page) {
        if (page.isRequired()) {
            if (recalculateCutOffPage()) {
                mPagerAdapter.notifyDataSetChanged();
                updateBottomBar();
            }
        }
    }

    @Override
    public Page onGetPage(String key) {
        return mWizardModel.findByKey(key);
    }

    private boolean recalculateCutOffPage() {
        List<Page> currentPageSequenceList = mWizardModel.getCurrentPageSequence();
        int cutOffPage = currentPageSequenceList.size() + 1;

        for (int i = 0; i < currentPageSequenceList.size(); i++) {
            Page page = currentPageSequenceList.get(i);
            if (page.isRequired() && !page.isCompleted()) {
                cutOffPage = i;
                break;
            }
        }
        if (mPagerAdapter.getCutOffPage() != cutOffPage) {
            mPagerAdapter.setCutOffPage(cutOffPage);
            return true;
        }
        return false;
    }

    @Override
    public void updateUser() {
        wizardController.updateUser((DataHolder) getApplicationContext());
        // TODO go to new intent or back to parent?
    }

}
