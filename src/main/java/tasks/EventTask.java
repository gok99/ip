package tasks;

import java.time.LocalDateTime;

import bot.TaskType;

/**
 * Event task
 */
public class EventTask extends Task {

    private String taskText;
    private LocalDateTime taskTime;
    private final TaskType TASK_TYPE = TaskType.Event;

    /**
     * Constructor for EventTask
     *
     * @param taskText explanatory text for task
     * @param taskTime time at which task is to be executed
     */
    public EventTask(String taskText, LocalDateTime taskTime) {
        this.taskText = taskText.trim();
        this.taskTime = taskTime;
    }

    @Override
    public String getTaskDesc() {
        return String.format("%s (at: %s)", taskText, getTaskTime());
    }

    @Override
    public String getTaskText() {
        return this.taskText;
    }

    @Override
    public String getTaskTime() {
        return this.taskTime.format(Task.OUTPUT_TIME_FORMAT);
    }

    @Override
    public TaskType getTaskType() {
        return this.TASK_TYPE;
    }

}
