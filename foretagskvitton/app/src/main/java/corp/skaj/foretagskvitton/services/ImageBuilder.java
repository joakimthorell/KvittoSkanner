package corp.skaj.foretagskvitton.services;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.IOException;

public class ImageBuilder {

    private ImageBuilder() {
    }

    public static Bitmap createImageFromURI(Context context, Uri uri) throws IOException {
        if (uri == null) {
            throw new NullPointerException("URI is null");
        }
        Bitmap bmp = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
        return bmp;
    }
}