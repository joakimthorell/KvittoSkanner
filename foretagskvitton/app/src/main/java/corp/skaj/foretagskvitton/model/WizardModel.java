/*
 * Copyright 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package corp.skaj.foretagskvitton.model;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import com.tech.freak.wizardpager.model.AbstractWizardModel;
import com.tech.freak.wizardpager.model.BranchPage;
import com.tech.freak.wizardpager.model.MultipleFixedChoicePage;
import com.tech.freak.wizardpager.model.Page;
import com.tech.freak.wizardpager.model.PageList;
import com.tech.freak.wizardpager.model.SingleFixedChoicePage;
import com.tech.freak.wizardpager.model.TextPage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import corp.skaj.foretagskvitton.services.DataHolder;

public class WizardModel extends AbstractWizardModel {

    public WizardModel(Context context) {
        super(context);
    }

    @Override
    protected PageList onNewRootPageList() {
        // Necessary because constructor runs super constructor which runs this method before strings are scanned.
        DataHolder dataHolder = (DataHolder) mContext.getApplicationContext();
        List<String> strings = dataHolder.getStrings();

        if (collectCompany(strings, dataHolder) == null) {
            return companyInfoNotFound(strings, dataHolder);
        } else {
            return companyInfoFound(strings, dataHolder);
        }
    }

    private PageList companyInfoNotFound(List<String> strings, DataHolder dataHolder) {
        double totalSum = ReceiptScanner.getTotalCost(strings);

        return new PageList(
                new BranchPage(this, "KORT")

                        .addBranch("Företagskort",

                                new MultipleFixedChoicePage(this, "FÖRETAG")

                                        //TODO List all companies.

                                        .setChoices(getCompanyNames(dataHolder)), //Företag, här måste vi få in en lista av alla valbara företag

                                //TODO Ska vi ha ett default företag??
                                new MultipleFixedChoicePage(this, "GROSSIST")

                                        //TODO lista grossister, vi kan inte lista från en specifikt företag. Får lista alla direkt (får tänkte om här)

                                        .setChoices(), //Grossister

                                //TODO Ska vi ha en default supplier??


                                new DatePage(this, "DATUM")
                                        .setValue(ReceiptScanner.getDate(strings)),

                                new TotalSumPage(this, "TOTALBELOPP")
                                        .setValue(totalSum > 0 ? String.valueOf(totalSum) : null)
                                        .setRequired(true),

                                new TotalSumPage(this, "MOMS")
                                        .setRequired(true),

                                new SingleFixedChoicePage(this, "KATEGORI")
                                        .setChoices(Category.getCategoriesAsArray())
                                        .setRequired(true),

                                //TODO add a choice above which is "other" for custom choice of category

                                new TextPage(this, "KOMMENTAR")
                                        .setRequired(false))

                        .addBranch("Privatkort",

                                new SingleFixedChoicePage(this, "FÖRETAG")
                                        .setChoices(getCompanyNames(dataHolder))
                                        .setRequired(true),

                                new MultipleFixedChoicePage(this, "GROSSIST")
                                        .setChoices(), //Grossister, läs T O D O ovan

                                new DatePage(this, "DATUM")
                                        .setValue(ReceiptScanner.getDate(strings)),

                                new TotalSumPage(this, "TOTALBELOPP")
                                        .setValue(totalSum > 0 ? String.valueOf(totalSum) : null)
                                        .setRequired(true),

                                new TotalSumPage(this, "MOMS")
                                        .setRequired(true),

                                new SingleFixedChoicePage(this, "KATEGORI")
                                        .setChoices(Category.getCategoriesAsArray())
                                        .setRequired(true),

                                new TextPage(this, "KOMMENTAR")
                                        .setRequired(false))

                        .setRequired(true));
    }

    private PageList companyInfoFound(List<String> strings, DataHolder dataHolder) {
        double totalSum = ReceiptScanner.getTotalCost(strings);

        //TODO If cardnumber found = save purchase in matching company.

        return new PageList(

                new BranchPage(this, "KORT"), //Kolla upp om detta verkligen ser rätt ut

                new DatePage(this, "DATUM")
                        .setValue(ReceiptScanner.getDate(strings)),

                new TotalSumPage(this, "TOTALBELOPP")
                        .setValue(totalSum > 0 ? String.valueOf(totalSum) : null)
                        .setRequired(true),

                new TotalSumPage(this, "MOMS")
                        .setRequired(true),

                new SingleFixedChoicePage(this, "KATEGORI")
                        .setChoices(Category.getCategoriesAsArray())
                        .setRequired(true),

                new TextPage(this, "KOMMENTAR")
                        .setRequired(false));
    }

    // Under construction...
    public Map<String, String> collectData() {
        Map<String, String> data = new HashMap<>();
        List<Page> pageSequence = getCurrentPageSequence();
        for (int i = 0; i < pageSequence.size(); i++) {
            data.put(pageSequence.get(i).getKey(), pageSequence.get(i).getData().toString());
        }
        return data;
    }

    private Company collectCompany(List<String> strings, DataHolder dataHolder) {
        try {
            return dataHolder.getUser().getCompany(new Card(Integer.parseInt(ReceiptScanner.getCardNumber(strings))));
        } catch (Exception e) {
            return null;
        }
    }

    private String[] getCompanyNames(DataHolder dataHolder) {
        List<Company> companies = dataHolder.getUser().getCompanies();
        String[] companyNames = new String[companies.size()];
        for (int i = 0; i < companies.size(); i++) {
            companyNames[i] = companies.get(i).getName();
        }
        return companyNames;
    }

    public void addNewPost(Map<String, String> data, DataHolder dataHolder) {
        // this needs loooooove
        String preKey = fixBundleStrings(data.get("KORT")) + ":";
        String s = data.get(preKey + "KATEGORI");
        System.out.println(s);
        Category category = Category.convertStringToCategory(fixBundleStrings(data.get(preKey+"KATEGORI")));
        double total = Double.parseDouble(data.get("TOTALBELOPP").toString());
        double tax = Double.parseDouble(data.get("MOMS").toString());

        Product product = new Product("WHOLE_RECEIPT", category, total, tax);
        Comment c = new Comment(data.get("KOMMENTAR").toString());
        product.addComment(c);

        Calendar cal;
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(data.get("DATUM").toString());
            cal = new GregorianCalendar();
            cal.setTime(date);
        } catch (ParseException e) {
            cal = Calendar.getInstance();
        }

        Uri URI = dataHolder.getURI();

        Receipt receipt = new Receipt(product, cal, total, URI);
        Company company = dataHolder.getUser().getCompany(data.get("FÖRETAG").toString());
        Supplier supplier = company.getSupplier(data.get("GROSSIST").toString());
        if (data.containsKey("Företagskort")) {
            Purchase purchase = new CompanyPurchase(receipt, supplier);
            company.getEmployees().get(0).addPurchase(purchase);
        } else {
            Purchase purchase = new PrivatePurchase(receipt, supplier);
            company.getEmployees().get(0).addPurchase(purchase);
        }
        System.out.println("Receipt saved to " + company.getName() + ". Complete!");
    }

    private String fixBundleStrings(String s) {
        s.replace("Bundle[{_=", "");
        s.replace("}]", "");
        return s;
    }
}
