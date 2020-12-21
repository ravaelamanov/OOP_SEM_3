package TaskChanges;

import DTO.TaskChange;
import util.TaskState;

public class StateChange extends TaskChange {
    private TaskState state;

    public StateChange() {}

    public StateChange(TaskState state) {
        this.state = state;
    }

    public TaskState getState() {
        return state;
    }

    public void setState(TaskState state) {
        this.state = state;
    }
}
