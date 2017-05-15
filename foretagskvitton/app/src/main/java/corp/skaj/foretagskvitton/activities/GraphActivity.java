package corp.skaj.foretagskvitton.activities;

import android.os.Bundle;

import corp.skaj.foretagskvitton.R;

public class GraphActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        initBottomBar(CHARTS_ID, this);
    }
}
