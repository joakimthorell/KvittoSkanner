package corp.skaj.foretagskvitton.exceptions;

/**
 *
 */
public class IllegalInputException extends Exception{
    public IllegalInputException (Object object) {
        System.out.println("Input not accepted. Exception throw by" + object.getClass());
    }
}
