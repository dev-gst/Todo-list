package model;

import java.time.ZonedDateTime;
import java.util.List;

public class TaskDTO {

    private final List<String> categories;
    private final String name;
    private final String description;
    private final int priority;
    private final TaskStatus status;
    private final ZonedDateTime deadline;

    public TaskDTO(String name, String description, int priority,
                   TaskStatus status, ZonedDateTime deadline, List<String> categories) {
        this.categories = categories;
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.deadline = deadline;
    }

    public TaskDTO(String name, String description, int priority,
                   ZonedDateTime deadline, List<String> categories) {
        this(name, description, priority, TaskStatus.TODO, deadline, categories);
    }

    public List<String> getCategories() {
        return categories;
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
