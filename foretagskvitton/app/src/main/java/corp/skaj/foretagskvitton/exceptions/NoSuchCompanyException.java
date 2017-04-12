package corp.skaj.foretagskvitton.exceptions;

/**
 *
 */
public class NoSuchCompanyException extends Exception {

    public NoSuchCompanyException(String companyName) {
        System.out.println(companyName + " does not exist");
    }
}
