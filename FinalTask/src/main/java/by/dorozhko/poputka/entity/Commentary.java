package by.dorozhko.poputka.entity;


import java.util.Objects;

public class Commentary extends Entity {
    private User commentator;
    private User commentAbout;
    private String comment;

    public Commentary() {
    }

    public Commentary(final int id, User commentator, User commentAbout, String comment) {
        super(id);
        this.commentator = commentator;
        this.commentAbout = commentAbout;
        this.comment = comment;
    }

    public User getCommentator() {
        return commentator;
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    public void setCommentator(User commentator) {
        this.commentator = commentator;
    }

    public User getCommentAbout() {
        return commentAbout;
    }

    public void setCommentAbout(User commentAbout) {
        this.commentAbout = commentAbout;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), commentator, commentAbout, comment);
    }

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
