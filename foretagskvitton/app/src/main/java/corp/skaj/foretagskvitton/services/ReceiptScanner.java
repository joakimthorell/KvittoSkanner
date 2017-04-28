package corp.skaj.foretagskvitton.services;

import java.util.Calendar;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ReceiptScanner {
    private double totalCost;
    private List<String> list;

    // Checks that the string starts with the current year in ex. 17 or 2017.
    private boolean correctFirstNum(String date) {
        return date.substring(0, 2).equals((String.valueOf(Calendar.YEAR).substring(1, 3))) ||
                date.equals(String.valueOf(Calendar.YEAR));
    }

    // Checks that the size is correct format, either 170218 or 2017-05-03.
    private boolean correctLength(String date) {
        return date.length() <= 10 || date.length() >= 6;
    }

    private List<Double> findAllDoubles(List<String> list) {
        List<Double> listOfDoubles = new ArrayList<>();
        double temp = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).contains(",")) {
                temp = Double.parseDouble(list.get(i));
                listOfDoubles.add(temp);
            }
        }
    }

    private double findBiggestDouble(List<Double> listOfDoubles) {
        double biggestDouble = 0;
        for (int i = 0; i < listOfDoubles.size() - 1; i++) {
            if (listOfDoubles.get(i) > listOfDoubles.get(i + 1)) {
                biggestDouble = listOfDoubles.get(i);
            }
        }
        return biggestDouble;
    }

    //TODO Kan kolla if "Totalt" or "Total" i samma metod m.h.a || (metoderna nedan)
    private boolean checkIfTotalBeforeAmount() {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals("Total")) {
                return true;
            }
        }
        return false;
    }

    private boolean checkIfTotaltBeforeAmount() {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals("Totalt")) {
                return true;
            }
        }
        return false;
    }

    private boolean checkIfSekAfter() {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals("SEK")) {
                return true;
            }
        }
        return false;
    }

    // TODO Kan kolla if Big or Small "Kr" i samma metod (även ovanstående SEK) m.h.a ||
    public boolean checkIfBigKrAfter() {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals("Kr")) {
                return true;
            }
        }
        return false;

    }

    private boolean checkIfSmallKrAfter() {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals("kr")) {
                return true;
            }
        }
        return false;
    }

    public void getTotalCost(List<String> list) {
        List<Double> listOfDoubles = findAllDoubles(list);
        totalCost = findBiggestDouble(listOfDoubles);
        if (totalCost == 0) {

        }
    }

    public String getDate(List<String> list) { // Gjorde ändring här, vet inte om det var korrekt? // Joakim
        for (int i = 0; i < list.size(); i++) {
            if (correctFirstNum(list.get(i).substring(0, 3)) && correctLength(list.get(i))) {
                return list.get(i);
            }
        }
        return Calendar.getInstance().getTime().toString();
    }

    public void getProducts() {
    }

    public void getCardNumber() {
    }

}