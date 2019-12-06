package by.dorozhko.poputka.entity;


import java.util.Objects;

public class Commentary extends Entity {
    /**
     * User who left comment.
     */
    private User commentator;
    /**
     * User about whom this comment.
     */
    private User commentAbout;
    /**
     * Text of comment.
     */
    private String comment;

    /**
     * public default constructor.
     */
    public Commentary() {
    }


    /**
     * Get comment author method.
     * @return author of comment.
     */
    public User getCommentator() {
        return commentator;
    }

    /**
     * Set author of comment method.
     *
     * @param commentator author of comment.
     */
    public void setCommentator(User commentator) {
        this.commentator = commentator;
    }

    /**
     * Get user about whom his comment.
     * @return user about this comment.
     */
    public User getCommentAbout() {
        return commentAbout;
    }

    /**
     * Set user about whom his comment.
     * @param commentAbout user about this comment.
     */
    public void setCommentAbout(User commentAbout) {
        this.commentAbout = commentAbout;
    }

    /**
     * Get text of comment method.
     * @return text of comment.
     */
    public String getComment() {
        return comment;
    }

    /**
     * Set text of comment.
     * @param comment text of comment.
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o - the reference object with which to compare.
     * @return true if this object is the same as the obj argument;
     * false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) {
            return false;
        }
        Commentary comment1 = (Commentary) o;
        return Objects.equals(commentator, comment1.commentator) &&
                Objects.equals(commentAbout, comment1.commentAbout) &&
                Objects.equals(comment, comment1.comment);
    }

    /**
     * Returns a hash code value for the object. This method is
     * supported for the benefit of hash tables such as those
     * provided by HashMap.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), commentator, commentAbout, comment);
    }

    /**
     * Returns a string representation of the object. In general,
     * the toString method returns a string that "textually represents"
     * this object. The result should be a concise but informative
     * representation that is easy for a person to read.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "id=" + getId()
                + "commentator=" + commentator +
                ", commentAbout=" + commentAbout +
                ", comment='" + comment + '\'' +
                '}';
    }
}
