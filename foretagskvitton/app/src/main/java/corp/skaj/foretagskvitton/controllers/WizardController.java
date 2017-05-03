package corp.skaj.foretagskvitton.controllers;

import android.content.Context;

import java.util.Date;
import java.util.List;

import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.services.ReceiptScanner;
import corp.skaj.foretagskvitton.wizard.ReceiptWizardModel;

public class WizardController {

    ReceiptWizardModel receiptWizardModel;
    ReceiptScanner receiptScanner;
    List<String> strings;
    Company company;
    double totalSum;
    Date date;

    public WizardController (Context context, List<String> strings){
        this.receiptWizardModel = new ReceiptWizardModel(context, company, totalSum, date);
        this.receiptScanner = new ReceiptScanner();
        this.strings = strings;
    }

    public boolean getCardNum(List<String> strings){
        return !receiptScanner.getCardNumber(strings).equals("0000");
    }

    public String getDate (List<String> strings){
        if(receiptScanner.getDate(strings) == null) {
            return receiptScanner.getDate(strings);
        }
        return receiptScanner.getDate(strings);
    }
    
    public double getTotCost(List<String> strings){
        if(receiptScanner.getTotalCost(strings) == 0.0 ||
                receiptScanner.getTotalCost(strings) == 0){
            return receiptScanner.getTotalCost(strings);
        }
        return receiptScanner.getTotalCost(strings);
    }
}
