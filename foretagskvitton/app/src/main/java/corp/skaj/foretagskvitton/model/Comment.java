package corp.skaj.foretagskvitton.model;

/**
 * This class holds comments for documenting Product, Company, Purchase and Employee.
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