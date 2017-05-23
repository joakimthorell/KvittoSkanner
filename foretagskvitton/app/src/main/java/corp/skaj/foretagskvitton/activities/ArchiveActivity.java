package corp.skaj.foretagskvitton.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.controllers.ArchiveController;
import corp.skaj.foretagskvitton.view.ArchiveFragment;

public class ArchiveActivity extends AbstractActivity {
    public static final String ARCHIVE_BUNDLE = "PURCHASE_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);
        FragmentManager fm = getSupportFragmentManager();
        ArchiveFragment af = ArchiveFragment.create();
        String purchaseId= getIntent().getStringExtra(MainActivity.ARCHIVE_KEY);

        new ArchiveController(
                getDataHandler()
                , purchaseId
                , af).setListerOnSaveButton((FloatingActionButton) findViewById(R.id.archive_receipt_savebutton));
        Bundle bundle = new Bundle();

        bundle.putString(ARCHIVE_BUNDLE, purchaseId);
        af.setArguments(bundle);
        fm.beginTransaction().replace(R.id.archive_fragment_container, af).commit();
    }
}