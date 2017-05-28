package corp.skaj.foretagskvitton.model;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class CategoryTest {
    private Category category;

    @Before
    public void setup() {
        category = Category.BENSIN;
    }

    @Test
    public void testCat() {

        String[] catTest1 = Category.getCategoriesArray();
        String catTest2 = Category.getCategories().get(0);
        String catTest3 = Category.getCategories().get(6);
        assertEquals(catTest1[0], "BENSIN");
        assertEquals(catTest2, "BENSIN");
        assertEquals(catTest3, "ÖVRIGT");
    }


}
