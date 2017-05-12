package corp.skaj.foretagskvitton.controllers;

import com.tech.freak.wizardpager.model.AbstractWizardModel;
import com.tech.freak.wizardpager.model.Page;

import java.util.List;


public interface IWizardController {
    void updateConsumePageSelectedEvent(boolean state);

    void updateEditingAfterReview(boolean state);

    AbstractWizardModel getWizardModel();

    List<Page> getCurrentPageSequence();
}
