package corp.skaj.foretagskvitton.exceptions;

/**
 * Created by annekeller on 2017-04-12.
 */

public class NoSuchEmployeeException extends Exception {
    public NoSuchEmployeeException () {
        System.out.println("No such employee in listOfPurchases");
    }
}
