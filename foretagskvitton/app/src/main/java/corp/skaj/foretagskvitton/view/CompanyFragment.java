package corp.skaj.foretagskvitton.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.Card;
import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.IData;

public class CompanyFragment extends AbstractFragment {
    private final static String COMPANY_BUNDLE = "COMPANY_ID";
    private Spinner mEmployees;
    private Spinner mCards;
    private TextView mComment;

    public CompanyFragment() {
        // Required empty public constructor
    }

    public static CompanyFragment create(String companyName) {
        Bundle bundle = new Bundle();
        bundle.putString(COMPANY_BUNDLE, companyName);
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
        String companyName = getArguments().getString(COMPANY_BUNDLE);
        setupFragment(v, companyName);
        return inflater.inflate(R.layout.fragment_company, container, false);
    }

    private void setupFragment(View view, String companyName) {
        mEmployees = (Spinner) view.findViewById(R.id.company_fragment_employees_spinner);
        mCards = (Spinner) view.findViewById(R.id.company_fragment_cards_spinner);
        mComment = (TextView) view.findViewById(R.id.company_fragment_comment);

        ArrayAdapter<String> employeeAdapter = buildArrayAdapter(view, getEmployees());
        setArrayAdapter(employeeAdapter, mEmployees);

        ArrayAdapter<String> cardsAdapter = buildArrayAdapter(view, getCards());
        setArrayAdapter(cardsAdapter, mCards);
    }

    private List<String> getCards() {
        List<String> list = new ArrayList<>();
        List<Company> companies = getUser().getCompanies();
        for (Company c : companies) {
            for (Card card : c.getCards()) {
                list.add(String.valueOf(card.getCard()));
            }
        }
        return list;
    }
}
