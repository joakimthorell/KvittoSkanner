package corp.skaj.foretagskvitton.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

import corp.skaj.foretagskvitton.R;
import corp.skaj.foretagskvitton.services.ReceiptScanner;

public class ArchiveReceiptPicture extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive_receipt_picture);
        Intent intent = getIntent();
        String imageAdress = intent.getStringExtra("image");
        Bitmap bmp = null;
        if (imageAdress != null) {
            Uri URI = Uri.parse(imageAdress);
            try {
                bmp = ReceiptScanner.createImageFromURI(this, URI);
            } catch (IOException ioe) {
                Toast.makeText(this, "Was not able to create Image ", Toast.LENGTH_SHORT).show();
            }
        } else {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.comingsoon);
        }
        ImageView view = (ImageView) findViewById(R.id.archive_showing_only_image);
        view.setImageBitmap(bmp);
    }
}
