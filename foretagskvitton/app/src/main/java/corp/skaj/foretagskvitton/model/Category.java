package corp.skaj.foretagskvitton.model;

import java.util.Arrays;

public enum Category {
    BENSIN,
    HOTELL,
    KONTORSMATERIAL,
    MAT,
    PORTO,
    REPRESENTATION,
    RESOR,
    TRANSPORT;

    public static String[] getCategoriesArray() {
        return Arrays.toString(values()).replaceAll("^.|.$", "").split(", ");
    }

    public boolean equals(Category category) {
        return this.name().equals(category.name());
    }

    public static Category[] categoriesInArr() {
        Category[] cat = new Category[]{BENSIN, HOTELL, KONTORSMATERIAL, MAT,
                PORTO, REPRESENTATION, RESOR, TRANSPORT};
        return cat;
    }
}
