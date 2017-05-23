package corp.skaj.foretagskvitton.view;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import corp.skaj.foretagskvitton.R;

public class CompanyFragment extends Fragment {
    private Spinner employees;
    private Spinner cards;
    private TextView comment;

    public CompanyFragment() {
        // Required empty public constructor
    }

    public static CompanyFragment create() {
        CompanyFragment fragment = new CompanyFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_company, container, false);
        setupFragment(v);
        return inflater.inflate(R.layout.fragment_company, container, false);
    }

    private void setupFragment(View v) {

    }
}
