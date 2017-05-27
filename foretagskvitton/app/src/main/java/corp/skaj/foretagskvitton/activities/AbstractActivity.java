package corp.skaj.foretagskvitton.activities;

import android.support.v7.app.AppCompatActivity;

import corp.skaj.foretagskvitton.model.IDataHandler;

public abstract class AbstractActivity extends AppCompatActivity {

    protected IDataHandler getDataHandler() {
        return (IDataHandler) getApplicationContext();
    }
}
