package corp.skaj.foretagskvitton.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class holds categories options for purchase. Each category has a color in hex.
 */
public enum Category {
    BENSIN("#E45664"), // red
    HOTELL("#FFE0AB"), // light yellow
    MAT("#B7E5B7"), // green
    REPRESENTATION("#C0D6E4"), // light blue
    RESOR("#99D5CF"), // turqoise
    TRANSPORT("#FFCCE5"), // baby pink
    ÖVRIGT("#5B005B"); // purple

    private final String color;

    private Category(String color) {
        this.color = color;
    }

    private Category() {
        // standard color for all that is not set
        color = "#FFFFFF";
    }

    public String getColor() {
        if (color == null) {
            System.out.println("There was no color for that Category, returning white");
        }
        return color;
    }

    public static String[] getCategoriesArray() {
        return Arrays.toString(values()).replaceAll("^.|.$", "").split(", ");
    }

    public static List<String> getCategories() {
        List<String> list = new ArrayList<>();
        String[] arrList = getCategoriesArray();
        for (int i = 0; i < arrList.length; i++) {
            list.add(arrList[i]);
        }
        return list;
    }
}
