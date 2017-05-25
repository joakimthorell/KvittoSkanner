package corp.skaj.foretagskvitton.view;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.widget.EditText;
import android.widget.TextView;

import corp.skaj.foretagskvitton.R;

public class MultiDialog {
    public enum Type {
        CREATER,
        EDITOR
    }

    public interface Callback {
        void dialogData(String newData, String oldData);
    }


    private Context mContext;
    private Callback mCreater;
    private Type mTypeOfDialog;
    private String mTextToEdit;
    private String mExtraTitle;

    public MultiDialog(Context context, Callback dialogCallback, Type typeOfDialog, String textToEdit, String extraTextInTitle) {
        mContext = context;
        mCreater = dialogCallback;
        mTypeOfDialog = typeOfDialog;
        mExtraTitle = extraTextInTitle;
        mTextToEdit = textToEdit;
    }

    public MultiDialog(Context context, Callback dialogCallback, Type typeOfDialog, String extraTextInTitle) {
        mContext = context;
        mCreater = dialogCallback;
        mTypeOfDialog = typeOfDialog;
        mExtraTitle = extraTextInTitle;
    }

    public AlertDialog.Builder newDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(mContext);

        final EditText textField = new EditText(mContext);
        textField.setSingleLine(true);
        textField.setText(mTextToEdit);

        // if more cases comes up, edit here
        String positiveButtonText;
        String title;
        String message = mContext.getString(R.string.main_controller_type_name);
        if (mTypeOfDialog == Type.CREATER) {
            positiveButtonText = mContext.getString(R.string.intro_add);
            title = mContext.getString(R.string.fab_controller_create_new);
        } else if (mTypeOfDialog == Type.EDITOR) {
            positiveButtonText = mContext.getString(R.string.main_controller_save);
            title = mContext.getString(R.string.main_controller_edit);
        } else {
            positiveButtonText = "error";
            title = "error";
        }
        title += " " + mExtraTitle;

        alert.setMessage(message);
        alert.setTitle(title);
        alert.setView(textField);

        alert.setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                Editable returnedString = textField.getText();
                mCreater.dialogData(returnedString.toString(), mTextToEdit);
            }
        });
        alert.setNegativeButton(mContext.getString(R.string.main_controller_cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // nothing to do here
            }
        });

        return alert;
    }
}
