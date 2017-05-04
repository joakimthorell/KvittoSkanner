package corp.skaj.foretagskvitton.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ReceiptScanner {

    private ReceiptScanner() {
        // Should not be able to create an instance of this object
    }

    public static String getDate(List<String> listOfStrings) {
        String date = new SimpleDateFormat("yyyy-mm-dd").format(new Date());

        for (int i = 0; i < listOfStrings.size(); i++) {
            String currentString = listOfStrings.get(i);
            letterReplace(currentString);

            if (currentString.length() < 4) {
                continue;
            }        // Should not be able to create an instance of this object


            if (correctFirstNum(currentString.substring(0, 4)) && correctLength(currentString)) {
                return currentString;
            }
        }
        return null;
    }

    // Checks that the string starts with the current year in ex. 17 or 2017.
    private static boolean correctFirstNum(String date) {
        String year = new SimpleDateFormat("yyyy").format(new Date());
        return date.substring(0, 2).equals(year.substring(0, 4)) || date.equals(year);
    }

    // Checks that the length is correct, either 170218 or 2017-05-03.
    private static boolean correctLength(String date) {
        return date.length() <= 10 && date.length() >= 6;
    }

    private static List<Double> findAllDoubles(List<String> listOfStrings) {
        List<Double> listOfDoubles = new ArrayList<>();
        for (int i = 0; i < listOfStrings.size(); i++) {
            String s = listOfStrings.get(i).replace(",", ".");
            letterReplace(s);
            if (s.contains(".")) {
                if (isDouble(s)) {
                    listOfDoubles.add(Double.parseDouble(s));
                }
            }
        }
        return listOfDoubles;
    }

    private static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    private static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    private static int checkForText(List<String> listOfStrings) {
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

    public static double checkBeforeAndAfter(int index, List<String> listOfStrings) {
        double totalCostBefore = 0.0;
        double totalCostAfter = 0.0;
        double totalCost;
        String temp;
        if (isInt(listOfStrings.get(index - 1)) || isDouble(listOfStrings.get(index - 1))) {
            temp = listOfStrings.get(index - 1);
            totalCostBefore = Double.parseDouble(temp);

        }
        if (isInt(listOfStrings.get(index + 1)) || isDouble(listOfStrings.get(index + 1))) {
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

    public static double getTotalCost(List<String> listOfStrings) {
        List<Double> listOfDoubles = findAllDoubles(listOfStrings);

        try {
            return Collections.max(listOfDoubles); // Denna kastar 2 olika exceptions.
        } catch (Exception cce) {
            int index = checkForText(listOfStrings);
            double totalCost = index >= 0 ? checkBeforeAndAfter(index, listOfStrings) : 0; // Om index är -1 som checkForText returnerar när den inte hittar något får man outOfBounds här
            return totalCost;
        }
    }

    public static void getProducts(List<String> listOfStrings) {

    }

    public static String getCardNumber(List<String> listOfStrings) {

        //TODO Metoden crashar om den inte hittar data. Fix dis.

        String currString = "";
        for (int i = 0; i < listOfStrings.size(); i++) {
            currString = listOfStrings.get(i).replace(" ", "");
            letterReplace(listOfStrings.get(i));

            if (containsAsterix(currString)) {
                return currString.substring(currString.length() - 4);
            } else if (correctCardNumLength(currString)) {

                if (currString.length() == 16 && lastFourIsNum(currString)) {
                    return currString.substring(12);
                }
                if (currString.length() == 4 && onlyNums(currString)) {
                    return currString;
                }
            } else if (currString.length() >= 4 && currString.length() < 16) {

                if (lastFourIsNum(currString) && notOrgNum(currString)) {
                    return currString.substring(currString.length() - 4);
                }
            }
        }
        return "0000";
    }

    private static boolean containsAsterix(String currString) {
        return currString.contains("*");
    }

    private static boolean correctCardNumLength(String currString) {
        return currString.length() == 16 || currString.length() == 4;
    }

    private static boolean onlyNums(String currString) {
        String compare = "\\d+"; //only numbers
        return currString.matches(compare);
    }

    private static boolean lastFourIsNum(String currString) {
        String currEnd = currString.substring(currString.length() - 4);
        return currEnd.matches("\\d+");
    }

    private static boolean notOrgNum(String currString) {
        String currEnd = currString.substring(currString.length() - 5);
        return !currEnd.contains("-");
    }

    private static String letterReplace(String currString) {
        currString.replaceAll("B", "8");
        currString.replaceAll("S", "5");
        currString.replaceAll("O", "0");
        currString.replaceAll("i", "1");
        currString.replaceAll("l", "1");
        currString.replaceAll("S", "9");
        return null;
    }
}