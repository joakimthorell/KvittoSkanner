package corp.skaj.foretagskvitton.services;


import java.text.DateFormat;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by annekeller on 2017-04-27.
 */

public class SearchThroughReceipt {


    double totalCost;
    List<String> list;
    DateFormat date;
    Calendar calendar;

    List<Double> listOfDoubles = new ArrayList<>();

    public void findTotalCost(List<String> list) {
    findAllDoubles(list);
    totalCost = findBiggestDouble(listOfDoubles);
        if (totalCost == 0) {

        }
    }

    public void findCardNumber () {

    }

    public String findDate (List<String> list ) {
        for(int i = 0; i < list.size(); i++){
            if(correctFirstnum(list.get(i).substring(0,3)) && correctLength(list.get(i))){
                return list.get(i);
            }
        }
        return calendar.getInstance().getTime().toString();
    }

    // Checks that the string starts with the current year in ex. 17 or 2017.
    private boolean correctFirstnum(String date){
         String year = String.valueOf(calendar.YEAR);

        return  date.substring(0,2).equals(year.substring(1,3)) ||
                date.equals(String.valueOf(calendar.YEAR));
    }
    // Checks that the size is correct format, either 170218 or 2017-05-03.
    private boolean correctLength(String date){
        return date.length() <= 10 || date.length() >= 6;
    }

    public void findProducts () {

    }

    public void findAllDoubles (List<String> list) {
        double temp = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).contains(",")) {
                temp = Double.parseDouble(list.get(i));
                listOfDoubles.add(temp);
            }
        }
    }

    public double findBiggestDouble (List<Double> listOfDoubles) {
        double biggestDouble = 0;
        for (int i = 0; i < listOfDoubles.size() - 1; i++) {
            if (listOfDoubles.get(i) > listOfDoubles.get(i+1)) {
                biggestDouble = listOfDoubles.get(i);
            }
        }
        return biggestDouble;
    }
    public boolean checkIfTotalBeforeAmount () {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals("Total")) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfTotaltBeforeAmount () {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals("Totalt")) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfSekAfter () {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals("SEK")) {
                return true;
            }
        }
        return false;
    }
    public boolean checkIfBigKrAfter () {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals("Kr")) {
                return true;
            }
        }
        return false;

    }

    public boolean checkIfSmallKrAfter () {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals("kr")) {
                return true;
            }
        }
        return false;

    }

}
