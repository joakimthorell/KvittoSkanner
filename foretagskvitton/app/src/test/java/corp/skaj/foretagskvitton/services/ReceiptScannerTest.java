package corp.skaj.foretagskvitton.services;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by mattsson on 2017-04-27.
 */
public class ReceiptScannerTest {

    ReceiptScanner receiptScanner = new ReceiptScanner();
    List<String> list;
    List <String> listOfDoubles;
    List <String> listOfCardNums;

    @Before
    public void setup(){
        list = new ArrayList<>();
        list.add("fjweiofeiw");
        list.add("20423423");
        list.add("020 023 423");
        list.add("216-04-28");
        list.add("16-04-28");
        list.add("2017234223423422");
        list.add("2017-04-27");

        listOfDoubles = new ArrayList<>();
        listOfDoubles.add("90,95");
        listOfDoubles.add("hej");
        listOfDoubles.add("299,00");
        listOfDoubles.add("12,90");
        listOfDoubles.add("8,95");
        listOfDoubles.add("skajcorp");
        listOfDoubles.add("hitta doris");
        listOfDoubles.add("50");
        listOfDoubles.add("5,95");
        listOfDoubles.add("20,00");

        listOfCardNums = new ArrayList<>();
        listOfCardNums.add("a2432");
        listOfCardNums.add("3v442");
        listOfCardNums.add("342");
        listOfCardNums.add("34422r32r23");
        listOfCardNums.add("xxxx");
        listOfCardNums.add("5655");
        listOfCardNums.add("xxxx xx*x Xxxx 5543");
    }
    
    @Test
    public void testDateFilter(){
        String testdate = "2017-04-27";
        String methodDate = receiptScanner.getDate(list);
        assertEquals(testdate, methodDate);
        assertEquals(testdate, receiptScanner.getDate(list));
    }

    @Test
    public void testFindingDouble () {
        String test = receiptScanner.getTotalCost(listOfDoubles);
        assertEquals("299.0", test);
    }

    @Test
    public void testCardNum (){
        //String expected = "xxxxxx*xXxxx5543";
        String expected = "5655";
        String test = receiptScanner.getCardNumber(listOfCardNums);
        assertEquals(expected, test);
    }
}