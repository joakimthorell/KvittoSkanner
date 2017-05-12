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

package corp.skaj.foretagskvitton.view;

import android.content.Context;

import com.tech.freak.wizardpager.model.AbstractWizardModel;
import com.tech.freak.wizardpager.model.ModelCallbacks;
import com.tech.freak.wizardpager.model.PageList;

import corp.skaj.foretagskvitton.services.DataHolder;
import corp.skaj.foretagskvitton.model.WizardModel;

public class WizardView extends AbstractWizardModel implements ModelCallbacks {

    private WizardModel model;

    public WizardView(Context context) {
        super(context);
    }

    @Override
    protected PageList onNewRootPageList() {

        DataHolder dataHolder = (DataHolder) mContext.getApplicationContext();
        model = new WizardModel(dataHolder.getUser(), this, dataHolder.getStrings());

        return model.getPages();
    }

    public AbstractWizardModel getWizardModel() {
        return this;
    }
}
