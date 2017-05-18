package corp.skaj.foretagskvitton.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.controllers.MainController;
import corp.skaj.foretagskvitton.model.IData;
import corp.skaj.foretagskvitton.view.ArchiveAdapter;
import corp.skaj.foretagskvitton.view.ListFragment;

public class GraphActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        initBottomBar(CHARTS_ID, this);

        IData dataHandler = (IData) getApplicationContext();


    }
}
