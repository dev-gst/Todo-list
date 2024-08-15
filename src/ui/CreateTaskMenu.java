package ui;

import model.TaskDTO;
import model.TaskStatus;

import java.time.ZonedDateTime;
import java.util.Scanner;

public class CreateTaskMenu {

    public static TaskDTO createTask() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Task Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Task Description: ");
        String description = scanner.nextLine();

        System.out.print("Enter Task Priority (1 - 5): ");
        int priority;
        while (true) {
            try {
                priority = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.print("Please enter a number between 1 and 5: ");
                continue;
            }

            if (priority > 0 && priority <= 5) {
                break;
            } else {
                System.out.print("Please enter a number between 1 and 5: ");
            }
        }

        System.out.print("Please enter a deadline in this format (2024-08-13T12:00:00+03:00): ");
        ZonedDateTime deadline = null;
        while (deadline == null) {
            try {
                deadline = ZonedDateTime.parse(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid format for deadline");
            }
        }

        System.out.println("Please enter a category: ");
        String category;
        while (true) {
            category = scanner.nextLine();
            if (!category.isBlank()) {
                break;
            } else {
                System.out.print("Please enter a category: ");
            }
        }

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setName(name);
        taskDTO.setDescription(description);
        taskDTO.setCategory(category);
        taskDTO.setDeadline(deadline);
        taskDTO.setPriority(priority);
        taskDTO.setStatus(TaskStatus.TODO);

        return taskDTO;
    }
}
