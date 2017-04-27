package corp.skaj.foretagskvitton.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import corp.skaj.foretagskvitton.services.SearchThroughReceipt;

import static org.junit.Assert.assertEquals;

/**
 * Created by kevinbrunstrom on 2017-04-27.
 */

public class SearchThroughReceiptTest {

    SearchThroughReceipt searchThroughReceipt;
    List <String> list;

    @Before
    public void setup(){
        list = new ArrayList<>();
        list.add("fjweiofeiw");
        list.add("2017-03-18");
        list.add("20423423");
        list.add("020 023 423");
    }

    @Test
    public void testDateFilter(){
        String corrdate = "2017-03-18";
        assertEquals(corrdate, searchThroughReceipt.findDate(list));
    }
}
