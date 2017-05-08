package corp.skaj.foretagskvitton.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.roughike.bottombar.BottomBar;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.controllers.BottomNavigationController;

public class GraphActivity extends AbstractActivity {
    public static final String STATE_FOR_BOTTOM_MENY = "GRAPH_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        BottomNavigationController.setupBottomNavBar(bottomBar, STATE_FOR_BOTTOM_MENY, this);
    }
}
