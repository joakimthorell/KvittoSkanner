package corp.skaj.foretagskvitton.controllers;

import android.content.Context;

import corp.skaj.foretagskvitton.wizard.ReceiptWizardModel;

/**
 * Created by kevinbrunstrom on 2017-05-03.
 */

public class WizardController {

    ReceiptWizardModel receiptWizardModel;

    public WizardController (Context context){
        this.receiptWizardModel = new ReceiptWizardModel(context);
    }

}
