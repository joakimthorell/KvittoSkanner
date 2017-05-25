package corp.skaj.foretagskvitton.controllers;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AlignmentSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.Card;
import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.Employee;
import corp.skaj.foretagskvitton.model.IData;
import corp.skaj.foretagskvitton.model.User;
import corp.skaj.foretagskvitton.view.CompanyFragment;
import corp.skaj.foretagskvitton.view.ILinkCompanyListener;

public class CompanyController implements ILinkCompanyListener {
    private final String mEditEmployee = "EDIT_EMPLOYEE";
    private final String mEditCard = "EDIT_CARD";
    private CompanyFragment mCompanyFragment;
    private IData mDataHandler;
    private Context mContext;

    public CompanyController(IData dataHandler, Context context, CompanyFragment companyFragment) {
        this.mDataHandler = dataHandler;
        this.mContext = context;
        this.mCompanyFragment = companyFragment;
    }

    @Override
    public void setEditEmployeeListener(Button editEmployeeButton) {
        editEmployeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddNewDialog(mEditEmployee);
            }
        });
    }

    @Override
    public void setRemoveEmployeeListener(Button removeEmployeeButton) {
        removeEmployeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeEmployee();
            }
        });
    }

    @Override
    public void setEditCardListener(Button editCardButton) {
        editCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddNewDialog(mEditCard);
            }
        });
    }

    @Override
    public void setRemoveCardListener(Button removeCardButton) {
        removeCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeCard();
            }
        });
    }

    private void showAddNewDialog(final String KEY) {
        AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
        final EditText edittext = new EditText(mContext);
        edittext.setSingleLine(true);

        if (KEY.equals(mEditEmployee)) {
            alert.setMessage("Skriv den anställdes namn: ");
        } else {
            alert.setMessage("Skriv sista 4 siffrorna på företagskortet: ");
        }
        alert.setTitle("Editera");
        alert.setView(edittext);
        alert.setPositiveButton("Lägg till", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //What ever you want to do with the value
                Editable editedInfo = edittext.getText();

                if (KEY.equals(mEditEmployee)) {
                    if (!(editedInfo.toString().length() < 1
                            || editedInfo.toString().matches("[a-zA-Z]+"))) {
                        return;
                    }
                } else if (!(editedInfo.toString().length() < 5
                        || editedInfo.toString().length() > 0
                        || editedInfo.toString().matches("[0-9]+"))) {
                    return;
                } else {
                    editChanges(editedInfo, KEY);
                }
            }
        });
        alert.setNegativeButton("Avbryt", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // nothing to do here
            }
        });
        alert.show();
    }

    private void editChanges(Editable editedInfo, String KEY) {
        int oldCardNum = cardStringToInt(mCompanyFragment.getCardSpinnerItem());
        if (KEY.equals(mEditEmployee)) {
            updateEmployee(editedInfo.toString());
        } else if (oldCardNum != -1) {
            updateCard(editedInfo.toString());
        } else {
            return;
        }
        Toast.makeText(mContext, "Dina ändringar är sparade ", Toast.LENGTH_SHORT).show();
        mDataHandler.saveUser();
    }

    private void updateEmployee(String newEmployeeName) {
        Employee employee = getEmployee();
        employee.setName(newEmployeeName);
        mCompanyFragment.updateEmployeeSpinner(companyName());
    }

    private void updateCard(String newCardNumber) {
        Card card;
        int oldCardNum = cardStringToInt(mCompanyFragment.getCardSpinnerItem());
        if (oldCardNum != -1) {
            card = getUser().getCompany(companyName()).getCard(cardStringToInt(mCompanyFragment.getCardSpinnerItem()));
            int cardNum = cardStringToInt(newCardNumber);
            if (cardNum != -1) {
                card.setCard(Integer.parseInt(newCardNumber));
                mCompanyFragment.updateCardSpinner(companyName());
            }
        }
    }

    private int cardStringToInt(String cardNum) {
        try {
            return Integer.parseInt(cardNum);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void removeEmployee() {
        Spannable centeredText = buildCenteredToastText(R.string.cant_delete_employee);
        if (getCompany().getEmployees().size() == 1) {
            Toast.makeText(mContext, centeredText, Toast.LENGTH_SHORT).show();
        } else {
            Employee employee = getEmployee();
            getCompany().removeEmployee(employee);
        }
    }

    private void removeCard() {
        Spannable centeredText = buildCenteredToastText(R.string.cant_delete_card);
        if ((getCompany().getCards().size() <= 0)) {
            Toast.makeText(mContext, centeredText, Toast.LENGTH_SHORT).show();
        } else {
            Card card = getCompany().getCard(cardStringToInt(mCompanyFragment.getCardSpinnerItem()));
            getUser().getCompany(companyName()).removeCard(card);
        }
    }

    private Spannable buildCenteredToastText(int rString) {
        Spannable centeredText = new SpannableString(mContext.getString(rString));
        getTextCentered(centeredText, mContext.getString(rString));
        return centeredText;
    }

    public void getTextCentered(Spannable centeredText, String text) {
        centeredText.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER),
                0, text.length() - 1,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE);
    }

    private Company getCompany() {
        return getUser().getCompany(companyName());
    }

    private Employee getEmployee() {
        return getUser().getCompany(companyName()).getEmployee(mCompanyFragment.getEmployeeSpinnerItem());
    }

    private String companyName() {
        return mCompanyFragment.getCompanyName();
    }

    private User getUser() {
        return mDataHandler.getUser();
    }
}