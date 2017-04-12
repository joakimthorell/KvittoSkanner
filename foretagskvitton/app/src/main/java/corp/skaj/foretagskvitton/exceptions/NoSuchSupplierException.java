package corp.skaj.foretagskvitton.exceptions;

/**
 * Created by annekeller on 2017-04-12.
 */

public class NoSuchSupplierException extends Exception{
    public NoSuchSupplierException () {
        System.out.println("No such supplier in listOfSupplier");
    }

}
