/**
 * This class is taken from a pullrequest made by Github user: Monomachus, in romannurik's Android-WizardPager repo.
 * A lot of code is changed from the original code.
 */

package corp.skaj.foretagskvitton.view;

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

import com.tech.freak.wizardpager.ui.PageFragmentCallbacks;

import java.util.Calendar;

import corp.skaj.foretagskvitton.R;

public class DateFragment extends Fragment {
    private static final String ARG_KEY = "DATE_FRAGMENT_KEY";
    private DatePickerDialog mDatePickerDialog;
    private PageFragmentCallbacks mCallbacks;
    private EditText mDateView;
    private DatePage mPage;
    private String mKey;

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


        mPage = (DatePage) mCallbacks.onGetPage(mKey);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_date, container, false);

        ((TextView) rootView.findViewById(android.R.id.title)).setText(mPage.getTitle());

        mDateView = ((EditText) rootView.findViewById(R.id.etDate));
        mDateView.setText(mPage.getData().getString(DatePage.SIMPLE_DATA_KEY));

        return rootView;
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);

        if (!(activity instanceof PageFragmentCallbacks)) {
            throw new ClassCastException("Activity must implement PageFragmentCallbacks");
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
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                String formattedDate = DatePage.dateFormatter.format(newDate.getTime());

                mDateView.setText(formattedDate);
                mPage.getData().putString(DatePage.SIMPLE_DATA_KEY, formattedDate);
                mPage.notifyDataChanged();
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        if (mPage.isDateFound()) {
            mDatePickerDialog.getDatePicker().updateDate(
                    mPage.getData().getInt(DatePage.DATE_YEAR_KEY),
                    mPage.getData().getInt(DatePage.DATE_MONTH_KEY),
                    mPage.getData().getInt(DatePage.DATE_DAY_KEY)
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
}