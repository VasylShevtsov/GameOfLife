package com.stockcharts.interns.vasyls.life;
import java.lang.Math;
import java.awt.Color;
import edu.princeton.cs.introcs.*;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class LifeApp {

    private static final int WIDTH = 200;
    private static final int HEIGHT = 100;
    private static final int SCALING_FACTOR = 7;
    private static final double RANDOM_FACTOR = 0.5;
    private static final Picture screen = new Picture(WIDTH * SCALING_FACTOR, HEIGHT * SCALING_FACTOR);

    public static void main(String[] args) {



        int[][] grid = new int[HEIGHT][WIDTH];

        for (int j = 0; j < HEIGHT; j++) {
                for (int i = 0; i < WIDTH; i++) {
                    grid[j][i] = 0;
                    Color c = Color.WHITE;
                    if (Math.random() < RANDOM_FACTOR) {
                        c = Color.BLACK;
                        grid[j][i] = 1;
                    }

                    drawBox(i, j, c);
                }
            }

        screen.show();
        
        int v = 1;
        int[][] newGrid = new int[HEIGHT][WIDTH];
        while (v == 1) {
            for (int y = 0; y < HEIGHT; y++) {
                for (int x = 0; x < WIDTH; x++) {
                    Color color = Color.WHITE;
                    newGrid[y][x] = 0;
                    if (nextLife(grid[y][x], countNeighbors(x, y, grid)) == 1) {
                        color = Color.BLACK;
                        newGrid[y][x] = 1;
                    }
                    drawBox(x, y, color);
                }
            }
            for (int i = 0; i < newGrid.length; i++)
                grid[i] = newGrid[i].clone();

            screen.show();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private static void drawBox(int x, int y, Color c) {
        int i = x * SCALING_FACTOR;
        int j = y * SCALING_FACTOR;

        for (int a = 0; a < SCALING_FACTOR; a++) {
            for (int b = 0; b < SCALING_FACTOR; b++) {
                screen.set(i+a, j+b, c);
            }
        }
    }

    private static int countNeighbors(int x, int y, int[][] grid) {
        int numOfNeighbors = 0;
        for (int j = y-1; j <= y+1; j++) {
            if (j < 0 || j > HEIGHT-1) continue;
            for (int i = x-1; i <= x+1; i++) {
                if (i < 0 || i > WIDTH-1) continue;
                if (grid[j][i] == 1) numOfNeighbors++;
            }
        }
        if (grid[y][x] == 1) numOfNeighbors--;
        return numOfNeighbors;
    }

    private static int nextLife(int currentState, int numOfNeighbors) {
        if (currentState == 1) {
            if (numOfNeighbors > 3) return 0;
            else if (numOfNeighbors > 1) return 1;
            else return 0;
            }
        if (currentState == 0 && numOfNeighbors == 3) return 1;
        else return 0;
        }
}
