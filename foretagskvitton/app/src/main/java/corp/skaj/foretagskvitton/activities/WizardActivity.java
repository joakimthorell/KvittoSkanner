
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

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.tech.freak.wizardpager.model.AbstractWizardModel;
import com.tech.freak.wizardpager.model.ModelCallbacks;
import com.tech.freak.wizardpager.model.Page;
import com.tech.freak.wizardpager.ui.PageFragmentCallbacks;
import com.tech.freak.wizardpager.ui.ReviewFragment;
import com.tech.freak.wizardpager.ui.StepPagerStrip;

import java.util.List;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.controllers.WizardController;
import corp.skaj.foretagskvitton.view.wizard.WizardAdapter;
import corp.skaj.foretagskvitton.model.IObserver;
import corp.skaj.foretagskvitton.view.wizard.WizardView;

public class WizardActivity extends AbstractActivity implements
        PageFragmentCallbacks, ReviewFragment.Callbacks, ModelCallbacks, IObserver {

    private WizardController mWizardController;
    private WizardView mWizardView;
    private WizardAdapter mPagerAdapter;
    private StepPagerStrip mStepPagerStrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wizard);

        // Set instances
        Button mNextButton = (Button) findViewById(R.id.wizardNextButton);
        Button mPrevButton = (Button) findViewById(R.id.wizardBackButton);
        ViewPager mPager = (ViewPager) findViewById(R.id.pager);
        mStepPagerStrip = (StepPagerStrip) findViewById(R.id.wizard_strip);
        mWizardView = new WizardView(this);
        mWizardController = new WizardController(this, mNextButton, mPrevButton, mPager, mPagerAdapter, mWizardView);
        mPagerAdapter = new WizardAdapter(getSupportFragmentManager(), mWizardView);
        mPager.setAdapter(mPagerAdapter);

        // Set listeners
        mWizardController.initNextButton(mNextButton, getSupportFragmentManager());
        mWizardController.initPrevButton(mPrevButton);
        mWizardController.initViewPagerListener(mStepPagerStrip);
        mWizardView.registerListener(this);

        // Set actionbar to custom toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.wizard_action_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_close);
        actionBar.setDisplayHomeAsUpEnabled(true);

        onPageTreeChanged();
        mWizardController.refreshBottomBar();
    }

    @Override
    public void onPageTreeChanged() {
        recalculateCutOffPage();
        int size = mWizardView.getCurrentPageSequence().size() + 1;
        mStepPagerStrip.setPageCount(size); // + 1 = review, step
        mPagerAdapter.notifyDataSetChanged();
        mWizardController.refreshBottomBar();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWizardView.unregisterListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle("model", mWizardView.save());
    }

    @Override
    public AbstractWizardModel onGetModel() {
        return mWizardView;
    }

    @Override
    public void onEditScreenAfterReview(String key) {
        List<Page> currentPageSequenceList = mWizardView.getCurrentPageSequence();
        int size = currentPageSequenceList.size() - 1;
        for (int i = size; i >= 0; i--) {
            if (currentPageSequenceList.get(i).getKey().equals(key)) {
                mWizardController.updateConsumePageSelectedEvent(true);
                mWizardController.updateEditingAfterReview(true);
                mWizardController.setCurrentItem(i);
                mWizardController.refreshBottomBar();
                break;
            }
        }
    }

    @Override
    public void onPageDataChanged(Page page) {
        if (page.isRequired()) {
            if (recalculateCutOffPage()) {
                mPagerAdapter.notifyDataSetChanged();
                mWizardController.refreshBottomBar();
            }
        }
    }

    @Override
    public Page onGetPage(String key) {
        return mWizardView.findByKey(key);
    }

    private boolean recalculateCutOffPage() {
        List<Page> currentPageSequenceList = mWizardView.getCurrentPageSequence();
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
    public void onDataChange() {
        mWizardController.saveReceipts();
        Toast.makeText(this, R.string.receipt_saved, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
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
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        return true;
    }


}
