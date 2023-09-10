package task;

public class Task implements Comparable<Task> {
    private int creationTime;
    private int exeTime;
    private static int tasksNo = 0;
    private int taskID;
    private int priority;

    public int getTaskID() {
        return taskID;
    }

    public Task() {
        taskID = tasksNo + 1;
        tasksNo++;
    }

    public int getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(int creationTime) {
        this.creationTime = creationTime;
    }

    public int getExeTime() {
        return exeTime;
    }

    public void setExeTime(int exeTime) {
        this.exeTime = exeTime;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(Task o) {
        if (this.priority > o.priority) {
            return -1;
        }
        if (this.priority < o.priority) {
            return 1;
        }
        if (this.exeTime > o.exeTime) {
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskID=" + taskID +
                ", creationTime=" + creationTime +
                ", executionTime=" + exeTime +
                ", priority=" + priority +
                '}';
    }
}
