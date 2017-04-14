package corp.skaj.foretagskvitton.exceptions;

/**
 *
 */
public class NoSuchCardException extends Exception{
    public NoSuchCardException () {
        System.out.println("No such card in listOfCards");
    }
}
