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
import com.tech.freak.wizardpager.model.NumberPage;
import com.tech.freak.wizardpager.model.Page;
import com.tech.freak.wizardpager.model.PageList;
import com.tech.freak.wizardpager.model.SingleFixedChoicePage;
import com.tech.freak.wizardpager.model.TextPage;

import java.util.ArrayList;
import java.util.List;

import corp.skaj.foretagskvitton.services.ReceiptScanner;


public class WizardModel extends AbstractWizardModel {
    private List<String> strings;

    public WizardModel(Context context, List<String> strings) {
        super(context);

        this.strings = strings;

        PageList list = onNewRootPageList();
        List<Page> superList = super.getCurrentPageSequence();
        superList.clear();
        superList.addAll(list);

        //TODO Get relevant info från strings + build wizard sequence
    }

    @Override
    protected PageList onNewRootPageList() {

        if (collectCompanyName(strings) == null) {
            return companyInfoNotFound();
        } else {
            return companyInfoFound();
        }
    }

    PageList companyInfoNotFound() {
        //System.out.println("TOTALSUMMAN ÄR " + String.valueOf(totalSum) + " KRONOR");
        //System.out.println("DATUMET SOM HITTAS ÄR " + date);
        double totalSum = ReceiptScanner.getTotalCost(strings);

        return new PageList(
                new BranchPage(this, "Skapa ny post")

                        .addBranch("Företagskort",

                                new MultipleFixedChoicePage(this, "Företag")

                                        //TODO List all companies.

                                        .setChoices("LISTA MED FÖRETAG"), //Företag, här måste vi få in en lista av alla valbara företag

                                new MultipleFixedChoicePage(this, "Grossist")

                                        //TODO List all suppliers.

                                        .setChoices("LISTA MED GROSSISTER"), //Grossister

                                new TextPage(this, "Datum")
                                        .setValue(ReceiptScanner.getDate(strings)),

                                //TODO gör en kalender där man får välja, om vi har tid över

                                new NumberPage(this, "Total belopp")
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

                                new TextPage(this, "Datum")
                                        .setValue(ReceiptScanner.getDate(strings)),

                                //TODO gör en kalender där man får välja, om vi har tid över

                                new NumberPage(this, "Total belopp")
                                        .setValue(totalSum > 0 ? String.valueOf(totalSum) : null)
                                        .setRequired(true),

                                new SingleFixedChoicePage(this, "Kategori")
                                        .setChoices("Resor", "Mat", "Bensin", "Hotell", "Frakt")
                                        .setRequired(true),

                                new TextPage(this, "Kommentar")
                                        .setRequired(false))

                        .setRequired(true));

    }

    PageList companyInfoFound() {
        double totalSum = ReceiptScanner.getTotalCost(strings);

        //TODO If cardnumber found = save purchase in matching company

        return new PageList(

                new BranchPage(this, "Skapa ny post"),

                new TextPage(this, "Datum")
                        .setValue(ReceiptScanner.getDate(strings)),

                //TODO gör en kalender där man får välja, return mCurrentPageSequence;om vi har tid över

                new NumberPage(this, "Total belopp")
                        .setValue(totalSum > 0 ? String.valueOf(totalSum) : null)
                        .setRequired(true),

                new SingleFixedChoicePage(this, "Kategori")
                        .setChoices("Resor", "Mat", "Bensin", "Hotell", "Frakt")
                        .setRequired(true),

                new TextPage(this, "Kommentar")
                        .setRequired(false));
    }

    private Company collectCompanyName(List<String> strings) {
        return (new User("TEMP_USER").getCompany(Integer.parseInt(ReceiptScanner.getCardNumber(strings))));

        //TODO Get User globally.

    }




}
