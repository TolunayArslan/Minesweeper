package model;


public class Minesweeper {

    final int cols = 20;
    final int rows = 20;
    final int mines = 8;

    int[] minePositions = new int[8];

    public Minesweeper() {
        // Generate 8 random number for the mines
        int numberOfFields = cols * rows;

        for(int index = 0; index<= 7;index++) {
            minePositions[index] = (int)(Math.random() * numberOfFields) ;
            System.out.println(minePositions[index]);
        }
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public int getMines() {
        return mines;
    }
}