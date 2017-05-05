package corp.skaj.foretagskvitton.model;

import android.support.v4.app.Fragment;

import com.tech.freak.wizardpager.model.ModelCallbacks;
import com.tech.freak.wizardpager.model.TextPage;

import corp.skaj.foretagskvitton.view.TotalSumFragment;

public class TotalSumPage extends TextPage {

    public TotalSumPage(ModelCallbacks callbacks, String title) {
        super(callbacks, title);
    }

    @Override
    public Fragment createFragment() {
        return TotalSumFragment.create(getKey());
    }
}
