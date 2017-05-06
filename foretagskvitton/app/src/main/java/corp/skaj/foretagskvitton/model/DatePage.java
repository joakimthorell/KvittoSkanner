package corp.skaj.foretagskvitton.model;

import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.tech.freak.wizardpager.model.ModelCallbacks;
import com.tech.freak.wizardpager.model.Page;
import com.tech.freak.wizardpager.model.ReviewItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import corp.skaj.foretagskvitton.view.DateFragment;

public class DatePage extends Page {
    private boolean dateFound;
    public static final String DATE_DATA_KEY = "corp.skaj.foretagskvitton.model.DatePage.date";
    public static SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    public static final String DATE_YEAR_KEY = "yearKey";
    public static final String DATE_MONTH_KEY = "monthKey";
    public static final String DATE_DAY_KEY = "dayKey";


    public DatePage(ModelCallbacks callbacks, String title) {
        super(callbacks, title);
        dateFound = false;
    }

    @Override
    public Fragment createFragment() {
        return DateFragment.create(getKey());
    }

    @Override
    public void getReviewItems(ArrayList<ReviewItem> dest) {
        dest.add(new ReviewItem(getTitle(), mData.getString(DATE_DATA_KEY), getKey()));
    }

    @Override
    public boolean isCompleted() {
        return !TextUtils.isEmpty(mData.getString(DATE_DATA_KEY));
    }

    public Page setValue(String s) {
        try {
            return setBundle(s);
        } catch (ParseException e) {
            // Nothing to do here
        }
        return this;
    }

    private Page setBundle(String s) throws ParseException {
        if (s == null) {
            return this;
        }

        // This is for putting date in TextField
        mData.putString(DATE_DATA_KEY, s);

        // This is for setting date in date picker dialog
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(s);
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DATE);
        dateFound = true;

        mData.putInt(DATE_YEAR_KEY, year);
        mData.putInt(DATE_MONTH_KEY, month);
        mData.putInt(DATE_DAY_KEY, day);

        return this;
    }

    public boolean isDateFound() {
        return dateFound;
    }
}
