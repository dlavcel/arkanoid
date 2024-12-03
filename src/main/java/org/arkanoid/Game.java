package org.arkanoid;

import org.arkanoid.menus.GameOverMenu;
import org.arkanoid.menus.MainMenu;
import org.arkanoid.objects.Ball;
import org.arkanoid.objects.Paddle;

import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MainMenu mainMenu = new MainMenu(scanner);
        GameOverMenu gameOverMenu = new GameOverMenu(scanner);

        while (true) {
            int choice = mainMenu.show();

            if (choice == 1) {
                boolean gameRunning = true;
                while (gameRunning) {
                    gameRunning = playGame(scanner, gameOverMenu);
                }
            } else if (choice == 2) {
                System.out.println("Bye-bye");
                break;
            }
        }
    }

    private static boolean playGame(Scanner scanner, GameOverMenu gameOverMenu) {
        Field field = new Field(paddle);
        Paddle paddle = new Paddle((field.getWIDTH() - Paddle.getWIDTH()) / 2);
        Ball ball = new Ball(field.getWIDTH() / 2 - 1, field.getHEIGHT() - 3);

        while (true) {
            updateField(field, paddle, ball);
            checkCollisions(field, ball, paddle);

            if (checkGameOver(ball, field, gameOverMenu)) {
                return false;
            }

            ball.updatePosition();
            paddleControl(scanner, paddle, field.getWIDTH());
        }
    }

    private static void updateField(Field field, Paddle paddle, Ball ball) {
        field.clearField();
        field.drawField(paddle, ball);
        field.checkBlockCollision(ball);
    }

    private static void checkCollisions(Field field, Ball ball, Paddle paddle) {
        if (ball.getX() == 1 || ball.getX() >= field.getWIDTH() - 2) {
            ball.bounce("horizontal");
        }
        if (ball.getY() <= 1) {
            ball.bounce("vertical");
        }
        paddleCollision(ball, paddle);
    }

    private static boolean checkGameOver(Ball ball, Field field, GameOverMenu gameOverMenu) {
        if (ball.getY() >= field.getHEIGHT() - 1) {
            System.out.println("GAME OVER!");
            int gameOverChoice = gameOverMenu.show();

            if (gameOverChoice == 1) {
                return true;
            } else if (gameOverChoice == 2) {
                System.out.println("Exiting the game. Goodbye!");
                System.exit(0);
            }
            return true;
        }
        return false;
    }

    private static void paddleCollision(Ball ball, Paddle paddle) {
        if (ball.getY() == paddle.getPOSITION_Y() &&
                (ball.getX() >= paddle.getX() && ball.getX() < paddle.getX() + paddle.getWIDTH())) {
            ball.bounce("vertical");
        }
    }

    private static void paddleControl(Scanner scanner, Paddle paddle, final int FIELD_WIDTH) {
        System.out.println("A - left, D - right");
        String command = scanner.nextLine().trim().toLowerCase();
        if (command.equals("a")) {
            paddle.moveLeft();
        } else if (command.equals("d")) {
            paddle.moveRight(FIELD_WIDTH);
        }
    }
}
