package corp.skaj.foretagskvitton.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TextCollectorTest {
    private List<String> list;
    private List <String> listOfDoubles;
    private List <String> listOfCardNums;
    private List<String> listWithoutDate;

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
    public void testCardNullCase() {
        assertEquals(null, TextCollector.getCard(null));
    }

    @Test
    public void testCostNullCase() {
        assertEquals(0.0, TextCollector.getPrice(null), 1);
    }

    @Test
    public void testDateNullCase() {
        assertEquals(null, TextCollector.getDate(null));
    }

    
    @Test
    public void testDateFilter(){
        String testdate = "2017-04-27";
        String methodDate = TextCollector.getDate(list);
        assertEquals(testdate, methodDate);
        assertEquals(testdate, TextCollector.getDate(list));
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
        String date = TextCollector.getDate(listWithoutDate);

        assertEquals(null, date);
    }

    @Test
    public void testCardNum (){
        List<String> list = new ArrayList<>(Arrays.asList("cxx134,-45","M R STER ","", "019677" + "9", "Bjl", " THAITAKEA\"3310y040,00mPer50nligk1234"
        ));
        List<String> test = new ArrayList<>(Arrays.asList("xx*¤#'\""));
        //System.out.println(TextCollector.anticipateAterix(TextCollector.listToString(test)));
        System.out.println(list);
        String expected = "3310";
        String result = TextCollector.getCard(list);
        assertEquals(expected, result);
    }
}