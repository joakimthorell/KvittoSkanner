package corp.skaj.foretagskvitton.exceptions;

/**
 * Created by mattsson on 2017-04-12.
 */

public class NoSuchCommentException extends RuntimeException {
    /*
    Extends RuntimeException makes the Exception UnChecked
     */
    public NoSuchCommentException() {
        System.out.println("No comment matching arguments found in listOfComments");
    }
}
