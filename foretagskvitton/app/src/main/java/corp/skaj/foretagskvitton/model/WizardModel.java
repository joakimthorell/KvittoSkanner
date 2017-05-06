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

import com.tech.freak.wizardpager.model.AbstractWizardModel;
import com.tech.freak.wizardpager.model.BranchPage;
import com.tech.freak.wizardpager.model.MultipleFixedChoicePage;
import com.tech.freak.wizardpager.model.PageList;
import com.tech.freak.wizardpager.model.SingleFixedChoicePage;
import com.tech.freak.wizardpager.model.TextPage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import corp.skaj.foretagskvitton.activities.WizardActivity;
import corp.skaj.foretagskvitton.controllers.DataHolder;
import corp.skaj.foretagskvitton.services.ReceiptScanner;


public class WizardModel extends AbstractWizardModel {
    private Map<String, String> data;

    public WizardModel(Context context) {
        super(context);
        data = new HashMap<>();
    }

    @Override
    protected PageList onNewRootPageList() {
        // Necessary because constructor runs super constructor which runs this method before strings are scanned.
        DataHolder dataHolder = (DataHolder)mContext.getApplicationContext();
        WizardActivity wa = (WizardActivity) mContext;
        List<String> strings = dataHolder.getStrings();

        if (collectCompany(strings) == null) {
            return companyInfoNotFound(strings);
        } else {
            return companyInfoFound(strings);
        }
    }

    private PageList companyInfoNotFound(List<String> strings) {
        double totalSum = ReceiptScanner.getTotalCost(strings);

        return new PageList(
                new BranchPage(this, "Skapa ny post")

                        .addBranch("Företagskort",

                                new MultipleFixedChoicePage(this, "Företag")

                                        //TODO List all companies.

                                        .setChoices(), //Företag, här måste vi få in en lista av alla valbara företag

                                new MultipleFixedChoicePage(this, "Grossist")

                                        .setChoices() //Grossister, här måste vi få in en valbar lista med grossister

                                        //TODO List all suppliers.

                                        .setChoices("LISTA MED GROSSISTER"), //Grossister


                                new DatePage(this, "Datum")
                                        .setValue(ReceiptScanner.getDate(strings)),

                                new TotalSumPage(this, "Total belopp")
                                        .setValue(totalSum > 0 ? String.valueOf(totalSum) : null)
                                        .setRequired(true),

                                new SingleFixedChoicePage(this, "Kategori")
                                        .setChoices("Resor", "Mat", "Bensin", "Hotell", "Frakt")
                                        .setRequired(true),

                                new TextPage(this, "Kommentar")
                                        .setRequired(false))

                        .addBranch("Privatkort",

                                new MultipleFixedChoicePage(this, "Företag")
                                        .setChoices(),

                                new MultipleFixedChoicePage(this, "Grossist")
                                        .setChoices(), //Grossister

                                new DatePage(this, "Datum")
                                        .setValue(ReceiptScanner.getDate(strings)),

                                new TotalSumPage(this, "Total belopp")
                                        .setValue(totalSum > 0 ? String.valueOf(totalSum) : null)
                                        .setRequired(true),

                                new SingleFixedChoicePage(this, "Kategori")
                                        .setChoices("Resor", "Mat", "Bensin", "Hotell", "Frakt")
                                        .setRequired(true),

                                new TextPage(this, "Kommentar")
                                        .setRequired(false))

                        .setRequired(true));
    }

    private PageList companyInfoFound(List<String> strings) {
        double totalSum = ReceiptScanner.getTotalCost(strings);

        //TODO If cardnumber found = save purchase in matching company.

        return new PageList(

                new BranchPage(this, "Skapa ny post"), //Kolla upp om detta verkligen ser rätt ut 

                new DatePage(this, "Datum")
                        .setValue(ReceiptScanner.getDate(strings)),

                //TODO gör en kalender där man får välja, return mCurrentPageSequence;om vi har tid över

                new TotalSumPage(this, "Total belopp")
                        .setValue(totalSum > 0 ? String.valueOf(totalSum) : null)
                        .setRequired(true),

                new SingleFixedChoicePage(this, "Kategori")
                        .setChoices("Resor", "Mat", "Bensin", "Hotell", "Frakt")
                        .setRequired(true),

                new TextPage(this, "Kommentar")
                        .setRequired(false));
    }

    private void collectData() {

        //TODO Collect and save all data from currentPageSequence in hashmap.
        // wizardModel.getCurrentPageSequence().get(0).getData();
    }

    private Company collectCompany(List<String> strings) {

        //TODO Get User globally, return company matching cardnumber.

        return null;
    }
}