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

import com.tech.freak.wizardpager.model.ModelCallbacks;
import com.tech.freak.wizardpager.model.MultipleFixedChoicePage;
import com.tech.freak.wizardpager.model.PageList;
import com.tech.freak.wizardpager.model.SingleFixedChoicePage;
import com.tech.freak.wizardpager.model.TextPage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WizardModel {
    private List<IObserver> observers;
    private PageList pages;

    public WizardModel(User user, ModelCallbacks view, List<String> strings) {
        pages = companyInfoNotFound(strings, user, view);
        observers = new ArrayList<>();
    }

    private PageList companyInfoNotFound(List<String> strings, User user, ModelCallbacks view) {
        double totalSum = TextCollector.getPrice(strings);
        String date = TextCollector.getDate(strings);

        String cardNum = TextCollector.getCard(strings);
        Company foundCompany = null;
        if (user.getCompanies().size() > 1 && cardNum != null) {
            Card foundCard = new Card(Integer.parseInt(cardNum));
            foundCompany = user.getCompany(foundCard);
        } else if (user.getCompanies().size() == 1) {
            foundCompany = user.getCompanies().get(0);
        } else {
            // let it be null
        }

        return new PageList(
                new SingleFixedChoicePage(view, WizardConstants.CARD)
                        .setChoices("Privat", "Företag")
                        .setValue(foundCompany == null ? null : "Företag")
                        .setRequired(true),

                // TODO företag fungerar men, MEN när den står som i klickan är de inte en boll i radio knappen utan den är bara rosa.
                new SingleFixedChoicePage(view, WizardConstants.COMPANY)
                        .setChoices(getCompanyNames(user))
                        .setValue(foundCompany == null ? null : foundCompany.getName())
                        .setRequired(true),

                new MultipleFixedChoicePage(view, WizardConstants.SUPPLIER)
                        // TODO lista alla grossister
                        .setChoices(),

                new DatePage(view, WizardConstants.DATE)
                        .setValue(date == null ? getCurrentDate() : date)
                        .setRequired(true),

                new TotalSumPage(view, WizardConstants.TOTAL)
                        .setValue(totalSum > 0 ? String.valueOf(totalSum) : null)
                        .setRequired(true),

                new TotalSumPage(view, WizardConstants.VAT)
                        .setRequired(true),

                new SingleFixedChoicePage(view, WizardConstants.CATEGORY)
                        .setChoices(Category.getCategoriesArray())
                        .setRequired(true),
                //TODO add a choice above which is "other" for custom choice of category
                new TextPage(view, WizardConstants.COMMENT)
                        .setRequired(false));
    }

    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    public void collectData() {
        notifyController();
    }

    private void notifyController() {
        for (IObserver io : observers) {
            io.onDataChange();
        }
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

    private String getCurrentDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public PageList getPages() {
        return pages;
    }
}
