package corp.skaj.foretagskvitton.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.Card;
import corp.skaj.foretagskvitton.model.Comment;
import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.Employee;

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
        fragment.setArguments(bundle);
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
        String companyName = getArguments().getString(COMPANY_BUNDLE);
        View v = inflater.inflate(R.layout.fragment_company, container, false);
        setupFragment(v, companyName);
        return v;
    }

    private void setupFragment(View view, String companyName) {
        mEmployees = (Spinner) view.findViewById(R.id.company_fragment_employees_spinner);
        mCards = (Spinner) view.findViewById(R.id.company_fragment_cards_spinner);
        mComment = (TextView) view.findViewById(R.id.company_fragment_comment);

        ArrayAdapter<String> employeeAdapter = buildArrayAdapter(view, getEmployees(companyName));
        setArrayAdapter(employeeAdapter, mEmployees);

        ArrayAdapter<String> cardsAdapter = buildArrayAdapter(view, getCards(companyName));
        setArrayAdapter(cardsAdapter, mCards);

        mComment.setText(getComment(companyName));
    }

    private String getComment(String companyName) {
        List<Comment> comments = getUser().getCompany(companyName).getComments();
        return comments.size() == 0 ? "Ingen kommentar" : comments.get(0).getComment();
    }

    private List<String> getCards(String companyName) {
        List<String> list = new ArrayList<>();
        Company company = getUser().getCompany(companyName);
        for (Card c : company.getCards()) {
            list.add(String.valueOf(c.getCard()));
        }
        return list;
    }

    private List<String> getEmployees(String companyName) {
        List<String> list = new ArrayList<>();
        Company company = getUser().getCompany(companyName);
        for (Employee e : company.getEmployees()) {
            list.add(e.getName());
        }
        return list;
    }
}