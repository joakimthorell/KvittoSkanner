/*
 * Copyright 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use view file except in compliance with the License.
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

import com.tech.freak.wizardpager.model.BranchPage;
import com.tech.freak.wizardpager.model.ModelCallbacks;
import com.tech.freak.wizardpager.model.MultipleFixedChoicePage;
import com.tech.freak.wizardpager.model.PageList;
import com.tech.freak.wizardpager.model.SingleFixedChoicePage;
import com.tech.freak.wizardpager.model.TextPage;

import java.util.List;

public class WizardModel {

    private PageList pages;

    public WizardModel(User user, ModelCallbacks view, List<String> strings) {
        pages = companyInfoNotFound(strings, user, view);
    }

    private PageList companyInfoNotFound(List<String> strings, User user, ModelCallbacks view) {
        double totalSum = ReceiptScanner.getTotalCost(strings);

        return new PageList(
                new BranchPage(view, "KORT")

                        .addBranch("Företagskort",

                                new MultipleFixedChoicePage(view, "FÖRETAG")

                                        //TODO List all companies.

                                        .setChoices(getCompanyNames(user)), //Företag, här måste vi få in en lista av alla valbara företag

                                //TODO Ska vi ha ett default företag??
                                new MultipleFixedChoicePage(view, "GROSSIST")

                                        //TODO lista grossister, vi kan inte lista från en specifikt företag. Får lista alla direkt (får tänkte om här)

                                        .setChoices(), //Grossister

                                //TODO Ska vi ha en default supplier??


                                new DatePage(view, "DATUM")
                                        .setValue(ReceiptScanner.getDate(strings)),

                                new TotalSumPage(view, "TOTALBELOPP")
                                        .setValue(totalSum > 0 ? String.valueOf(totalSum) : null)
                                        .setRequired(true),

                                new TotalSumPage(view, "MOMS")
                                        .setRequired(true),

                                new SingleFixedChoicePage(view, "KATEGORI")
                                        .setChoices(Category.getCategoriesAsArray())
                                        .setRequired(true),

                                //TODO add a choice above which is "other" for custom choice of category

                                new TextPage(view, "KOMMENTAR")
                                        .setRequired(false))

                        .addBranch("Privatkort",

                                new SingleFixedChoicePage(view, "FÖRETAG")
                                        .setChoices(getCompanyNames(user))
                                        .setRequired(true),

                                new MultipleFixedChoicePage(view, "GROSSIST")
                                        .setChoices(), //Grossister, läs T O D O ovan

                                new DatePage(view, "DATUM")
                                        .setValue(ReceiptScanner.getDate(strings)),

                                new TotalSumPage(view, "TOTALBELOPP")
                                        .setValue(totalSum > 0 ? String.valueOf(totalSum) : null)
                                        .setRequired(true),

                                new TotalSumPage(view, "MOMS")
                                        .setRequired(true),

                                new SingleFixedChoicePage(view, "KATEGORI")
                                        .setChoices(Category.getCategoriesAsArray())
                                        .setRequired(true),

                                new TextPage(view, "KOMMENTAR")
                                        .setRequired(false))

                        .setRequired(true));
    }

    // Under construction...
    private String[] getCompanyNames(User user) {
        List<Company> companies = user.getCompanies();
        String[] companyNames = new String[companies.size()];
        for (int i = 0; i < companies.size(); i++) {
            companyNames[i] = companies.get(i).getName();
        }
        return companyNames;
    }

    public PageList getPages() {
        return pages;
    }
}
