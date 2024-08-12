package services;

import model.SortMethod;
import model.Task;
import model.TaskDTO;

import java.util.ArrayList;

public class TaskServices {
    private final ArrayList<Task> tasks;
    private int currentId;
    private SortMethod sortBy;

    public TaskServices() {
        this.tasks = new ArrayList<>();
        this.currentId = 1;
        this.sortBy = SortMethod.BY_PRIORITY;
    }

    public SortMethod getSortBy() {
        return sortBy;
    }

    public void setSortBy(SortMethod sortBy) {
        this.sortBy = sortBy;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void createTask(TaskDTO taskDTO) {
        Task task = new Task(currentId);
        task.setName(taskDTO.getName());
        task.setDescription(taskDTO.getDescription());
        task.setPriority(taskDTO.getPriority());
        task.setDeadline(taskDTO.getDeadline());
        task.setCategory(taskDTO.getCategory());
        task.setStatus(taskDTO.getStatus());

        tasks.add(task);
        incrementId();
    }

    public void deleteTaskById(int id) {
        tasks.removeIf(task -> task.getId() == id);
    }

    private void incrementId() {
        this.currentId++;
    }
}
