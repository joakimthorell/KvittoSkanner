package corp.skaj.foretagskvitton.exceptions;

/**
 *
 */
public class NoSuchEmployeeException extends Exception {
    public NoSuchEmployeeException () {
        System.out.println("No such employee in listOfPurchases");
    }
}
