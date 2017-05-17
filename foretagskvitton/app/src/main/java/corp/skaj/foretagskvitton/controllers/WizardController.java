package corp.skaj.foretagskvitton.controllers;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.tech.freak.wizardpager.model.AbstractWizardModel;
import com.tech.freak.wizardpager.model.Page;
import com.tech.freak.wizardpager.ui.StepPagerStrip;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.Category;
import corp.skaj.foretagskvitton.model.Comment;
import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.Employee;
import corp.skaj.foretagskvitton.model.IData;
import corp.skaj.foretagskvitton.model.IObserver;
import corp.skaj.foretagskvitton.model.Product;
import corp.skaj.foretagskvitton.model.Purchase;
import corp.skaj.foretagskvitton.model.Receipt;
import corp.skaj.foretagskvitton.model.User;
import corp.skaj.foretagskvitton.model.WizardConstants;
import corp.skaj.foretagskvitton.view.WizardView;
import corp.skaj.foretagskvitton.view.ConfirmWizardFragment;

public class WizardController implements IObserver {
    private WizardView mWizardView;
    private ViewPager mPager;
    private Button mNextButton;
    private Button mPrevButton;
    private boolean mEditingAfterReview;
    private boolean mConsumePageSelectedEvent;
    private IData handler;


    public WizardController(Context context, Button mNextButton, Button mPrevButton, ViewPager mPager) {
        mEditingAfterReview = false;
        this.mNextButton = mNextButton;
        this.mPrevButton = mPrevButton;
        this.mPager = mPager;
        this.handler = (IData) context.getApplicationContext();
        mWizardView = new WizardView(this, context);
    }

    public void initViewPagerListener(final StepPagerStrip mStepPagerStrip) {
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
                    refreshBottomBar();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // Do nothing.
            }
        });
    }

    public void initNextButton(Button mNextButton,
                               final MyPagerAdapter mPagerAdapter,
                               final FragmentManager fragmentManager) {
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int size = mWizardView.getWizardView().getCurrentPageSequence().size();
                if (mPager.getCurrentItem() == size) {
                    ConfirmWizardFragment wls = new ConfirmWizardFragment();
                    wls.setModel(mWizardView.getWizardModel());
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

    public void initPrevButton(Button mPrevButton) {
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPager.setCurrentItem(mPager.getCurrentItem() - 1);
            }
        });
    }

    public void refreshBottomBar() {
        mPrevButton.setVisibility(View.VISIBLE);
        int position = mPager.getCurrentItem();
        if (position == mWizardView.getCurrentPageSequence().size()) {
            mNextButton.setText(R.string.wizard_complete);
        } else if (position <= 0) {
            mPrevButton.setVisibility(View.GONE);

            // TODO set next button as bigger if possible

        } else {
            mNextButton.setText(R.string.nextButtonText);
        }
    }

    /**
     * Key for bundle is "_". This is hardcoded from the WizardPager package
     */
    @Override
    public void onDataChange() {
        Bundle b = mWizardView.getWizardData();
        Bundle companyNameBundle = b.getBundle(WizardConstants.COMPANY);
        Bundle payMethodBundle = b.getBundle(WizardConstants.CARD);
        Bundle supplierBundle = b.getBundle(WizardConstants.SUPPLIER);
        Bundle dateBundle = b.getBundle(WizardConstants.DATE);
        Bundle totalBundle = b.getBundle(WizardConstants.TOTAL);
        Bundle vatBundle = b.getBundle(WizardConstants.VAT);
        Bundle categoryBundle = b.getBundle(WizardConstants.CATEGORY);
        Bundle commentBundle = b.getBundle(WizardConstants.COMMENT);

        // TODO need some kind of check for thing that is not required in wizard. There may be nullpointers

        Product product = buildProduct(
                totalBundle.getString("_"),
                vatBundle.getString("_"),
                categoryBundle.getString("_"));

        Receipt receipt = buildReceipt(
                product,
                dateBundle.getString("_"),
                totalBundle.getString("_"));

        Purchase purchase = buildPurchase(
                receipt, supplierBundle.getString("_"),
                payMethodBundle.getString("_"));

        if (commentBundle.getString("_") != null) {
            purchase.addComment(new Comment(commentBundle.getString("_")));
        }
        User user = handler.readData(User.class.getName(), User.class);
        Company company = user.getCompany(companyNameBundle.getString("_"));
        Employee employee = company.getEmployees().get(0);
        employee.addPurchase(purchase);
        saveUser(user);
    }

    private void saveUser(User user) {
        System.out.println("Saving user " + user.getName());
        handler.writeData(User.class.getName(), user);
        System.out.println("User : " + user.getName() + " saved. COMPLETE!");
        System.out.println("Removing used items");
        handler.removeData("mURI");
        handler.removeData("mStrings");
        System.out.println("Removed data. COMPLETE!");
    }

    private Purchase buildPurchase(Receipt receipt, String supplierAsString, String purchaseType) {
        Purchase.PurchaseType typeOfPurchase = purchaseType.equals("Företag") ? Purchase.PurchaseType.COMPANY : Purchase.PurchaseType.PRIVATE;
        // TODO fix supplier as we change to to be inside user instead

        return new Purchase(receipt, typeOfPurchase);
    }

    private Receipt buildReceipt(Product product, String dateAsString, String totalAsString) {
        Date dateAsDate = null;
        try {
            dateAsDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateAsString);
        } catch (ParseException pe) {
            dateAsDate = new Date();
        }
        Calendar date = Calendar.getInstance();
        date.setTime(dateAsDate);
        double total = Double.parseDouble(totalAsString);
        String URIAsString = handler.readData("mURI", String.class);
        return new Receipt(product, date, total, URIAsString);
    }

    private Product buildProduct(String priceAsString, String taxAsString, String categoryAsString) {
        double totalPrice = Double.parseDouble(priceAsString);
        double taxAsDouble = Double.parseDouble(taxAsString);
        Category categoryAsCategory = Category.valueOf(categoryAsString.toUpperCase());
        return new Product(Product.WHOLE_RECEIPT, categoryAsCategory, totalPrice, taxAsDouble);
    }

    public void updateConsumePageSelectedEvent(boolean state) {
        mConsumePageSelectedEvent = state;
    }

    public void updateEditingAfterReview(boolean state) {
        mEditingAfterReview = state;
    }

    public void setCurrentItem(int i) {
        mPager.setCurrentItem(i);
    }

    public List<Page> getCurrentPageSequence() {
        return mWizardView.getWizardView().getCurrentPageSequence();
    }

    public AbstractWizardModel getWizardView() {
        return mWizardView.getWizardView();
    }
}
