package services;

import model.SortMethod;
import model.Task;

import java.util.ArrayList;
import java.util.Comparator;

public class SortTasks {

    public static void sort(ArrayList<Task> tasks, SortMethod sortMethod) {
        switch (sortMethod) {
            case BY_STATUS:
                byStatus(tasks);
            case BY_CATEGORY:
                byCategory(tasks);
            default:
                byPriority(tasks);
        }
    }

    private static void byPriority(ArrayList<Task> tasks) {
        tasks.sort(Comparator.comparingInt(Task::getPriority));
    }

    private static void byCategory(ArrayList<Task> tasks) {
        tasks.sort(Comparator.comparing(Task::getCategory));
    }

    private static void byStatus(ArrayList<Task> tasks) {
        tasks.sort(Comparator.comparing(Task::getStatus));
    }
}
