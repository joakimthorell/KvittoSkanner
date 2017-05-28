package corp.skaj.foretagskvitton.controllers;

import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AlignmentSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.Card;
import corp.skaj.foretagskvitton.model.Comment;
import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.Employee;
import corp.skaj.foretagskvitton.model.IDataHandler;
import corp.skaj.foretagskvitton.model.User;
import corp.skaj.foretagskvitton.view.CompanyFragment;
import corp.skaj.foretagskvitton.view.ICompany;
import corp.skaj.foretagskvitton.view.MultiDialog;

public class CompanyController implements ICompany, MultiDialog.Callback {
    private static final String mEditEmployee = "edit_employee";
    private static final String BUNDLE_KEY = "internal_bundle_key";
    private CompanyFragment mCompanyFragment;
    private IDataHandler mDataHandler;
    private Context mContext;

    public CompanyController(IDataHandler dataHandler, Context context, CompanyFragment companyFragment) {
        this.mDataHandler = dataHandler;
        this.mContext = context;
        this.mCompanyFragment = companyFragment;
    }

    @Override
    public void setEditEmployeeListener(Button editEmployeeButton) {
        editEmployeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putString(BUNDLE_KEY, mEditEmployee);
                Employee e = getEmployee();
                new MultiDialog(mContext,
                        CompanyController.this,
                        MultiDialog.Type.EDITOR,
                        e.getName(),
                        mContext.getString(R.string.employee_at_company),
                        b)
                        .newDialog()
                        .show();
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
                if (getCompany().getCards().size() > 0) {
                    String selectedCard = mCompanyFragment.getCardSpinnerItem();
                    new MultiDialog(mContext,
                            CompanyController.this,
                            MultiDialog.Type.EDITOR,
                            selectedCard,
                            mContext.getString(R.string.text_card))
                            .newDialog()
                            .show();
                } else {
                    Toast.makeText(mContext, R.string.create_new_card, Toast.LENGTH_SHORT).show();
                }
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

    public void setSaveCommentListener(TextView textView) {
        textView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                saveComment();
            }
        });
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
            mCompanyFragment.updateEmployeeSpinner();
            saveUser();
            Toast.makeText(mContext, mContext.getString(R.string.employee_removed), Toast.LENGTH_SHORT).show();
        }
    }

    private void removeCard() {
        Spannable centeredText = buildCenteredToastText(R.string.cant_delete_card);
        if ((getCompany().getCards().size() <= 0)) {
            Toast.makeText(mContext, centeredText, Toast.LENGTH_SHORT).show();
        } else {
            Card card = getCompany().getCard(cardStringToInt(mCompanyFragment.getCardSpinnerItem()));
            getUser().getCompany(companyName()).removeCard(card);
            mCompanyFragment.updateCardSpinner();
            saveUser();
            Toast.makeText(mContext, mContext.getString(R.string.card_removed), Toast.LENGTH_SHORT).show();
        }
    }

    private void saveComment() {
        String currentComment = mCompanyFragment.getCurrentComment();
        if (currentComment != null) {
            List<Comment> comments = getCompany().getComments();
            if (comments.size() > 0) {
                comments.get(0).setComment(currentComment);
                mDataHandler.saveUser();
            } else {
                createNewComment(comments, currentComment);
            }
        } else {
            Toast.makeText(mContext, R.string.invalid_input, Toast.LENGTH_SHORT).show();
        }
    }

    public void createNewComment(List<Comment> comments, String currentComment) {
        comments.add(new Comment(currentComment));
        mDataHandler.saveUser();
        Toast.makeText(mContext, R.string.changes_saved, Toast.LENGTH_SHORT).show();
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

    @Override
    public void dialogData(String newData, String oldData, Bundle extras) {
        // Checking if any data is ok first
        if (newData == null || newData.length() < 1) {
            Toast.makeText(mContext, mContext.getString(R.string.text_not_saved), Toast.LENGTH_SHORT).show();
            return;
        }

        if (extras != null) { // if not null, edit employee
            editUser(extras, newData, oldData);
        } else { // edit card
            editCard(newData, oldData);
        }

    }

    private boolean setNewEmployeeName(Employee toEdit, String newName) {
        if (Employee.isNameStandard(newName)) {
            toEdit.setName(newName);
            return true;
        }
        return false;
    }

    private void saveUser() {
        mDataHandler.saveUser();
    }

    private void editUser(Bundle extras, String newName, String oldName) {
        String request = extras.getString(BUNDLE_KEY);
        if (request != null && request == mEditEmployee) {
            Employee e = getCompany().getEmployee(oldName);
            if (setNewEmployeeName(e, newName)) {
                Toast.makeText(mContext, mContext.getString(R.string.changes_saved), Toast.LENGTH_SHORT).show();
                saveUser();
                mCompanyFragment.updateEmployeeSpinner();
            } else {
                Toast.makeText(mContext, mContext.getString(R.string.text_not_saved), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void editCard(String newCardNum, String oldCardNum) {
        if (newCardNum.matches("[0-9]+") && newCardNum.length() == 4) {
            Company c = getCompany();
            Card cardToEdit = c.getCard(Integer.parseInt(oldCardNum)); // should always work
            if (setNewCardNum(newCardNum, cardToEdit)) {
                saveUser();
                mCompanyFragment.updateCardSpinner();
                Toast.makeText(mContext, mContext.getString(R.string.changes_saved), Toast.LENGTH_SHORT).show();
                return;
            }
        }
        Toast.makeText(mContext, mContext.getString(R.string.text_not_saved), Toast.LENGTH_SHORT).show();
    }

    private boolean setNewCardNum(String newCardNum, Card toEdit) {
        int num;
        try {
            num = Integer.parseInt(newCardNum);
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            return false;
        }
        return toEdit.setCard(num);
    }

}