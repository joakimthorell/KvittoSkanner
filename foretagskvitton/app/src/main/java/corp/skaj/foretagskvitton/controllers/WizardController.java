package corp.skaj.foretagskvitton.controllers;

import android.content.Context;

import java.util.List;

import corp.skaj.foretagskvitton.services.ReceiptScanner;
import corp.skaj.foretagskvitton.wizard.ReceiptWizardModel;

public class WizardController {

    ReceiptWizardModel receiptWizardModel;
    ReceiptScanner receiptScanner;
    List<String> strings;

    public WizardController (Context context, List<String> strings ){
        this.receiptWizardModel = new ReceiptWizardModel(context);
        this.receiptScanner = new ReceiptScanner();
        this.strings = strings;
    }

    private boolean gotCardNum(List<String> strings){
        return !receiptScanner.getCardNumber(strings).equals("0000");
    }

    private String gotDate (List<String> strings){
        if(receiptScanner.getDate(strings) == null) {
            return receiptScanner.getDate(strings);
        }
        return receiptScanner.getDate(strings);
    }
    
    private double getTotCost(List<String> strings){
        if(receiptScanner.getTotalCost(strings) == 0.0 ||
                receiptScanner.getTotalCost(strings) == 0){
            return receiptScanner.getTotalCost(strings);
        }
        return receiptScanner.getTotalCost(strings);
    }
}
