package ui;

import model.SortMethod;

import java.util.Scanner;

public class SortMenu {

    public static SortMethod getSortBy() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printSortMenu();
            String usrInput = scanner.nextLine();

            switch (usrInput){
                case "1":
                    return SortMethod.BY_PRIORITY;
                case "2":
                    return SortMethod.BY_CATEGORY;
                case "3":
                    return SortMethod.BY_STATUS;
                default:
                    System.out.println("Invalid input");
            }
        }
    }

    private static void printSortMenu() {
        System.out.println("Sort by: ");
        System.out.println("1 - Priority");
        System.out.println("2 - Category");
        System.out.println("3 - Status");
    }
}
