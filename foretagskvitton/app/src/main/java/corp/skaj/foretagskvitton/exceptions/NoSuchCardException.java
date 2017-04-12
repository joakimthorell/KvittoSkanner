package corp.skaj.foretagskvitton.exceptions;

/**
 * Created by annekeller on 2017-04-12.
 */

public class NoSuchCardException extends Exception{
    public NoSuchCardException () {
        System.out.println("No such card in listOfCards");
    }
}
