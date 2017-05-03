package corp.skaj.foretagskvitton.controllers;

import android.content.Context;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.services.ReceiptScanner;
import corp.skaj.foretagskvitton.wizard.ReceiptWizardModel;

public class WizardController {

    private ReceiptWizardModel receiptWizardModel;
    private List<String> strings;
    private Company company;
    private double totalSum;
    private String date;

    public WizardController(Context context, List<String> strings) {
        this.strings = strings;
        totalSum = getTotCost();
        date = getDate();
        company = null; // TODO temporary

        this.receiptWizardModel = new ReceiptWizardModel(context, company, totalSum, date);
    }

    private boolean getCardNum() {
        return !ReceiptScanner.getCardNumber(strings).equals("0000");
    }

    private String getDate() {
        if (ReceiptScanner.getDate(strings) == null) {
            return null;
        }
        return ReceiptScanner.getDate(strings);
    }

    private double getTotCost() {
        if (ReceiptScanner.getTotalCost(strings) == 0.0 ||
                ReceiptScanner.getTotalCost(strings) == 0) {
            return ReceiptScanner.getTotalCost(strings);
        }
        return ReceiptScanner.getTotalCost(strings);
    }

    public ReceiptWizardModel getReceiptWizardModel() {
        return receiptWizardModel;
    }
}
