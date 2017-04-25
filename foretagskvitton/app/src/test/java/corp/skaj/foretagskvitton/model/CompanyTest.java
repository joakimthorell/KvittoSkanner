package corp.skaj.foretagskvitton.model;

import org.junit.Before;

import static org.junit.Assert.*;

/**
 * Created by annekeller on 2017-04-13.
 */
public class CompanyTest {
    Company company;

    @Before
    public void setup () {
        company = new Company("testCompany");
    }

}