package corp.skaj.foretagskvitton.exceptions;

/**
 *
 */
public class NoSuchPurchaseException extends Exception {
    public NoSuchPurchaseException() {
        System.out.println("No such Purchase in listOfPurchases");
    }
}
