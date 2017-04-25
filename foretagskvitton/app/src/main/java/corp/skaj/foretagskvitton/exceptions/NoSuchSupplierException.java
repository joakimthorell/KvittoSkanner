package corp.skaj.foretagskvitton.exceptions;

/**
 *
 */
public class NoSuchSupplierException extends Exception{
    public NoSuchSupplierException () {
        System.out.println("No such supplier in listOfSupplier");
    }

}
