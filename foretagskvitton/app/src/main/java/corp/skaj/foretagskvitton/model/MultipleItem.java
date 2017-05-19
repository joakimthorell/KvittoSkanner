package corp.skaj.foretagskvitton.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class MultipleItem implements MultiItemEntity{

    public static final int EMPLOYEE = 13;
    public static final int CARD = 23;
    public static final int COMMENT = 31;
    private String content;
    private int itemType;

    public MultipleItem(int itemType, String content) {
        this.content = content;
        this.itemType = itemType;
    }

    public int getItemType() {
        return itemType;
    }

    public String getContent() {
        return content;
    }
}
