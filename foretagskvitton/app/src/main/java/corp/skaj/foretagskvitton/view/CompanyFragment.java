package corp.skaj.foretagskvitton.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.Card;
import corp.skaj.foretagskvitton.model.Comment;
import corp.skaj.foretagskvitton.model.Company;

public class CompanyFragment extends AbstractFragment {
    private final static String COMPANY_BUNDLE = "COMPANY_ID";
    private Spinner mEmployees;
    private Spinner mCards;
    private TextView mComment;
    private TextView mCompanyName;
    private ICompany mCompanyListener;
    private IFAB mFabController;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String companyName = getArguments().getString(COMPANY_BUNDLE);
        View v = inflater.inflate(R.layout.fragment_company, container, false);
        setupFragment(v, companyName);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionsMenu button = (FloatingActionsMenu)view.findViewById(R.id.floating_action_button);
        mFabController.bindButton(button);
    }

    private void setupFragment(View view, String companyName) {
        setWidgets(view);
        setButtonListeners(view);
        mCompanyName.setText(companyName);
        mComment.setText(getComment(companyName));

        ArrayAdapter<String> employeeAdapter = buildArrayAdapter(getEmployees(companyName, null));
        setArrayAdapter(employeeAdapter, mEmployees);

        setCardSpinner(companyName);

    }

    private void setCardSpinner(String companyName) {
        List<String> cards = getCards(companyName);
        ArrayAdapter<String> cardsAdapter;
        if (cards.size() == 0) {
            cardsAdapter = buildArrayAdapter(new ArrayList<String>(Arrays.asList(getContext().getString(R.string.no_card))));
        } else {
            cardsAdapter = buildArrayAdapter(getCards(companyName));
        }
        setArrayAdapter(cardsAdapter, mCards);
    }

    private void setWidgets(View view) {
        mCompanyName = (TextView) view.findViewById(R.id.fragment_company_title);
        mEmployees = (Spinner) view.findViewById(R.id.fragment_company_employees_spinner);
        mCards = (Spinner) view.findViewById(R.id.fragment_company_cards_spinner);
        mComment = (TextView) view.findViewById(R.id.fragment_company_comment);
    }

    private void setButtonListeners(View view) {
        Button mEditEmployee = (Button) view.findViewById(R.id.fragment_company_edit_employee_button);
        Button mRemoveEmployee = (Button) view.findViewById(R.id.fragment_company_remove_employee_button);
        Button mEditCard = (Button) view.findViewById(R.id.fragment_company_edit_card_button);
        Button mRemoveCard = (Button) view.findViewById(R.id.fragment_company_remove_card_button);
        Button mSaveComment = (Button) view.findViewById(R.id.fragment_company_save_company_comment);
        mCompanyListener.setEditEmployeeListener(mEditEmployee);
        mCompanyListener.setRemoveEmployeeListener(mRemoveEmployee);
        mCompanyListener.setEditCardListener(mEditCard);
        mCompanyListener.setRemoveCardListener(mRemoveCard);
        mCompanyListener.setSaveCommentListener(mSaveComment);
    }

    public void setListener(ICompany listener) {
        mCompanyListener = listener;
    }

    public void setFabController(IFAB fabController) {
        mFabController = fabController;
    }

    private String getComment(String companyName) {
        List<Comment> comments = getUser().getCompany(companyName).getComments();
        return comments.size() == 0 ? "Ingen kommentar" : comments.get(0).getComment();
    }

    public String getCurrentComment(){
        return mComment.getText() == null ? null : mComment.getText().toString();
    }

    public void updateEmployeeSpinner() {
        ArrayAdapter<String> adapter = buildArrayAdapter(getEmployees(getCompanyName(), null));
        setArrayAdapter(adapter, mEmployees);
    }

    public void updateCardSpinner() {
        ArrayAdapter<String> cardsAdapter = buildArrayAdapter(getCards(getCompanyName()));
        setArrayAdapter(cardsAdapter, mCards);
    }

    public String getEmployeeSpinnerItem() {
        String name = mEmployees.getSelectedItem().toString();
        System.out.println(name);
        return name;
    }

    public String getCardSpinnerItem() {
         Object selectedCardItem = mCards.getSelectedItem();
        return selectedCardItem == null ? null : selectedCardItem.toString();
    }

    private List<String> getCards(String companyName) {
        List<String> list = new ArrayList<>();
        Company company = getUser().getCompany(companyName);
        for (Card c : company.getCards()) {
            list.add(String.valueOf(c.getCard()));
        }
        return list;
    }

    public String getCompanyName() {
        return mCompanyName.getText().toString();
    }
}