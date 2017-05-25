package corp.skaj.foretagskvitton.services.textcollector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class TotalSumCollector extends AbstractCollector {

    public TotalSumCollector() {
        //package private
    }

    public double getTotalSum(List<String> strings) {
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

    private List<Double> findDoubles(List<String> strings) {
        List<Double> doubles = new ArrayList<>();
        for (int i = 0; i < strings.size(); i++) {
            String s = strings.get(i).replace(",", ".");
            s = replaceLetters(s);
            if (s.contains(".")) {
                if (isDouble(s)) {
                    doubles.add(Double.parseDouble(s));
                }
            }
        }
        return doubles;
    }

    private boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    private boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    private int checkForText(List<String> strings) {
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

    private double checkBeforeAndAfter(int index, List<String> strings) {
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
}
