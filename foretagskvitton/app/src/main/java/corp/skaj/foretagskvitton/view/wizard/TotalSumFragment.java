package corp.skaj.foretagskvitton.view.wizard;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.InputType;

import com.tech.freak.wizardpager.ui.TextFragment;

public class TotalSumFragment extends TextFragment {

    public static TotalSumFragment create(String key) {
        Bundle args = new Bundle();
        args.putString(ARG_KEY, key);
        TotalSumFragment f = new TotalSumFragment();
        f.setArguments(args);
        return f;
    }

    @SuppressLint("InlinedApi")
    @Override
    protected void setInputType() {
        mEditTextInput.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
    }
}