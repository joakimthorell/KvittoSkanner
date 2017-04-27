package corp.skaj.foretagskvitton.services;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.IOException;
import java.util.List;

import static android.provider.CalendarContract.CalendarCache.URI;

/**
 * Created by mattsson on 2017-04-27.
 */

public class TextRecognizer {

    private TextRecognizer() {
        // Should not be able to create this object
    }

    public static List<String> collectStringsFromImage(Context context, Uri uri) throws IOException, NullPointerException {
        Bitmap bmp = createImageFromUri(context, uri);
        return null;
    }

    private static Bitmap createImageFromUri(Context context, Uri uri) throws IOException, NullPointerException {
        if (URI == null) {
            throw new NullPointerException("URI is null");
        }
        Bitmap bmp = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
        return bmp;
    }

}
