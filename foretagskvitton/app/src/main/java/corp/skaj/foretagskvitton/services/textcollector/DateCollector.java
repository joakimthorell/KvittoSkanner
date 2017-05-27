package corp.skaj.foretagskvitton.services.textcollector;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

class DateCollector extends AbstractCollector {

    DateCollector() {
        //package private
    }

    public String getDate(List<String> strings) {
        if (strings == null) {
            return null;
        }
        for (int i = 0; i < strings.size(); i++) {
            String currentString = strings.get(i);
            currentString = replaceLetters(currentString);
            if (currentString.length() < 4) {
                continue;
            }
            if (correctFirstNum(currentString.substring(0, 4)) && correctLength(currentString)) {
                return currentString;
            }
        }
        return null;
    }

    // Checks that the string starts with the current year in ex. 17 or 2017.
    private boolean correctFirstNum(String date) {
        String year = new SimpleDateFormat("yyyy").format(new Date());
        return date.substring(0, 2).equals(year.substring(0, 4)) || date.equals(year);
    }

    // Checks that the length is correct, either 170218 or 2017-05-03.
    private boolean correctLength(String date) {
        return date.length() <= 10 && date.length() >= 6;
    }

}