package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import bot.TaskType;

/**
 * Abstract task class
 */
public abstract class Task {

    public static final DateTimeFormatter INPUT_TIME_FORMAT = DateTimeFormatter.ofPattern("d/MM/yyyy kkmm");
    public static final DateTimeFormatter OUTPUT_TIME_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy - hh mm a");
    private boolean isDone = false;

    /**
     * Mark a task as complete
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Get completion status of task
     *
     * @return true iff task is complete
     */
    public boolean getTaskDone() {
        return this.isDone;
    }

    /**
     * Convert task object to string
     *
     * @return String representing task
     */
    public String serialize() {
        return String.format("%s,%b,%s,%s",
                this.getTaskType(),
                this.getTaskDone(),
                this.getTaskText(),
                this.getTaskTime());
    }

    /**
     * Convert string representation of task
     * to task object
     *
     * @param s string representation of task
     * @return task object represented by string
     */
    public static Task deserialize(String s) {
        String[] parts = s.split(",");
        TaskType taskType = TaskType.valueOf(parts[0]);

        Task task;
        LocalDateTime taskTime;

        switch (taskType) {
        case Deadline:
            taskTime = LocalDateTime.parse(parts[3], OUTPUT_TIME_FORMAT);
            task = new DeadlineTask(parts[2], taskTime);
            break;
        case Event:
            taskTime = LocalDateTime.parse(parts[3], OUTPUT_TIME_FORMAT);
            task = new EventTask(parts[2], taskTime);
            break;
        case Todo:
            task = new TodoTask(parts[2]);
            break;
        default:
            task = new GeneralTask("");
        }

        if (Boolean.parseBoolean(parts[1])) {
            task.markDone();
        }
        return task;
    }

    @Override
    public String toString() {
        String taskChecked = this.isDone ? "X" : " ";
        return String.format("[%s][%s] %s", this.getTaskType().getSymbol(), taskChecked, this.getTaskDesc());
    }

    /**
     * Get a task's description details
     *
     * @return task description string
     */
    public abstract String getTaskDesc();

    /**
     * Get text associated with task
     *
     * @return Task's text
     */
    public abstract String getTaskText();

    /**
     * Get time associated with task
     *
     * @return Task's time
     */
    public abstract String getTaskTime();

    /**
     * Get task's TaskType
     *
     * @return Task's TaskType
     */
    public abstract TaskType getTaskType();

    /**
     * Get whether task is done
     *
     * @return true iff task is done
     */
    public boolean isTaskDone() {
        return this.isDone;
    }

}
