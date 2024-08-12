package model;

import java.time.ZonedDateTime;

public class TaskDTO {

    private final String category;
    private final String name;
    private final String description;
    private final int priority;
    private final TaskStatus status;
    private final ZonedDateTime deadline;

    public TaskDTO(String name, String description, int priority,
                   TaskStatus status, ZonedDateTime deadline, String category) {
        this.category = category;
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.deadline = deadline;
    }

    public TaskDTO(String name, String description, int priority,
                   ZonedDateTime deadline, String category) {
        this(name, description, priority, TaskStatus.TODO, deadline, category);
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public ZonedDateTime getDeadline() {
        return deadline;
    }
}
