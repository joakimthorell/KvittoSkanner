package corp.skaj.foretagskvitton.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import corp.skaj.foretagskvitton.R;

public class WizardLastStep extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setMessage(R.string.last_step_text)
                .setPositiveButton(R.string.last_step_approve_button, null) // Here we should call for completing receipt
                .setNegativeButton(R.string.cancel, null)
                .create();
    }
}
