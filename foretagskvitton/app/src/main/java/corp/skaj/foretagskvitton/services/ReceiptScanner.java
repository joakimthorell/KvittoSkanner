package corp.skaj.foretagskvitton.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 */
public class ReceiptScanner {
    private List<String> listOfStrings;

    public String getDate(List<String> listOfStrings) {
        for (int i = 0; i < listOfStrings.size(); i++) {
            String currentString = listOfStrings.get(i);

            if (correctFirstNum(currentString.substring(0, 4)) && correctLength(currentString)) {
                return currentString;
            }
        }
        return "2017-04-28";
        //return Calendar.getInstance().getTime().toString();
    }

    /**
     *
     * @param date
     * @return
     */
    // Checks that the string starts with the current year in ex. 17 or 2017.
    // TODO - Check how simple Dateformat class works? Necessery with other solutions for date?
    private boolean correctFirstNum(String date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        Calendar cal = Calendar.getInstance();
        String year = "2017";
       //String year = dateFormat.format(cal).toString();
        return date.substring(0, 2).equals(year.substring(0, 4)) || date.equals(year);
    }

    /**
     *
     * @param date
     * @return
     */
    // Checks that the size is in correct swedish format, either 170218 or 2017-05-03.
    private boolean correctLength(String date) {
        return date.length() <= 10 && date.length() >= 6;
    }

    /**
     *
     * @param listOfStrings
     * @return
     */
    private List<Double> findAllDoubles(List<String> listOfStrings) {
        List<Double> listOfDoubles = new ArrayList<>();
        for (int i = 0; i < listOfStrings.size(); i++) {
            String s = listOfStrings.get(i).replace("," , ".");
            if (s.contains(".")) {
                if(isDouble(s)) {
                    listOfDoubles.add(Double.parseDouble(s));
                }
            }
        }
        return listOfDoubles;
    }

    /**
     *
     * @param s
     * @return <code>true</code> if s is a double
     * <code>false</code> otherwise
     */
    private boolean isDouble (String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     *
     * @param s
     * @return <code>true</code> if s is a interger
     * <code>false</code> otherwise
     */
    private boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     *
     * @return <code>true</code> if kr or sek is found
     * <code>false</code> otherwise
     */

    private int checkForText () {
        for (int i = 0; i < listOfStrings.size(); i++) {
            if (listOfStrings.get(i).toLowerCase().equals("kr")
                        || listOfStrings.get(i).toLowerCase().equals("sek")
                            || listOfStrings.get(i).toLowerCase().equals("total")
                                || listOfStrings.get(i).toLowerCase().equals("totalt")) {
                return i;
            }
        }
        return -1;
    }


    public double checkBeforeAndAfter (int index) {
        double totalCostBefore = 0.0;
        double totalCostAfter = 0.0;
        double totalCost;
        String temp;
        if (isInt(listOfStrings.get(index - 1)) || isDouble(listOfStrings.get(index - 1))) {
            temp = listOfStrings.get(index - 1);
            totalCostBefore = Double.parseDouble(temp);

        } if (isInt(listOfStrings.get(index + 1)) || isDouble(listOfStrings.get(index + 1))) {
            temp = listOfStrings.get(index + 1);
            totalCostAfter = Double.parseDouble(temp);
        }
        if (totalCostBefore > totalCostAfter) {
            totalCost = totalCostBefore;
        } else {
            totalCost = totalCostAfter;
        }

        return totalCost > 0 ? totalCost : 0;
    }


    public double getTotalCost(List<String> listOfStrings) {
        this.listOfStrings = listOfStrings;
        List<Double> listOfDoubles = findAllDoubles(listOfStrings);

        try {
            return Collections.max(listOfDoubles);
        } catch (ClassCastException cce) {
            int index = checkForText();
            double totalCost = checkBeforeAndAfter(index);
            return totalCost;
            }
        }

    public void getProducts(List<String> listOfStrings) {

    }

    public String getCardNumber(List<String> listOfStrings) {
        String currString = "";
        for (int i = 0; i < listOfStrings.size(); i++) {
            currString = listOfStrings.get(i).replace(" ", "");

            if(containsAsterix(currString)){
                return currString.substring(currString.length() - 4);
            }
            else if (correctCardNumLength(currString)){

                if(currString.length() == 16 && containsNumEnd(currString)) {
                    return currString.substring(12);
                }
                if(currString.length() == 4 && onlyNums(currString)){
                    return currString;
                }
            }
            else if (currString.length() >= 4 && currString.length() < 16){
                if(containsNumEnd(currString) && notOrgNum(currString)){
                    return currString.substring(currString.length() - 4);
                }
            }
        }
      return "0000";
    }


    private boolean containsAsterix(String currString){
     return  currString.contains("*");
    }

    private boolean correctCardNumLength(String currString){
        return currString.length() == 16 || currString.length() == 4;
    }

    private boolean onlyNums (String currString){
        String compare = "\\d+";
        return currString.matches(compare);
    }

    private boolean containsNumEnd(String currString){
        String currEnd = currString.substring(currString.length() - 4);
        return currEnd.matches("\\d+");
    }

    private boolean notOrgNum(String currString){
        String currEnd = currString.substring(currString.length() - 5);
        return !currEnd.contains("-");
    }
}