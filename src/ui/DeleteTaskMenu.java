package ui;

import java.util.Scanner;

public class DeleteTaskMenu {

    public static int deleteById() {
        Scanner scanner = new Scanner(System.in);
        int id = -1;
        do {
            printDeleteMenu();
            String usrInput = scanner.nextLine();

            try {
                id = Integer.parseInt(usrInput);
            } catch (Exception e) {
                System.out.println("Please enter a valid ID");
            }

        } while (id <= 0);

        return id;
    }

    private static void printDeleteMenu() {
        System.out.println("Input task id: ");
    }
}
