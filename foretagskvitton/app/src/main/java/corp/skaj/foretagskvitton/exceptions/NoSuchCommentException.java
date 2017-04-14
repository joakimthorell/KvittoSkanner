package corp.skaj.foretagskvitton.exceptions;

/**
 * This class extends RuntimeException. Makes the exception unchecked.
 */
public class NoSuchCommentException extends RuntimeException {
    public NoSuchCommentException() {
        System.out.println("No comment matching arguments found in listOfComments");
    }
}
