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
import android.os.Parcelable;

import com.tech.freak.wizardpager.model.AbstractWizardModel;
import com.tech.freak.wizardpager.model.BranchPage;
import com.tech.freak.wizardpager.model.MultipleFixedChoicePage;
import com.tech.freak.wizardpager.model.NumberPage;
import com.tech.freak.wizardpager.model.Page;
import com.tech.freak.wizardpager.model.PageList;
import com.tech.freak.wizardpager.model.SingleFixedChoicePage;
import com.tech.freak.wizardpager.model.TextPage;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.ConcurrentMap;

import corp.skaj.foretagskvitton.controllers.WizardController;
import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.view.WizardLastStep;

public class WizardModel extends AbstractWizardModel {

    String company;
    double totalSum;
    String date;

    public WizardModel(Context context, Company company, double totalSum, String date) {
        super(context);
        this.company = company == null ? null : company.getName();
        this.totalSum = totalSum;
        this.date = date;
    }

    @Override
    protected PageList onNewRootPageList() {
        if (company == null) {
            return companyInfoNotFound();
        } else {
            return companyInfoFound();
        }
    }

    PageList companyInfoNotFound() {

        System.out.println("TOTALSUMMAN ÄR " + String.valueOf(totalSum) + " KRONOR");
        System.out.println("DATUMET SOM HITTAS ÄR " + date);

        return new PageList(
                new BranchPage(this, "Skapa ny post")

                        .addBranch("Företagskort",

                                new MultipleFixedChoicePage(this, "Företag")
                                        .setChoices(), //Företag

                                new MultipleFixedChoicePage(this, "Grossist")
                                        .setChoices(), //Grossister

                                new TextPage(this, "Datum")
                                        .setValue(date),

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
                                        .setValue(date),

                                //TODO gör en kalender där man får välja, om vi har tid över

                                new NumberPage(this, "Total belopp")
                                        .setValue(totalSum > 0 ? String.valueOf(totalSum) : null)
                                        .setRequired(true),

                                new SingleFixedChoicePage(this, "Kategori")
                                        .setChoices("Resor", "Mat", "Bensin", "Hotell", "Frakt")
                                        .setRequired(true),

                                new TextPage(this, "Kommentar")
                                        .setRequired(false)));

    }

    PageList companyInfoFound() {

        return new PageList(

                new BranchPage(this, "Skapa ny post"),

                new TextPage(this, "Datum")
                        .setValue(date),

                //TODO gör en kalender där man får välja, om vi har tid över

                new NumberPage(this, "Total belopp")
                        .setValue(totalSum > 0 ? String.valueOf(totalSum) : null)
                        .setRequired(true),

                new SingleFixedChoicePage(this, "Kategori")
                        .setChoices("Resor", "Mat", "Bensin", "Hotell", "Frakt")
                        .setRequired(true),

                new TextPage(this, "Kommentar")
                        .setRequired(false));

    }

}
