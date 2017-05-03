package corp.skaj.foretagskvitton.model;

import java.io.Serializable;

/**
 *
 */
public class Comment implements Serializable {
    private String comment;

    public Comment(String comment) {
        this.comment = comment;
    }

    /**
     *
     * @return comment
     */
    public String getComment() {
        return comment;
    }

    /**
     *
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
}