package corp.skaj.foretagskvitton.exceptions;

/**
 * Created by lasanjin on 4/12/17.
 */
public class NoSuchCompanyException extends Exception {
    public NoSuchCompanyException(String companyName) {
        System.out.println(companyName + " does not exist");
    }
}
