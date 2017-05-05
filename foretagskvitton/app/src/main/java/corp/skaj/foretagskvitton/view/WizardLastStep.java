package corp.skaj.foretagskvitton.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.controllers.DataHolder;
import corp.skaj.foretagskvitton.controllers.IUpdateUser;

public class WizardLastStep extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setMessage(R.string.last_step_text)
                .setPositiveButton(R.string.last_step_approve_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!(getContext() instanceof IUpdateUser)) {
                            throw new ClassCastException("Activity must implement IUpdateUser");
                        }
                        IUpdateUser updateUser = (IUpdateUser)getContext();
                        updateUser.updateUser();
                    }
                }) // Here we should call for completing receipt
                .setNegativeButton(R.string.cancel, null)
                .create();
    }
}
