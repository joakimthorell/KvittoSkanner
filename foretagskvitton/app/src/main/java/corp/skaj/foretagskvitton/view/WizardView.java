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
import android.os.Bundle;

import com.tech.freak.wizardpager.model.AbstractWizardModel;
import com.tech.freak.wizardpager.model.ModelCallbacks;
import com.tech.freak.wizardpager.model.PageList;

import java.util.List;

import corp.skaj.foretagskvitton.model.IObserver;
import corp.skaj.foretagskvitton.model.User;
import corp.skaj.foretagskvitton.model.WizardModel;
import corp.skaj.foretagskvitton.model.IData;

public class WizardView extends AbstractWizardModel implements ModelCallbacks {
    private WizardModel model;

    public WizardView(IObserver observer, Context context) {
        super(context);
        model.addObserver(observer);
    }

    @Override
    protected PageList onNewRootPageList() {
        IData dataHandler = (IData)mContext.getApplicationContext();
        User user = dataHandler.readData(User.class.getName(), User.class);
        List<String> strings = (List<String>) dataHandler.readData("mStrings", List.class);
        model = new WizardModel(user, this, strings);
        return model.getPages();
    }

    public AbstractWizardModel getWizardView() {
        return this;
    }

    public WizardModel getWizardModel() {
        return model;
    }

    public Bundle getWizardData() {
        return super.save();
    }


}
