package corp.skaj.foretagskvitton.model;

/**
 * Holds information about users comments. Gets and sets a comment
 */
public class Comment  {
    private String comment;

    public Comment(String comment) {
        this.comment = comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }


}