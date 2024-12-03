package org.arkanoid;

import org.arkanoid.interfaces.Renderable;
import org.arkanoid.objects.Ball;
import org.arkanoid.objects.Block;
import org.arkanoid.objects.Paddle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Field {
    private static final int WIDTH = 30;
    private static final int HEIGHT = 10;

    private final int BLOCK_ROWS = 4;
    private final char BORDER_CHAR = '@';
    private final char BLOCK_CHAR = '#';
    private final char BALL_CHAR = 'O';
    private final char PADDLE_CHAR = '=';
    private final char EMPTY_CHAR = ' ';

    private char[][] field = new char[HEIGHT][WIDTH];
    private List<Block> blocks = new ArrayList<>();
    private List<Renderable> renderableObjects = new ArrayList<>();


    public Field(Paddle paddle, Ball ball) {
        initializeBlocks();
        renderableObjects.add(paddle);
        renderableObjects.add(ball);
        renderableObjects.addAll(blocks);
    }


    private void initializeBlocks() {
        for (int y = 1; y < BLOCK_ROWS; y++) {
            for (int x = 1; x < WIDTH - 1; x++) {
                blocks.add(new Block(x, y));
            }
        }
    }

    public void clearField() {
        for (char[] row : field) {
            Arrays.fill(row, EMPTY_CHAR);
        }
    }

    public void drawField(Paddle paddle, Ball ball) {
        drawBorders();
        drawPaddle(paddle);
        drawBall(ball);
        drawBlocks();
        printField();
    }

    private void drawBorders() {
        for (int i = 0; i < WIDTH; i++) {
            field[0][i] = BORDER_CHAR;
            field[HEIGHT - 1][i] = BORDER_CHAR;
        }
        for (int i = 0; i < HEIGHT; i++) {
            field[i][0] = BORDER_CHAR;
            field[i][WIDTH - 1] = BORDER_CHAR;
        }
    }

    private void drawObjects() {
        clearPaddle();
        for (Renderable obj : renderableObjects) {
            if (obj instanceof Paddle) {
                drawPaddle((Paddle) obj);
            } else {
                int x = obj.getX();
                int y = obj.getY();
                char symbol = obj.getSymbol();

                if (x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT) {
                    field[y][x] = symbol;
                }
            }
        }
    }



    private void drawPaddle(Paddle paddle) {
        int paddleX = paddle.getX();
        int paddleWidth = paddle.getWIDTH();
        clearPaddle();

        for (int x = paddleX; x < paddleX + paddleWidth; x++) {
            if (x >= 0 && x < WIDTH) {
                field[HEIGHT - 2][x] = PADDLE_CHAR;
            }
        }
    }

    private void clearPaddle() {
        for (int x = 0; x < WIDTH; x++) {
            if (field[HEIGHT - 2][x] == PADDLE_CHAR) {
                field[HEIGHT - 2][x] = EMPTY_CHAR;
            }
        }
    }

    private void drawBall(Ball ball) {
        int x = ball.getX();
        int y = ball.getY();

        if (x >= 0 && x < field[0].length && y >= 0 && y < field.length) {
            field[y][x] = BALL_CHAR;
        }
    }

    private void drawBlocks() {
        for (Block block : blocks) {
            int blockX = block.getX();
            int blockY = block.getY();

            if (block.isDestroyed()) {
                field[blockY][blockX] = EMPTY_CHAR;
            } else {
                field[blockY][blockX] = BLOCK_CHAR;
            }
        }
    }

    private void printField() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                System.out.print(field[y][x]);
            }
            System.out.println();
        }
    }

    public void checkBlockCollision(Ball ball) {
        int ballX = ball.getX();
        int ballY = ball.getY();

        for (Block block : blocks) {
            if (block.getX() == ballX && block.getY() == ballY && !block.isDestroyed()) {
                ball.bounce("vertical");
                block.destroy();
                break;
            }
        }
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }
}
