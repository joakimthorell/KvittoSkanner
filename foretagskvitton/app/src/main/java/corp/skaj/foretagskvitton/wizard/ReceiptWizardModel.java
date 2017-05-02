package corp.skaj.foretagskvitton.wizard;

import android.content.Context;

import com.tech.freak.wizardpager.model.AbstractWizardModel;
import com.tech.freak.wizardpager.model.BranchPage;
import com.tech.freak.wizardpager.model.PageList;

public class ReceiptWizardModel extends AbstractWizardModel {
    public ReceiptWizardModel(Context context) {
        super(context);
    }

    @Override
    protected PageList onNewRootPageList() {
        // TODO add all the option fragments
        return null;
    }
}
