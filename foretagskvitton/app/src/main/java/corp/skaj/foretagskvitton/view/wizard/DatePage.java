package corp.skaj.foretagskvitton.view.wizard;

import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.tech.freak.wizardpager.model.ModelCallbacks;
import com.tech.freak.wizardpager.model.Page;
import com.tech.freak.wizardpager.model.ReviewItem;
import com.tech.freak.wizardpager.model.TextPage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DatePage extends TextPage implements IDate {
    private boolean mDateFound;

    public DatePage(ModelCallbacks callbacks, String title) {
        super(callbacks, title);
        mDateFound = false;
    }

    @Override
    public void getReviewItems(ArrayList<ReviewItem> dest) {
        dest.add(new ReviewItem(getTitle(), mData.getString(SIMPLE_DATA_KEY), getKey()));
    }

    @Override
    public boolean isCompleted() {
        return !TextUtils.isEmpty(mData.getString(SIMPLE_DATA_KEY));
    }

    public DatePage setValue(String s) {
        try {
            setBundle(s);
        } catch (ParseException e) {
            // Nothing to do here
        }
        return this;
    }

    private Page setBundle(String s) throws ParseException {
        if (s == null) {
            return this;
        }
        // Set date in TextField
        mData.putString(SIMPLE_DATA_KEY, s);

        // Set date in date picker dialog.
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(s);
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DATE);
        mData.putInt(DateFragment.DATE_YEAR_KEY, year);
        mData.putInt(DateFragment.DATE_MONTH_KEY, month);
        mData.putInt(DateFragment.DATE_DAY_KEY, day);
        mDateFound = true;

        return this;
    }

    @Override
    public Fragment createFragment() {
        DateFragment df = DateFragment.create(getKey());
        df.setListener(this);
        return df;
    }

    @Override
    public boolean isDateFound() {
        return mDateFound;
    }
}