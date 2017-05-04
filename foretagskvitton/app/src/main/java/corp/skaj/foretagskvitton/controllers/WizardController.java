package corp.skaj.foretagskvitton.controllers;

import android.content.Context;

import java.util.List;

import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.services.ReceiptScanner;
import corp.skaj.foretagskvitton.model.WizardModel;

public class WizardController {
    private WizardModel wizardModel;
    private List<String> strings;
    private Company company;
    private double totalSum;
    private String date;

    public WizardController(Context context, List<String> strings) {
        this.strings = strings;
        totalSum = getTotCost();
        date = getDate();
        company = null; // TODO temporary

        this.wizardModel = new WizardModel(context, company, totalSum, date);
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

    public WizardModel getWizardModel() {
        return wizardModel;
    }
}
