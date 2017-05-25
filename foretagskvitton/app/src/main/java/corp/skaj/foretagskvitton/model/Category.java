package corp.skaj.foretagskvitton.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class holds categories options for purchase.
 * Each category has a color in hex.
 */
public enum Category {
    BENSIN("#E43F3F"), // red
    HOTELL("#FFFF66"), // light yellow
    KONTORSMATERIAL("#FFCCE5"), // pinkish
    MAT("#99FF33"), // green
    PORTO("#33FFFF"), // light blue
    REPRESENTATION("#FFB266"), // orange/brown
    RESOR("#FF66FF"), // purpleish
    TRANSPORT("#6666FF"); // darker blue

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

    public boolean equals(Category category) {
        return this.name().equals(category.name());
    }
}
