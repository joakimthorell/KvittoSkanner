package corp.skaj.foretagskvitton.services;

import java.util.Calendar;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ReceiptScanner {
    private List<String> listOfStrings;


    /**
     *
     * @param date
     * @return
     */

    // Checks that the string starts with the current year in ex. 17 or 2017.
    private boolean correctFirstNum(String date) {
        return date.substring(0, 2).equals((String.valueOf(Calendar.YEAR).substring(1, 3))) ||
                date.equals(String.valueOf(Calendar.YEAR));
    }

    /**
     *
     * @param date
     * @return
     */

    // Checks that the size is correct format, either 170218 or 2017-05-03.
    private boolean correctLength(String date) {
        return date.length() <= 10 || date.length() >= 6;
    }

    /**
     *
     * @param listOfStrings
     * @return listOfDoubles
     */

    private List<Double> findAllDoubles(List<String> listOfStrings) {
        List<Double> listOfDoubles = new ArrayList<>();
        double temp = 0;
        for (int i = 0; i < listOfStrings.size(); i++) {
            if (listOfStrings.get(i).contains(",")) {
                temp = Double.parseDouble(listOfStrings.get(i));
                listOfDoubles.add(temp);
            }
        }
        return listOfDoubles;
    }

    /**
     *
     * @param listOfDoubles
     * @return biggestDouble
     */

    private double findBiggestDouble(List<Double> listOfDoubles) {
        double biggestDouble = 0;
        for (int i = 0; i < listOfDoubles.size() - 1; i++) {
            if (listOfDoubles.get(i) > listOfDoubles.get(i + 1)) {
                biggestDouble = listOfDoubles.get(i);
            }
        }
        return biggestDouble;
    }

    /**
     *
     * @return <code>true</code> if total of totalt is found
     * <code>false</code> otherwise
     */

    private boolean checkIfTextBefore() {
        for (int i = 0; i < listOfStrings.size(); i++) {
            if (listOfStrings.get(i).toLowerCase().equals("total")
                    || listOfStrings.get(i).toLowerCase().equals("totalt")) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @return <code>true</code> if kr or sek is found
     * <code>false</code> otherwise
     */

    private boolean checkIfTextAfter() {
        for (int i = 0; i < listOfStrings.size(); i++) {
            if (listOfStrings.get(i).toLowerCase().equals("kr")
                        || listOfStrings.get(i).toLowerCase().equals("sek")) {
                return true;
            }
        }
        return false;
    }


    public String getTotalCost(List<String> listOfStrings) {
        double totalCost = 0;
        this.listOfStrings = listOfStrings;
        List<Double> listOfDoubles = findAllDoubles(listOfStrings);
        totalCost = findBiggestDouble(listOfDoubles);

        return String.valueOf(totalCost);
        }

    public String getDate(List<String> listOfStrings) { // Gjorde ändring här, vet inte om det var korrekt? // Joakim
        for (int i = 0; i < listOfStrings.size(); i++) {
            if (correctFirstNum(listOfStrings.get(i).substring(0, 3)) && correctLength(listOfStrings.get(i))) {
                return listOfStrings.get(i);
            }
        }
        return Calendar.getInstance().getTime().toString();
    }

    public void getProducts() {
    }

    public void getCardNumber() {
    }

}