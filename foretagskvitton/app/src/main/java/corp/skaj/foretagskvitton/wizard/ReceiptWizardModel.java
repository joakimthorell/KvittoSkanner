package corp.skaj.foretagskvitton.wizard;

import android.content.Context;

import com.tech.freak.wizardpager.model.AbstractWizardModel;
import com.tech.freak.wizardpager.model.PageList;

/**
 * Created by mattsson on 2017-05-02.
 */

public class ReceiptWizardModel extends AbstractWizardModel {
    public ReceiptWizardModel(Context context) {
        super(context);
    }

    @Override
    protected PageList onNewRootPageList() {
        return null;
    }
}
