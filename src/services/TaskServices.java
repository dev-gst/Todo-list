package services;

import models.SortMethod;
import models.entities.Task;
import models.DTOs.TaskDTO;
import repositories.FileRepository;

import java.util.List;

public class TaskServices {
    private final FileRepository fileRepository;

    private SortMethod sortBy;

    public TaskServices(FileRepository fileRepository, SortMethod defaultSortMethod) {
        this.fileRepository= fileRepository;
        this.sortBy = defaultSortMethod;
    }

    public TaskServices(FileRepository fileRepository) {
        this(fileRepository, SortMethod.BY_PRIORITY);
    }

    public SortMethod getSortBy() {
        return sortBy;
    }

    public void setSortBy(SortMethod sortBy) {
        this.sortBy = sortBy;
    }

    public List<Task> getTasks() {
        return fileRepository.getTasks();
    }

    public void createTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setName(taskDTO.getName());
        task.setDescription(taskDTO.getDescription());
        task.setPriority(taskDTO.getPriority());
        task.setDeadline(taskDTO.getDeadline());
        task.setCategory(taskDTO.getCategory());
        task.setStatus(taskDTO.getStatus());

        fileRepository.add(task);
    }

    public void deleteTaskById(int id) {
        fileRepository.removeByID(id);
    }

    public void sort() {
        this.fileRepository.sort(this.sortBy);
    }
}
