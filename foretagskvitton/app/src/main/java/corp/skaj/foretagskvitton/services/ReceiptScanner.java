package corp.skaj.foretagskvitton.services;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.SparseArray;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.Text;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class collects data from an image, allowing application to
 * collect model relevant data from a picture of a receipt.
 */
public class ReceiptScanner {

    private ReceiptScanner() {
    }

    public static List<String> collectStringsFromURI(Context context, Uri uri) throws IOException {
        Bitmap bmp = ImageBuilder.createImageFromURI(context, uri);
        SparseArray<TextBlock> textBlocks = getTextBlocksFromImage(context, bmp);
        if (textBlocks == null) {
            // This will only happend if no text found in image
            // or text recognizer is not operational.
            return null;
        }
        return buildListOfStrings(textBlocks);
    }

    private static SparseArray<TextBlock> getTextBlocksFromImage(Context context, Bitmap bmp) {
        TextRecognizer textRecognizer = new TextRecognizer.Builder(context.getApplicationContext()).build();
        if (textRecognizer.isOperational()) {
            Frame frame = new Frame.Builder().setBitmap(bmp).build();
            SparseArray<TextBlock> listOfTextBlocks = textRecognizer.detect(frame);
            textRecognizer.release();
            return listOfTextBlocks;
        } else {
            System.out.println("TextRecognizer is not operational");
            return null;
        }
    }

    private static List<String> buildListOfStrings(SparseArray<TextBlock> listOfTextBlock) {
        List<String> listOfStrings = new ArrayList<>();
        for (int i = 0; i < listOfTextBlock.size(); i++) {
            if (listOfTextBlock.get(i) != null) {
                listOfStrings.addAll(linesToStrings(listOfTextBlock.get(i).getComponents()));
            }
        }
        return listOfStrings;
    }

    private static List<String> linesToStrings(List<? extends Text> list) {
        List<String> listOfStrings = new ArrayList<>();
        for (Text t : list) {
            listOfStrings.add(t.getValue());
        }
        return listOfStrings;
    }
}
