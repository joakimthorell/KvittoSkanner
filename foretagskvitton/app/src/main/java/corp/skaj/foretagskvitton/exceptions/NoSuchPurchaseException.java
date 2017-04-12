package corp.skaj.foretagskvitton.exceptions;

/**
 * Created by mattsson on 2017-04-12.
 */

public class NoSuchPurchaseException extends Exception {
    public NoSuchPurchaseException() {
        System.out.println("No such Purchase in listOfPurchases");
    }
}
