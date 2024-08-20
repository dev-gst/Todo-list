package ui.menus;

import models.entities.Task;

import java.util.List;

public class ListTasksMenu {

    public static void listTasks(List<Task> tasks) {
        if (!tasks.isEmpty()) {
            System.out.println("*******************************************");
            for (Task task : tasks) {
                System.out.println(task);
                System.out.println("*******************************************");
            }
        } else {
            System.out.println("No tasks found");
        }
    }
}
