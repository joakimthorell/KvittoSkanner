package corp.skaj.foretagskvitton.wizard;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import corp.skaj.foretagskvitton.R;

public class WizardBottomNavigation extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wizard_bottom_navigation, container, false);
        return view;
    }


}
