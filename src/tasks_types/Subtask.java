package tasks_types;

import java.util.Objects;

public class Subtask extends Task {

    private long myEpicID;

    public Subtask(String nameTask, String description, long myEpicID) {
        super(nameTask, description);
        this.myEpicID = myEpicID;
    }

    public long getMyEpicID() {
        return myEpicID;
    }

    public void setMyEpicID(long myEpicID) {
        this.myEpicID = myEpicID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Subtask subtask = (Subtask) o;
        return myEpicID == subtask.myEpicID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), myEpicID);
    }

    public TypesTasks getType() {
        return TypesTasks.SUBTASK;
    }

    @Override
    public String toString() {
        return super.getNumberId() + "," +
                getType() + "," +
                super.getNameTask() + "," +
                super.getStatus() + "," +
                super.getDescription() + "," +
                myEpicID;
    }
}