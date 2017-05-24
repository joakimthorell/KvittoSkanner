package corp.skaj.foretagskvitton.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.controllers.ArchiveController;
import corp.skaj.foretagskvitton.view.ArchiveFragment;
import corp.skaj.foretagskvitton.view.ArchiveListener;

public class ArchiveActivity extends AbstractActivity
    implements ArchiveListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);
        FragmentManager fm = getSupportFragmentManager();
        String purchaseId = getIntent().getStringExtra(MainActivity.ARCHIVE_KEY);
        ArchiveFragment af = ArchiveFragment.create(purchaseId);
        ArchiveController controller = new ArchiveController(getDataHandler(), purchaseId, af);
        af.setFabListener(controller);
        af.setImageListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fm.beginTransaction().replace(R.id.archive_fragment_container, af).commit();
    }

    @Override
    public void setImagePressed(Uri address) {
        // todo change to new fragment to show image
    }
}