package corp.skaj.foretagskvitton.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    private TextView mCompanyName;
    private ICompany mCompanyListener;

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
        mCompanyName = (TextView) view.findViewById(R.id.fragment_company_title);
        mEmployees = (Spinner) view.findViewById(R.id.fragment_company_employees_spinner);
        mCards = (Spinner) view.findViewById(R.id.fragment_company_cards_spinner);
        mComment = (TextView) view.findViewById(R.id.fragment_company_comment);
        Button mEditEmployee = (Button) view.findViewById(R.id.fragment_company_edit_employee_button);
        Button mRemoveEmployee = (Button) view.findViewById(R.id.fragment_company_remove_employee_button);
        Button mEditCard = (Button) view.findViewById(R.id.fragment_company_edit_card_button);
        Button mRemoveCard = (Button) view.findViewById(R.id.fragment_company_remove_card_button);
        mCompanyListener.setEditEmployeeListener(mEditEmployee);
        mCompanyListener.setRemoveEmployeeListener(mRemoveEmployee);
        mCompanyListener.setEditCardListener(mEditCard);
        mCompanyListener.setRemoveCardListener(mRemoveCard);

        mCompanyName.setText(companyName);

        ArrayAdapter<String> employeeAdapter = buildArrayAdapter(view, getEmployees(companyName));
        setArrayAdapter(employeeAdapter, mEmployees);

        ArrayAdapter<String> cardsAdapter = buildArrayAdapter(view, getCards(companyName));
        setArrayAdapter(cardsAdapter, mCards);

        mComment.setText(getComment(companyName));
    }

    public void setListener (ICompany listener) {
        mCompanyListener = listener;
    }

    private String getComment(String companyName) {
        List<Comment> comments = getUser().getCompany(companyName).getComments();
        return comments.size() == 0 ? "Ingen kommentar" : comments.get(0).getComment();
    }

    public void updateEmployeeSpinner (String companyName) {
        ArrayAdapter<String> adapter = buildArrayAdapter(getView(), getEmployees(companyName));
        setArrayAdapter(adapter, mEmployees);
    }

    public void updateCardSpinner (String companyName) {
        ArrayAdapter<String> cardsAdapter = buildArrayAdapter(getView(), getCards(companyName));
        setArrayAdapter(cardsAdapter, mCards);
    }

    public String getEmployeeItem () {
        System.out.println("LETS DO THIS" + getCompanyName());
        return mEmployees.getSelectedItem().toString();
    }

    public String getCardItem () {
        return mCards.getSelectedItem().toString();
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

    public String getCompanyName () {
        return mCompanyName.getText().toString();
    }
}