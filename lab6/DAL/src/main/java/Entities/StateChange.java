package Entities;

import util.TaskState;

import javax.persistence.*;

@Entity
@Access(value = AccessType.FIELD)
public class StateChange extends TaskChange {

    @Enumerated(EnumType.STRING)
    private TaskState state;

    public StateChange() {
        state = null;
    }

    public TaskState getState() {
        return state;
    }

    public void setState(TaskState state) {
        this.state = state;
    }
}
