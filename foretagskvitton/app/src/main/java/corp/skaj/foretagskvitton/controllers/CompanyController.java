package corp.skaj.foretagskvitton.controllers;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.Card;
import corp.skaj.foretagskvitton.model.Employee;
import corp.skaj.foretagskvitton.model.IData;
import corp.skaj.foretagskvitton.model.Supplier;
import corp.skaj.foretagskvitton.model.User;
import corp.skaj.foretagskvitton.view.CompanyFragment;
import corp.skaj.foretagskvitton.view.ICompany;
import corp.skaj.foretagskvitton.view.SupplierListFragment;

public class CompanyController implements ICompany{
    private IData mDataHandler;
    private final String mEditEmployee = "EMPLOYEE_KEY";
    private final String mEditCard = "CARD_KEY";
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

                if(editedInfo.toString().length() < 1){
                    return;
                }
                editInfo(editedInfo, KEY);
            }
        });
        alert.setNegativeButton("Avbryt", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // nothing to do here
            }
        });
        alert.show();
    }

    private void editInfo (Editable editedInfo, String KEY) {
        if (KEY.equals(mEditEmployee)) {
            updateEmployee(editedInfo.toString());
        } else {
            updateCard(editedInfo.toString());
        }
        Toast.makeText(mContext, "Dina ändringar är sparade ", Toast.LENGTH_SHORT).show();
        mDataHandler.saveUser();
    }

    private void updateEmployee (String newEmployeeName) {
        User user = getUser();
        Employee employee = user.getCompany(mCompanyFragment.getCompanyName()).getEmployee(mCompanyFragment.getEmployeeItem());
        employee.setName(newEmployeeName);
        mCompanyFragment.updateEmployeeSpinner(mCompanyFragment.getCompanyName());
    }

    private void updateCard (String newCardNumber) {
        Card card = getUser().getCompany(mCompanyFragment.getCompanyName()).getCard(Integer.parseInt(mCompanyFragment.getCardItem()));
        card.setCard(Integer.parseInt(newCardNumber));
        mCompanyFragment.updateCardSpinner(mCompanyFragment.getCompanyName());
    }

    private void removeEmployee () {
        Employee employee = getUser().getCompany(mCompanyFragment.getCompanyName()).getEmployee(mCompanyFragment.getEmployeeItem());
        getUser().getCompany(mCompanyFragment.getCompanyName()).removeEmployee(employee);
    }

    private void removeCard () {
        Card card = getUser().getCompany(mCompanyFragment.getCompanyName()).getCard(Integer.parseInt(mCompanyFragment.getCardItem()));
        getUser().getCompany(mCompanyFragment.getCompanyName()).removeCard(card);
    }

    private User getUser () {
        return mDataHandler.getUser();
    }



}