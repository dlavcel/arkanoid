package org.arkanoid.menus;

import java.util.Scanner;

public class GameOverMenu {
    private final Scanner scanner;

    public GameOverMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    public int show() {
        System.out.println("=== Game Over ===");
        System.out.println("1. Restart Game");
        System.out.println("2. Exit");

        int choice = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Select an option: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice == 1 || choice == 2) {
                    validInput = true;
                } else {
                    System.out.println("Invalid choice. Please enter 1 to restart or 2 to exit.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number (1 or 2).");
                scanner.nextLine();
            }
        }

        return choice;
    }
}
