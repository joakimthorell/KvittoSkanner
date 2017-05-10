package corp.skaj.foretagskvitton.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Category {

    BENSIN,
    HOTELL,
    KONTORSMATERIAL,
    MAT,
    PORTO,
    REPRESENTATION,
    RESOR,
    TRANSPORT;

    public static ArrayList<String> getCategoriesAsArrayList() {
        String[] arr = Arrays.toString(Category.values()).replaceAll("^.|.$", "").split(", ");
        List<String> categories = Arrays.asList(arr);
        ArrayList<String> strings = new ArrayList<>();
        strings.addAll(categories);
        return strings;
    }

    public static String[] getCategoriesAsArray() {
        return Arrays.toString(Category.values()).replaceAll("^.|.$", "").split(", ");
    }
}
