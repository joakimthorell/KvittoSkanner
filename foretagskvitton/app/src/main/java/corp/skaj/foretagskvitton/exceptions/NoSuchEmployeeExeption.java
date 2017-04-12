package corp.skaj.foretagskvitton.exceptions;

/**
 * Created by annekeller on 2017-04-12.
 */

public class NoSuchEmployeeExeption extends Exception {
    public NoSuchEmployeeExeption () {
        System.out.println("No such employee in listOfPurchases");
    }
}
