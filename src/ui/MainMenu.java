package ui;

import models.SortMethod;
import models.DTOs.TaskDTO;
import services.TaskServices;
import ui.menus.*;

import java.util.Scanner;
public class MainMenu {
    private static final int MENU_ENTRIES = 6;

    private final TaskServices taskServices;

    public MainMenu(TaskServices taskServices) {
        this.taskServices = taskServices;
    }

    public void startMenu() {
        while (true) {
            int usrInput = getUserInput();
            switch (usrInput) {
                case 1:
                    ListTasksMenu.listTasks(taskServices.getTasks());
                    break;
                case 2:
                    TaskDTO taskDTO = CreateTaskMenu.createTask();
                    taskServices.createTask(taskDTO);
                    taskServices.sort();
                    break;
                case 3:
                    UpdateTaskMenu.updateTaskStatus(taskServices.getTasks());
                    taskServices.sort();
                    break;
                case 4:
                    taskServices.deleteTaskById(DeleteTaskMenu.deleteById());
                    taskServices.sort();
                    break;
                case 5:
                    SortMethod sortMethod = SortMenu.getSortBy();
                    taskServices.setSortBy(sortMethod);
                    taskServices.sort();
                    break;
                case MENU_ENTRIES:
                    return;
                default:
                    System.out.println("Please enter a number of 1 to " + MENU_ENTRIES);
            }
        }
    }

    private void printMainMenu() {
        System.out.println();
        System.out.println("Please, press a number of 1 to " + MENU_ENTRIES + ":");
        System.out.println("1 - List tasks");
        System.out.println("2 - Create task");
        System.out.println("3 - Update task status");
        System.out.println("4 - Delete task");
        System.out.println("5 - Sort tasks");
        System.out.println(MENU_ENTRIES + " - Exit program");
        System.out.println();
    }

    private int getUserInput() {
        Scanner scanner = new Scanner(System.in);
        int usrInput;
        while (true) {
            printMainMenu();
            try {
                System.out.print("> ");
                String input = scanner.nextLine();
                usrInput = Integer.parseInt(input);
                if (usrInput >= 1 && usrInput <= MENU_ENTRIES) {
                    break;
                }

            } catch (Exception e) {
                System.out.println("Please enter a number of 1 to " + MENU_ENTRIES);
            }
        }

        return usrInput;
    }
}
