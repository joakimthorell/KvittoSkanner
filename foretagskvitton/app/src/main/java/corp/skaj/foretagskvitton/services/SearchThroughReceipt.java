package corp.skaj.foretagskvitton.services;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by annekeller on 2017-04-27.
 */

public class SearchThroughReceipt {


    double totalCost;
    List<String> list;
    DateFormat date;
    Calendar calandar;
    public void findTotalCost() {

    }

    public void findCardNumber () {

    }

    public String findDate ( ) {
        for(int i = 0; i < list.size(); i++){
            if(correctFirstnum(list.get(i).substring(0,3)) && correctLength(list.get(i))){
                return list.get(i);
            }
        }
        return calandar.getInstance().getTime().toString();
    }

    // Checks that the string starts with the current year in ex. 17 or 2017.
    private boolean correctFirstnum(String date){
        return  date.substring(0,2).equals((String.valueOf(calandar.YEAR).substring(1,3))) ||
                date.equals(String.valueOf(calandar.YEAR));
    }
    // Checks that the size is correct format, either 170218 or 2017-05-03.
    private boolean correctLength(String date){
        return date.length() <= 10 || date.length() >= 6;
    }

    public void findProducts () {

    }

}
