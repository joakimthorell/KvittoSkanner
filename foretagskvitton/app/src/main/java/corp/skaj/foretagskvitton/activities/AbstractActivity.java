package corp.skaj.foretagskvitton.activities;

import android.support.v7.app.AppCompatActivity;

import corp.skaj.foretagskvitton.model.IData;

public abstract class AbstractActivity extends AppCompatActivity {

    protected IData getDataHandler() {
        return (IData) getApplicationContext();
    }
}
