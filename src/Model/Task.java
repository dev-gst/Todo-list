package Model;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class Task {
    private final int id;

    private String name;
    private String description;
    private int priority;
    private TaskStatus status;
    private ZonedDateTime deadline;
    private List<String> categories;

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

    // Priority has to be between 1 and 5
    public void setPriority(int priority) {
        if (priority < 1 || priority > 5) {
            throw new IllegalArgumentException();
        }

        this.priority = priority;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
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

    public String toString() {
        String taskToString;
        if (this.status == TaskStatus.TODO || this.status == TaskStatus.DOING) {
            taskToString =
                    "Task" +
                            "\nId: " + this.id +
                            "\nName: " + this.name +
                            "\nDescription: " + this.description +
                            "\n\nPRIORITY: " + this.priority +
                            "\nDeadline: " + this.deadline +
                            "\n\nStatus: " + this.status +
                            "\nCategories: " + this.categories.toString();
        } else {
            taskToString =
                    "Task" +
                            "\nId: " + this.id +
                            "\nName: " + this.name +
                            "\nDescription: " + this.description +
                            "\n\nStatus: " + this.status +
                            "\nCategories: " + this.categories.toString();
        }

        return taskToString;
    }
}
