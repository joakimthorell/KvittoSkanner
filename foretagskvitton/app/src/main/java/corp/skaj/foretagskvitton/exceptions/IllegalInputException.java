package corp.skaj.foretagskvitton.exceptions;

/**
 * Created by annekeller on 2017-04-12.
 */

public class IllegalInputException extends Exception{
    public IllegalInputException (Object object) {
        System.out.println("Input not accepted. Exception throw by" + object.getClass());
    }
}
