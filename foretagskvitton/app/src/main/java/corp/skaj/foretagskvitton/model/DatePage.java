package corp.skaj.foretagskvitton.model;

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
import java.util.Locale;

public class DatePage extends TextPage {
    public static SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    public static final String DATE_YEAR_KEY = "YEAR_KEY";
    public static final String DATE_MONTH_KEY = "MONTH_KEY";
    public static final String DATE_DAY_KEY = "DAY_KEY";
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
        mDateFound = true;

        mData.putInt(DATE_YEAR_KEY, year);
        mData.putInt(DATE_MONTH_KEY, month);
        mData.putInt(DATE_DAY_KEY, day);

        return this;
    }

    public boolean isDateFound() {
        return mDateFound;
    }
}
