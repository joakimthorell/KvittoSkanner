package corp.skaj.foretagskvitton.services;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by annekeller on 2017-04-27.
 */

public class SearchThroughReceipt {


    double totalCost;
    List<String> list;
    List<Double> listOfDoubles = new ArrayList<>();


    public void findTotalCost() {
    findAllDoubles();
    totalCost = findBiggestDouble();
    }

    public void findCardNumber () {

    }

    public void findDate ( ) {

    }

    public void findProducts () {

    }

    public void findAllDoubles () {
        double temp = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).contains(",")) {
                temp = Double.parseDouble(list.get(i));
                listOfDoubles.add(temp);
            }
        }
    }

    public double findBiggestDouble () {
        double biggestDouble = 0;
        for (int i = 0; i < listOfDoubles.size(); i++) {
            if (listOfDoubles.get(i) > listOfDoubles.get(i+1)) {
                biggestDouble = listOfDoubles.get(i);
            }
        }
        return biggestDouble;
    }
}
