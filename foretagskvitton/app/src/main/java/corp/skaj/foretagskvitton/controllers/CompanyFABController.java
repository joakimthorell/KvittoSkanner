package corp.skaj.foretagskvitton.controllers;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.Card;
import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.Employee;
import corp.skaj.foretagskvitton.model.IData;
import corp.skaj.foretagskvitton.view.CompanyFragment;
import corp.skaj.foretagskvitton.view.MultiDialog;

public class CompanyFABController extends FABController
        implements MultiDialog.Callback {

    private boolean mCreateCard;
    private Company mCompany;
    private CompanyFragment mFragment;

    public CompanyFABController(Context context, Company company, CompanyFragment fragment) {
        super(context, null);
        mCompany = company;
        mFragment = fragment;
        mCreateCard = false;
    }

    @Override
    public void bindButton(FloatingActionsMenu button) {
        bindFAB(button);
    }

    private void bindFAB(final FloatingActionsMenu button) {

        // new employee button
        FloatingActionButton newEmployee = new FloatingActionButton(getContext());
        newEmployee.setTitle(getContext().getString(R.string.text_employee));
        newEmployee.setImageDrawable(getContext().getDrawable(R.drawable.ic_employee));
        newEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MultiDialog(getContext(),
                        CompanyFABController.this,
                        MultiDialog.Type.CREATER,
                        getContext().getString(R.string.text_employee))
                        .newDialog().show();
                button.collapse();
            }
        });
        button.addButton(newEmployee);

        // new card button
        FloatingActionButton newCard = new FloatingActionButton(getContext());
        newCard.setTitle(getContext().getString(R.string.text_card));
        newCard.setImageDrawable(getContext().getDrawable(R.drawable.ic_card));
        newCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCreateCard = true;
                new MultiDialog(getContext(),
                        CompanyFABController.this,
                        MultiDialog.Type.CREATER,
                        getContext().getString(R.string.text_card))
                        .newDialog().show();
                button.collapse();
            }
        });
        button.addButton(newCard);
    }

    @Override
    public void dialogData(String newData, String oldData, Bundle extras) {
        if (newData != null && newData.length() > 0) {
            IData handler = ((IData) getContext().getApplicationContext());
            if (mCreateCard) {
                mCreateCard = false;
                if (isCard(newData)) {
                    if (createCard(newData)) {
                        handler.saveUser();
                        Toast.makeText(getContext(), getContext().getString(R.string.text_created), Toast.LENGTH_SHORT).show();
                        mFragment.updateCardSpinner();
                        return;
                    }
                }
                Toast.makeText(getContext(), getContext().getString(R.string.text_not_saved), Toast.LENGTH_SHORT).show();
                return;
            } else {
                if (isLetters(newData)) {
                    Employee newEmployee = new Employee(newData);
                    mCompany.addEmployee(newEmployee);
                    handler.saveUser();
                    Toast.makeText(getContext(), getContext().getString(R.string.text_created), Toast.LENGTH_SHORT).show();
                    mFragment.updateEmployeeSpinner();
                    return;
                }
                Toast.makeText(getContext(), getContext().getString(R.string.text_not_saved), Toast.LENGTH_SHORT).show();
            }
        }
    }


    private boolean createCard(String data) {
        int cardNum = Integer.parseInt(data);
        if (!hasCard(cardNum)) {
            Card newCard = new Card(cardNum);
            mCompany.addCard(newCard);
            System.out.println("PRINTING THE NEW CARD NUMMER HERE" + newCard.getCard());

            return true;
        }
        return false;
    }

    private boolean isCard(String inputText) {
        return inputText.length() == 4
                && inputText.matches("[0-9]+");
    }

    private boolean hasCard(int cardNum) {
        for (Card c : mCompany.getCards()) {
            if (c.getCard() == cardNum) {
                return true;
            }
        }
        return false;
    }

    private boolean isLetters(String data) {
        return data.matches("[a-zA-Z]+");
    }
}