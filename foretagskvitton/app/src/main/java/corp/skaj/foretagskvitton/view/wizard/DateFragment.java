/**
 * This class is taken from a pullrequest made by Github user: Monomachus, in romannurik's Android-WizardPager repo.
 * A lot of code is changed from the original code.
 */

package corp.skaj.foretagskvitton.view.wizard;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.tech.freak.wizardpager.model.Page;
import com.tech.freak.wizardpager.ui.PageFragmentCallbacks;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import corp.skaj.foretagskvitton.R;

public class DateFragment extends Fragment {
    public static final String DATE_YEAR_KEY = "YEAR_KEY";
    public static final String DATE_MONTH_KEY = "MONTH_KEY";
    public static final String DATE_DAY_KEY = "DAY_KEY";
    private static final String ARG_KEY = "DATE_FRAGMENT_KEY";
    private DatePickerDialog mDatePickerDialog;
    private PageFragmentCallbacks mCallbacks;
    private EditText mDateView;
    private String mKey;
    private IDate mListener;

    public static DateFragment create(String key) {
        Bundle args = new Bundle();
        args.putString(ARG_KEY, key);
        DateFragment fragment = new DateFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public DateFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        mKey = args.getString(ARG_KEY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_date, container, false);
        ((TextView) rootView.findViewById(android.R.id.title)).setText(getPage().getTitle());
        mDateView = ((EditText) rootView.findViewById(R.id.etDate));
        mDateView.setText(getPage().getData().getString(getPage().SIMPLE_DATA_KEY));
        return rootView;
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        if (!(activity instanceof PageFragmentCallbacks)) {
            throw new ClassCastException(getContext().getString(R.string.class_cast_excep));
        }
        mCallbacks = (PageFragmentCallbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDateView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mDatePickerDialog.show();
            }
        });
        Calendar newCalendar = Calendar.getInstance();

        mDatePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                String formattedDate = dateFormatter.format(newDate.getTime());
                mDateView.setText(formattedDate);
                getPage().getData().putString(getPage().SIMPLE_DATA_KEY, formattedDate);
                getPage().notifyDataChanged();
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        if (mListener.isDateFound()) {
            mDatePickerDialog.getDatePicker().updateDate(
                    getPage().getData().getInt(DATE_YEAR_KEY),
                    getPage().getData().getInt(DATE_MONTH_KEY),
                    getPage().getData().getInt(DATE_DAY_KEY)
            );
        }
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (mDateView != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            if (!menuVisible) {
                imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
            }
        }
    }

    public void setListener(IDate listener) {
        mListener = listener;
    }

    private Page getPage() {
        return mCallbacks.onGetPage(mKey);
    }
}