package corp.skaj.foretagskvitton.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.view.ArchiveFragment;

public class ArchiveActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);
        FragmentManager fm = getSupportFragmentManager();
        String purchaseId= getIntent().getStringExtra(MainActivity.ARCHIVE_KEY);
        ArchiveFragment af = ArchiveFragment.create(purchaseId);

        fm.beginTransaction().replace(R.id.archive_fragment_container, af).commit();
    }
}