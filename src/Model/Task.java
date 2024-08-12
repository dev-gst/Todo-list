package Model;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class Task {
    private final int id;
    private final List<String> categories;

    private String name;
    private String description;
    private int priority;
    private TaskStatus status;
    private ZonedDateTime deadline;

    public Task(int id) {
        this.id = id;
        this.categories = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void addCategory(String category) {
        categories.add(category);
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public ZonedDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(ZonedDateTime completionDate) {
        this.deadline = completionDate;
    }
}
