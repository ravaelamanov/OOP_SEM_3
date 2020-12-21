package TaskChanges;

import DTO.TaskChange;

public class Comment extends TaskChange {
    private String comment;

    public Comment() {}

    public Comment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
