package corp.skaj.foretagskvitton.controllers;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.tech.freak.wizardpager.ui.StepPagerStrip;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.Category;
import corp.skaj.foretagskvitton.model.Comment;
import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.Employee;
import corp.skaj.foretagskvitton.model.IDataHandler;
import corp.skaj.foretagskvitton.model.Product;
import corp.skaj.foretagskvitton.model.Purchase;
import corp.skaj.foretagskvitton.model.Receipt;
import corp.skaj.foretagskvitton.model.User;
import corp.skaj.foretagskvitton.view.wizard.IWizard;
import corp.skaj.foretagskvitton.view.wizard.WizardConstants;
import corp.skaj.foretagskvitton.view.wizard.WizardFragment;
import corp.skaj.foretagskvitton.view.wizard.WizardView;

public class WizardController {
    private WizardView mWizardView;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private Button mNextButton;
    private Button mPrevButton;
    private boolean mEditingAfterReview;
    private boolean mConsumePageSelectedEvent;
    private IDataHandler mDataHandler;

    public WizardController(Context context, Button nextButton, Button prevButton,
                            ViewPager viewpager, PagerAdapter pagerAdapter, WizardView wizardView) {
        mEditingAfterReview = false;
        mNextButton = nextButton;
        mPrevButton = prevButton;
        mPager = viewpager;
        mPagerAdapter = pagerAdapter;
        mWizardView = wizardView;
        mDataHandler = (IDataHandler) context.getApplicationContext();
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

    public void initNextButton(final IWizard notifier, Button mNextButton,
                               final FragmentManager fragmentManager) {
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int size = mWizardView.getWizardView().getCurrentPageSequence().size();
                if (mPager.getCurrentItem() == size) {
                    WizardFragment wls = new WizardFragment();
                    wls.setObserver(notifier);
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
        } else {
            mNextButton.setText(R.string.nextButtonText);
        }
    }

    /**
     * Key for bundle is "_". This is hardcoded from the WizardPager package.
     */
    public void saveReceipts() {
        String key = "_";
        Bundle b = mWizardView.getWizardData();
        Bundle companyNameBundle = b.getBundle(WizardConstants.COMPANY);
        Bundle payMethodBundle = b.getBundle(WizardConstants.CARD);
        Bundle supplierBundle = b.getBundle(WizardConstants.SUPPLIER);
        Bundle dateBundle = b.getBundle(WizardConstants.DATE);
        Bundle totalBundle = b.getBundle(WizardConstants.TOTAL);
        Bundle vatBundle = b.getBundle(WizardConstants.VAT);
        Bundle categoryBundle = b.getBundle(WizardConstants.CATEGORY);
        Bundle commentBundle = b.getBundle(WizardConstants.COMMENT);

        Product product = buildProduct(
                totalBundle.getString(key),
                vatBundle.getString(key),
                categoryBundle.getString(key));

        Receipt receipt = buildReceipt(
                product,
                dateBundle.getString(key),
                totalBundle.getString(key));

        Purchase purchase = buildPurchase(
                receipt,
                supplierBundle == null ? null : supplierBundle.getString(key),
                payMethodBundle.getString(key));

        Company company = getCompany(companyNameBundle, key);
        addPurchase(company, purchase);
        addComment(commentBundle, purchase, key);
        mDataHandler.saveUser();
        resetSavedData();
    }

    private Company getCompany(Bundle companyNameBundle, String key) {
        User user = mDataHandler.getUser();
        return companyNameBundle != null ?
                user.getCompany(companyNameBundle.getString(key)) :
                user.getCompanies().get(0); // if bundle is null there is only one company
    }

    private void addComment(Bundle commentBundle, Purchase purchase, String key) {
        if (commentBundle.getString(key) != null) {
            purchase.addComment(new Comment(commentBundle.getString(key)));
        }
    }

    private void addPurchase(Company company, Purchase purchase) {
        Employee employee = company.getEmployees().get(0);
        employee.addPurchase(purchase);
    }

    private void resetSavedData() {
        mDataHandler.removeData(IDataHandler.IMAGE_URI_KEY);
        mDataHandler.removeData(IDataHandler.COLLECTED_STRINGS_KEY);
    }

    private Purchase buildPurchase(Receipt receipt, String supplierAsString, String purchaseType) {
        Purchase.PurchaseType typeOfPurchase = purchaseType.equals("Företag")
                ? Purchase.PurchaseType.COMPANY : Purchase.PurchaseType.PRIVATE;
        return new Purchase(receipt, typeOfPurchase);
    }

    private Receipt buildReceipt(Product product, String dateString, String totalString) {
        Date date = getDate(dateString);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        double total = Double.parseDouble(totalString);
        String URIString = mDataHandler.readData(IDataHandler.IMAGE_URI_KEY, String.class);
        return new Receipt(product, calendar, total, URIString);
    }

    private Date getDate(String dateString) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        } catch (ParseException pe) {
            return new Date();
        }
    }

    private Product buildProduct(String priceAsString, String vatString, String categoryAsString) {
        double totalPrice = Double.parseDouble(priceAsString);
        double vatDouble = Double.parseDouble(vatString);
        Category category = Category.valueOf(categoryAsString.toUpperCase());
        return new Product(Product.ALL_PRODUCTS, category, totalPrice, vatDouble);
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
}
