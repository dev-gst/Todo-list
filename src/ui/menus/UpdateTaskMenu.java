package ui.menus;

import model.Task;
import model.TaskStatus;

import java.util.List;
import java.util.Scanner;

public class UpdateTaskMenu {

    public static void updateTaskStatus(List<Task> tasks) {
        Scanner scanner = new Scanner(System.in);

        int id = -1;
        System.out.println("Enter id of task to update it's status");
        while (true) {
            try {
                id = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid ID");
            }

            for (Task task : tasks) {
                if (task.getId() == id) {
                    task.setStatus(changeStatus());
                    return;
                }
            }

            System.out.println("Task not found");
        }

    }

    private static TaskStatus changeStatus() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter new status for the task");
        System.out.println("1 - TODO");
        System.out.println("2 - DOING");
        System.out.println("3 - DONE");

        int status = -1;
        while (true) {
            try {
                status = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid status");
            }

            switch (status) {
                case 1:
                    return TaskStatus.TODO;
                case 2:
                    return TaskStatus.DOING;
                case 3:
                    return TaskStatus.DONE;
                default:
                    System.out.println("Invalid status");
            }
        }
    }
}
