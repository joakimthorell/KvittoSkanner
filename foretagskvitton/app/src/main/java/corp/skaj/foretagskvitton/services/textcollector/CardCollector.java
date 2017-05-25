package corp.skaj.foretagskvitton.services.textcollector;

import java.util.List;

class CardCollector extends AbstractCollector {
    private static final String MASTERCARD = "mastercard";
    private static final String VISA = "visa";
    private static final String KORTNUMMER = "kortnummer";
    private static final String KORT = "kort";

    public CardCollector() {
        //package private
    }

    public String getCard(List<String> strings) {
        if (strings == null) {
            return null;
        }
        return findCard(listToString(strings).toLowerCase());
    }

    private String findCard(String s) {
        int index = getCardIndex(s);
        if (index != -1) {
            return evaluateResult(s, detachCard((s.substring(index, s.length()))), index);
        } else {
            String newS = placePotentialAsterix(s);
            int asterix = findAsterix(newS);
            if (asterix != -1) {
                return evaluateResult(newS, detachCard((newS.substring(asterix, newS.length()))), asterix);
            }
            return evaluateResult(s, detachCard(s), 0);
        }
    }

    private String evaluateResult(String s, String result, int index) {
        if (result == null) {
            return detachCard(replaceLetters(s.substring(index, s.length())));
        }
        return result;
    }

    private int getCardIndex(String s) {
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

    private String listToString(List<String> strings) {
        StringBuilder sb = new StringBuilder();
        for (String s : strings) {
            sb.append(s);
        }
        return sb.toString().replaceAll("\\s+", "");
    }

    private String detachCard(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count = isDigit(String.valueOf(s.charAt(i))) ? count + 1 : 0;
            if (isFourDigits(s, count, i)) {
                System.out.println(s.substring(i - 3, i + 1));
                return s.substring(i - 3, i + 1);
            }
        }
        return null;
    }

    private boolean isFourDigits(String s, int count, int i) {
        return count == 4 && isDetached(s, i, -4, 2);
    }

    private boolean isDetached(String s, int i, int before, int after) {
        if (!outOfBounds(s, i, before, after)) {
            return !isDigit(String.valueOf(s.charAt(i + before)))
                    && !isDigit(String.valueOf(s.charAt(i + after)))
                    && !isDate(s, i, before)
                    && !isDate(s, i, after)
                    && !isPrice(s, i);
        } else if (!outOfBounds(s, i, before, 0)) {
            return !isDate(s, i, before);
        }
        return false;
    }

    private boolean isDate(String s, int i, int position) {
        return !isAsterix(s, i)
                && "-.".contains(String.valueOf(s.charAt(i + position)));
    }

    private boolean isPrice(String s, int i) {
        return ",".contains(String.valueOf(s.charAt(i + 1)));
    }

    private boolean isAsterix(String s, int i) {
        return ("*x\"'¤").contains(String.valueOf(s.charAt(i - 4)));
    }

    private boolean outOfBounds(String s, int i, int before, int after) {
        try {
            s.charAt(i + before);
            s.charAt(i + after);
        } catch (IndexOutOfBoundsException e) {
            return true;
        }
        return false;
    }

    private boolean isDigit(String s) {
        return "0123456789".contains(s);
    }

    private String placePotentialAsterix(String s) {
        return s.replaceAll("x", "*")
                .replaceAll("\"", "*")
                .replaceAll("'", "*")
                .replaceAll("#", "*")
                .replaceAll("¤", "*");
    }

    private int findAsterix(String s) {
        return s.indexOf("*");
    }
}
