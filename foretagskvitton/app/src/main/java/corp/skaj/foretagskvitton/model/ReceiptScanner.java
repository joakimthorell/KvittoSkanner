package corp.skaj.foretagskvitton.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ReceiptScanner {
    private static final String MASTERCARD = "mastercard";
    private static final String VISA = "visa";
    private static final String KORTNUMMER = "kortnummer";
    private static final String KORT = "kort";

    private ReceiptScanner() {
    }

    public static String getCard(List<String> strings) {
        if (strings == null) {
            return null;
        }
        return findCard(listToString(strings).toLowerCase());
    }

    public static String getDate(List<String> strings) {
        if (strings == null) {
            return null;
        }
        for (int i = 0; i < strings.size(); i++) {
            String currentString = strings.get(i);
            replaceLetters(currentString);
            if (currentString.length() < 4) {
                continue;
            }
            if (correctFirstNum(currentString.substring(0, 4)) && correctLength(currentString)) {
                return currentString;
            }
        }
        return null;
    }

    public static double getTotalCost(List<String> strings) {
        if (strings == null) {
            return 0.0;
        }
        List<Double> doubles = findDoubles(strings);
        try {
            return Collections.max(doubles); // Denna kastar 2 olika exceptions.
        } catch (Exception cce) {
            int index = checkForText(strings);
            double totalCost = index >= 0 ? checkBeforeAndAfter(index, strings) : 0; // Om index är -1 som checkForText returnerar när den inte hittar något får man outOfBounds här
            return totalCost;
        }
    }

    public static void getProducts(List<String> strings) {
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

    private static List<Double> findDoubles(List<String> strings) {
        List<Double> doubles = new ArrayList<>();
        for (int i = 0; i < strings.size(); i++) {
            String s = strings.get(i).replace(",", ".");
            replaceLetters(s);
            if (s.contains(".")) {
                if (isDouble(s)) {
                    doubles.add(Double.parseDouble(s));
                }
            }
        }
        return doubles;
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

    private static int checkForText(List<String> strings) {
        for (int i = 0; i < strings.size(); i++) {
            if (strings.get(i).toLowerCase().equals("kr")
                    || strings.get(i).toLowerCase().equals("sek")
                    || strings.get(i).toLowerCase().equals("total")
                    || strings.get(i).toLowerCase().equals("totalt")
                    || strings.get(i).toLowerCase().equals("betala")) {
                return i;
            }
        }
        return -1;
    }

    private static double checkBeforeAndAfter(int index, List<String> strings) {
        double totalCostBefore = 0.0;
        double totalCostAfter = 0.0;
        double totalCost;
        String temp;
        if (isInt(strings.get(index - 1)) || isDouble(strings.get(index - 1))) {
            temp = strings.get(index - 1);
            totalCostBefore = Double.parseDouble(temp);
        }
        if (isInt(strings.get(index + 1)) || isDouble(strings.get(index + 1))) {
            temp = strings.get(index + 1);
            totalCostAfter = Double.parseDouble(temp);
        }
        if (totalCostBefore > totalCostAfter) {
            totalCost = totalCostBefore;
        } else {
            totalCost = totalCostAfter;
        }
        return totalCost > 0 ? totalCost : 0;
    }

    private static String findCard(String s) {
        int index = getIndex(s);
        if (index != -1) {
            return evaluateResult(s, detachCard((s.substring(index, s.length()))), index);
        } else {
            String newS = anticipateAterix(s);
            int asterix = findAterix(newS);
            if (asterix != -1) {
                return evaluateResult(newS, detachCard((newS.substring(asterix, newS.length()))), asterix);
            }
            return evaluateResult(s, detachCard(s), 0);
        }
    }

    private static String evaluateResult(String s, String result, int index) {
        if (result.equals("null")) {
            return detachCard(replaceLetters(s.substring(index, s.length())));
        }
        return result;
    }

    private static int getIndex(String s) {
        if (s.contains(KORTNUMMER)) {
            return s.lastIndexOf(KORTNUMMER);
        }
        if (s.contains(MASTERCARD)) {
            return s.lastIndexOf(MASTERCARD);
        }
        if (s.contains(VISA)) {
            return s.lastIndexOf(VISA);
        }
        if (s.contains(KORT)) {
            return s.lastIndexOf(KORT);
        }
        return -1;
    }

    private static String listToString(List<String> strings) {
        String S = "";
        for (String s : strings) {
            S += s;
        }
        return S.replaceAll("\\s+", "");
    }

    private static String detachCard(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count = isDigit(String.valueOf(s.charAt(i))) ? count + 1 : 0;
            if (isFourDigits(s, count, i)) {
                return s.substring(i - 3, i + 1);
            }
        }
        return "null";
    }

    private static boolean isFourDigits(String s, int count, int i) {
        return count == 4 && isDetached(s, i);
    }

    private static boolean isDetached(String s, int i) {
        return outOfBounds(s, i) || !isDigit(String.valueOf(s.charAt(i + 1)))
                && !isDigit(String.valueOf(s.charAt(i - 4)))
                && !isDate(s, i)
                && !isPrice(s, i);
    }

    private static boolean isDate(String s, int i) {
        return isAsterix(s, i) && "-.".contains(String.valueOf(s.charAt(i + 1))) || "-.".contains(String.valueOf(s.charAt(i - 4)));
    }

    private static boolean isPrice(String s, int i) {
        return ",".contains(String.valueOf(s.charAt(i + 1)));
    }

    private static boolean isAsterix(String s, int i) {
        return "'*x".contains(String.valueOf(s.charAt(i - 4)))
                || String.valueOf(s.charAt(i - 4)).matches("[^\"]*");
    }

    private static boolean outOfBounds(String s, int i) {
        try {
            s.charAt(i + 1);
            s.charAt(i - 4);
        } catch (IndexOutOfBoundsException e) {
            return true;
        }
        return false;
    }

    private static boolean isDigit(String s) {
        return "0123456789".contains(s);
    }

    private static String replaceLetters(String s) {
        return s.replaceAll("il", "1").replaceAll("o", "0").replaceAll("s", "5").replaceAll("b", "8");
    }

    private static String anticipateAterix(String s) {
        return s.replaceAll("x^´`'[^\"]*", "*");
    }

    private static int findAterix(String s) {
        return s.indexOf("*");
    }
}