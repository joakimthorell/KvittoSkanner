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

package corp.skaj.foretagskvitton.view.wizard;

import com.tech.freak.wizardpager.model.ModelCallbacks;
import com.tech.freak.wizardpager.model.PageList;
import com.tech.freak.wizardpager.model.SingleFixedChoicePage;
import com.tech.freak.wizardpager.model.TextPage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import corp.skaj.foretagskvitton.model.Card;
import corp.skaj.foretagskvitton.model.Category;
import corp.skaj.foretagskvitton.model.Company;
import corp.skaj.foretagskvitton.model.IObserver;
import corp.skaj.foretagskvitton.model.Supplier;
import corp.skaj.foretagskvitton.services.textcollector.TextCollector;
import corp.skaj.foretagskvitton.model.User;

public class WizardPageBuilder {
    private List<IObserver> observers;
    private PageList pages;

    public WizardPageBuilder(User user, ModelCallbacks view, List<String> strings) {
        pages = companyInfoNotFound(strings, user, view);
        observers = new ArrayList<>();
    }

    private PageList companyInfoNotFound(List<String> strings, User user, ModelCallbacks view) {
        double totalSum = TextCollector.getTotalSum(strings);
        int nCompanies = user.getCompanies().size();
        String date = TextCollector.getDate(strings);
        String card = TextCollector.getCard(strings);
        Company company = findCompany(nCompanies, card, user);
        String[] suppliers = getSuppliers(user.getSuppliers());

        if (user.getCompanies().size() == 1 && suppliers.length == 0) {
            return oneCompanyNoSupplierWizard(view, totalSum, company, date);
        }
        if (user.getCompanies().size() == 1) {
            return oneCompanyWizard(view, totalSum, company, suppliers, date);
        }
        if (suppliers.length == 0) {
            return noSupplierWizard(view, user, totalSum, company, date);
        } else {
            return new PageList(
                    new SingleFixedChoicePage(view, WizardConstants.CARD)
                            .setChoices(WizardConstants.PRIVATE, WizardConstants.COMPANY)
                            .setValue(company == null ? null : WizardConstants.COMPANY)
                            .setRequired(true),

                    // När ett företag är i förväg markerat är det ibland inte en "boll" i radio-knappen. Bugg i WizardPager.
                    new SingleFixedChoicePage(view, WizardConstants.COMPANY)
                            .setChoices(getCompanyNames(user))
                            .setValue(company == null ? null : company.getName())
                            .setRequired(true),

                    new SingleFixedChoicePage(view, WizardConstants.SUPPLIER)
                            .setChoices(suppliers),

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
    }

    private PageList oneCompanyNoSupplierWizard(ModelCallbacks view, double totalSum, Company company, String date) {
        return new PageList(
                new SingleFixedChoicePage(view, WizardConstants.CARD)
                        .setChoices(WizardConstants.PRIVATE, WizardConstants.COMPANY)
                        .setValue(company == null ? null : WizardConstants.COMPANY)
                        .setRequired(true),

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

    private PageList oneCompanyWizard(ModelCallbacks view, double totalSum, Company company, String[] suppliers, String date) {
        return new PageList(
                new SingleFixedChoicePage(view, WizardConstants.CARD)
                        .setChoices(WizardConstants.PRIVATE, WizardConstants.COMPANY)
                        .setValue(company == null ? null : WizardConstants.COMPANY)
                        .setRequired(true),

                new SingleFixedChoicePage(view, WizardConstants.SUPPLIER)
                        .setChoices(suppliers),

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


    private PageList noSupplierWizard(ModelCallbacks view, User user, double totalSum, Company company, String date) {
        return new PageList(
                new SingleFixedChoicePage(view, WizardConstants.CARD)
                        .setChoices(WizardConstants.PRIVATE, WizardConstants.COMPANY)
                        .setValue(company == null ? null : WizardConstants.COMPANY)
                        .setRequired(true),

                // När ett företag är i förväg markerat är det ibland inte en "boll" i radio-knappen. Bugg i WizardPager.
                new SingleFixedChoicePage(view, WizardConstants.COMPANY)
                        .setChoices(getCompanyNames(user))
                        .setValue(company == null ? null : company.getName())
                        .setRequired(true),

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

    private String[] getSuppliers(List<Supplier> suppliers) {
        String[] supplierNames = new String[suppliers.size()];
        for (int i = 0; i < supplierNames.length; i++) {
            supplierNames[i] = suppliers.get(i).getName();
        }
        return supplierNames;
    }

    private Company findCompany(int nCompanies, String sCard, User user) {
        if (nCompanies > 1 && sCard != null) {
            Card card = getCard(sCard);
            return user.getCompany(card);

        } else if (nCompanies == 1) {
            return user.getCompanies().get(0);
        }
        return null;
    }

    private Card getCard(String sCard) {
        try {
            return new Card(Integer.parseInt(sCard));
        } catch (Exception e) {
            System.out.println("sCard was no int " + this.toString());
        }
        return null;
    }

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

