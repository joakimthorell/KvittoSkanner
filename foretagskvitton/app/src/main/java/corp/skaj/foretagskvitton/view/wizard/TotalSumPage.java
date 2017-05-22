package corp.skaj.foretagskvitton.view.wizard;

import android.support.v4.app.Fragment;

import com.tech.freak.wizardpager.model.ModelCallbacks;
import com.tech.freak.wizardpager.model.TextPage;

public class TotalSumPage extends TextPage {

    public TotalSumPage(ModelCallbacks callbacks, String title) {
        super(callbacks, title);
    }

    public Fragment createFragment() {
        return TotalSumFragment.create(getKey());
    }
}