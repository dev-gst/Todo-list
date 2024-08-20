package repositories;

import models.SortMethod;
import models.TaskStatus;
import models.entities.Task;

import java.io.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FileRepository {
    static private final String DIR_PATH = "src/repositories/data/";
    static private final String FILE_PATH = DIR_PATH + "tasks.csv";
    static private int CURRENT_ID = 1;

    private final List<Task> taskList;

    public FileRepository(List<Task> taskList) {
        this.taskList = taskList;
        initReader();
    }

    public void saveAll() {
        if (taskList.isEmpty()) {
            return;
        }

        // Only reset file on the first iteration
        for (int i = 0; i < taskList.size(); i++) {
            save(taskList.get(i), i != 0);
        }
    }

    public void save(Task task, boolean append) {
        try (FileWriter fileWriter = new FileWriter(FILE_PATH, append);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            String line = task.getId() + "," +
                    task.getName() + "," +
                    task.getDescription() + "," +
                    task.getPriority() + "," +
                    task.getStatus() + "," +
                    task.getDeadline() + "," +
                    task.getCategory() + "\n";

            bufferedWriter.write(line);

        } catch (IOException e) {
            System.out.println("Error while saving task: " + e.getMessage());
        }
    }

    // To prevent modification of original list
    public List<Task> getTasks() {
        return new ArrayList<>(this.taskList);
    }

    public void add(Task task) {
        task.setID(CURRENT_ID);
        this.taskList.add(task);
        save(task, true);
        CURRENT_ID++;
    }

    public void removeByID(int id) {
        this.taskList.removeIf(task -> task.getId() == id);
        saveAll();
    }

    public void sort(SortMethod sortBy) {
        switch (sortBy) {
            case BY_STATUS:
                taskList.sort(Comparator.comparing(Task::getStatus));
            case BY_CATEGORY:
                taskList.sort(Comparator.comparing(Task::getCategory));
            default: // By_PRIORITY
                taskList.sort(Comparator.comparingInt(Task::getPriority));
        }

        saveAll();
    }

    private void initReader() {
        createDataDirIfNotExists();

        File csvFile = new File(FILE_PATH);
        if (!csvFile.exists()) {
            return;
        }

        readFileData();
    }

    private void readFileData() {
        try (FileReader fileReader = new FileReader(FILE_PATH);
            BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Task task = constructTaskHelper(line);
                this.taskList.add(task);
                CURRENT_ID++;
            }

        } catch (IOException e) {
            System.out.println("Error while reading tasks: " + e.getMessage());
        }
    }

    private Task constructTaskHelper(String line) {
        String[] fields = line.trim().split(",");

        Task task = new Task();
        task.setID(Integer.parseInt(fields[0]));
        task.setName(fields[1]);
        task.setDescription(fields[2]);
        task.setPriority(Integer.parseInt(fields[3]));
        task.setStatus(TaskStatus.valueOf(fields[4]));
        task.setDeadline(ZonedDateTime.parse(fields[5]));
        task.setCategory(fields[6]);

        return task;
    }

    private void createDataDirIfNotExists() {
        try {
            File dir = new File(DIR_PATH);
            if (!dir.exists()) {
                boolean ignored = dir.mkdir();
            }
        } catch (SecurityException e) {
            System.out.println("Error while creating data dir: " + e.getMessage());
        }
    }
}
