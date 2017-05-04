package corp.skaj.foretagskvitton.services;

import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class ReceiptScannerTest {

    List<String> list;
    List <String> listOfDoubles;
    List <String> listOfCardNums;
    List<String> listWithoutDate;

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

         //listOfCardNums.add("*3v 5655");
        listOfCardNums.add("342");
        listOfCardNums.add("34422r32r23");
        listOfCardNums.add("xxxx");
        listOfCardNums.add("5343-5554"); // skips org-nums!
        listOfCardNums.add("dewwk* 5655");
        listOfCardNums.add("Xk** x**x XXxx 5655");

        listWithoutDate = new ArrayList<>();
        for (int i  = 0; i < 10 ; i++) {
            listWithoutDate.add(String.valueOf(i));
        }
    }

    @Test
    public void testNullCase() {
        assertEquals(null, ReceiptScanner.getCardNumber(null));
    }

    
    @Test
    public void testDateFilter(){
        String testdate = "2017-04-27";
        String methodDate = ReceiptScanner.getDate(list);
        assertEquals(testdate, methodDate);
        assertEquals(testdate, ReceiptScanner.getDate(list));
    }

    /*
    @Test
    public void testFindingDouble () {
        double test = receiptScanner.getTotalCost(listOfDoubles);
        assertEquals("299.0", test);
    }
    */

    @Test
    public void testIfNoDateFound() {
        String date = ReceiptScanner.getDate(listWithoutDate);

        assertEquals(null, date);
    }

    @Test
    public void testCardNum (){
        //String expected = "xxxxxx*xXxxx5543";
        String expected = "5655";
        String test = ReceiptScanner.getCardNumber(listOfCardNums);
        assertEquals(expected, test);
    }
}