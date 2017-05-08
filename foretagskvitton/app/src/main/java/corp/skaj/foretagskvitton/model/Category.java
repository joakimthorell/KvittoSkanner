package corp.skaj.foretagskvitton.model;

//TODO How do we use this?

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Category {
        //Array of categories in alphabetic order
        private final static List<String> categories = new ArrayList<>(Arrays.asList(
                "Bensin",
                "Hotell",
                "Kontorsmaterial",
                "Mat",
                "Porto",
                "Representation",
                "Resor",
                "Transport"));

        private static String category = "";

        private Category () {

        }

        public static String getCategory () {
                return category;
        }

        public static List<String> getCategories () {
                return categories;
        }
}
