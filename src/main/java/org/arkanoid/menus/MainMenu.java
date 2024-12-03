package org.arkanoid.menus;

import java.util.Scanner;

public class MainMenu {
    private final Scanner scanner;

    public MainMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    public int show() {
        System.out.println("=== Arkanoid Game ===");
        System.out.println("1. Start Game");
        System.out.println("2. Exit");

        int choice = 0;

        while (true) {
            System.out.print("Select an option: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == 1 || choice == 2) {
                    return choice;
                } else {
                    System.out.println("Invalid choice. Please enter 1 to start the game or 2 to exit.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number (1 or 2).");
                scanner.nextLine();
            }
        }
    }
}
