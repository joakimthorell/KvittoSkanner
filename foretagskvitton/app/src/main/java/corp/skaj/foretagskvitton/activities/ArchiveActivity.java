package corp.skaj.foretagskvitton.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.Button;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.controllers.ArchiveController;
import corp.skaj.foretagskvitton.view.ArchiveFragment;

public class ArchiveActivity extends AbstractActivity {
    public static final String COMMENT_ID = "COMMENT_ID";
    public static final String ARCHIVE_BUNDLE = "PURCHASE_ID";
    public static final String PICTURE_ID = "PICTURE_ID";
    public static final String ACTIVITY_KEY = "ARCHIVE_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);

        String purchaseId= getIntent().getStringExtra(MainActivity.ARCHIVE_KEY);

        Bundle bundle = new Bundle();
        bundle.putString(ARCHIVE_BUNDLE, purchaseId);


        ArchiveFragment af = ArchiveFragment.create();
        ArchiveController acab = new ArchiveController(getDataHandler(), purchaseId, af);
        af.setArguments(bundle);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.archive_fragment_container, af).commit();
//        acab.setListerOnSaveButton((Button) findViewById(R.id.archive_receipt_savebutton));
    }
}