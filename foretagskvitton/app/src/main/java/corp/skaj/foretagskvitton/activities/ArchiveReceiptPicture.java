package corp.skaj.foretagskvitton.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.controllers.ArchiveController;

public class ArchiveReceiptPicture extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive_receipt_picture);

        Intent intent = getIntent();
        //String purId = intent.getStringExtra(ArchiveController.ITEM_ID);
    }

}
