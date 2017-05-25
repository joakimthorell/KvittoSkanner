package corp.skaj.foretagskvitton.view.wizard;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.model.IObserver;

public class WizardFragment extends DialogFragment {
    private IObserver mObserver;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setMessage(R.string.last_step_text)
                .setPositiveButton(R.string.last_step_approve_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (mObserver != null) {
                            mObserver.collectData();
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .create();
    }

    public void setObserver(IObserver model) {
        mObserver = model;
    }
}
