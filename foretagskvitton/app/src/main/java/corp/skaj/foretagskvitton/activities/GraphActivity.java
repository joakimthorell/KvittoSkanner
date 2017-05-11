package corp.skaj.foretagskvitton.activities;

import android.os.Bundle;

import corp.skaj.foretagskvitton.R;

public class GraphActivity extends AbstractActivity {
    public static final String STATE_FOR_BOTTOM_MENU = "GRAPH_ACTIVITY";
    public static final Integer BOTTOM_BAR_ID = R.id.action_charts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        initBottomBar(BOTTOM_BAR_ID, this);
    }
}
