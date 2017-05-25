package corp.skaj.foretagskvitton.controllers;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AlignmentSpan;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.Card;
import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.Employee;
import corp.skaj.foretagskvitton.model.IData;
import corp.skaj.foretagskvitton.model.Supplier;
import corp.skaj.foretagskvitton.model.User;
import corp.skaj.foretagskvitton.view.CompanyFragment;
import corp.skaj.foretagskvitton.view.ICompany;
import corp.skaj.foretagskvitton.view.SupplierListFragment;

public class CompanyController implements ICompany {
    private IData mDataHandler;
    private final String mEditEmployee = "EDIT_EMPLOYEE";
    private final String mEditCard = "EDIT_CARD";
    private Context mContext;
    private CompanyFragment mCompanyFragment;

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
            alert.setMessage("Skriv den anställdes namn:");
        } else {
            alert.setMessage("Skriv in kortnummer");
        }
        alert.setTitle("Editera");
        alert.setView(edittext);
        alert.setPositiveButton("Lägg till", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //What ever you want to do with the value
                Editable editedInfo = edittext.getText();

                if (editedInfo.toString().length() < 1) {
                    return;
                }
                editChanges(editedInfo, KEY);
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
        if (KEY.equals(mEditEmployee)) {
            updateEmployee(editedInfo.toString());
        } else {
            updateCard(editedInfo.toString());
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
        Card card = getUser().getCompany(companyName()).getCard(Integer.parseInt(mCompanyFragment.getCardItem()));
        card.setCard(Integer.parseInt(newCardNumber));
        mCompanyFragment.updateCardSpinner(companyName());
    }

    private void removeEmployee() {
        Spannable centeredText = new SpannableString(mContext.getString(R.string.cant_delete_employee));
        getTextCentered(centeredText, mContext.getString(R.string.cant_delete_employee));
        if (getCompany().getEmployees().size() == 1) {
            Toast.makeText(mContext, centeredText, Toast.LENGTH_SHORT).show();
        } else {
            Employee employee = getEmployee();
            getCompany().removeEmployee(employee);
        }
    }

    private void removeCard() {
        Spannable centeredText = new SpannableString(mContext.getString(R.string.cant_delete_card));
        getTextCentered(centeredText, mContext.getString(R.string.cant_delete_card));
        if ((getCompany().getCards().size() <= 0)) {
            Toast.makeText(mContext, centeredText, Toast.LENGTH_SHORT).show();
        } else {
            Card card = getCompany().getCard(Integer.parseInt(mCompanyFragment.getCardItem()));
            getUser().getCompany(companyName()).removeCard(card);
        }
    }

    private User getUser() {
        return mDataHandler.getUser();
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
        return getUser().getCompany(companyName()).getEmployee(mCompanyFragment.getEmployeeItem());
    }

    private String companyName() {
        return mCompanyFragment.getCompanyName();
    }

}