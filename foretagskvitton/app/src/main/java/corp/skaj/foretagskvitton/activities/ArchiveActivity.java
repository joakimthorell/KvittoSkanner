package corp.skaj.foretagskvitton.activities;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import corp.skaj.foretagskvitton.R;

import corp.skaj.foretagskvitton.controllers.ReceiptAdapter;

import corp.skaj.foretagskvitton.model.User;

public class ArchiveActivity extends AbstractActivity {
    private ReceiptAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);
        User user = getUser();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        mAdapter = new ReceiptAdapter(user);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        initBottomBar(ARCHIVE_ID, this);
        //mAdapter.notifyDataSetChanged();
    }
}

