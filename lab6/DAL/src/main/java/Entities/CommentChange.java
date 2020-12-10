package Entities;

import javax.persistence.*;

@Entity
@Access(value = AccessType.FIELD)
@DiscriminatorValue("COMMENT")
public class CommentChange extends TaskChange {

    @Column
    private String com;

    public CommentChange() {
        com = null;
    }

    public String getComment() {
        return com;
    }

    public void setComment(String com) {
        this.com = com;
    }
}
