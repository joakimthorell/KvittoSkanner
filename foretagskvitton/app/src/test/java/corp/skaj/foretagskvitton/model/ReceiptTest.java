package corp.skaj.foretagskvitton.model;

import android.text.style.TtsSpan;

import org.junit.Before;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by mattsson on 2017-04-12.
 */
public class ReceiptTest {

    Receipt receipt;

    @Before
    public void setup() {
        Product product = new Product("Appelsin Juice", 15.90, 25);
        
        receipt = new Receipt()
    }
}