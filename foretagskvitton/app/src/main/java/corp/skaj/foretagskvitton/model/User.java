package corp.skaj.foretagskvitton.model;

/**
 * Created by annekeller on 2017-04-05.
 */

public class User extends Employee {
    private static User user = new User();

    private User () {
       //TODO
        //Here we have to collect saved data, when app is closed and we reopen... We always want the same user.
    }

    /**
     * This method returns the user
    * @return the only user
     */
    public static User getUser() {
        return user;
    }

}
