package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Klasse Minesweeper
 * @author Tolunay Arslan
 * Die Klasse Minesweeper beinhaltet die Game Logik und ist UI unabhängig.
 */
public class Minesweeper {

    private final int columns;
    private final  int rows;

    // The total amount of mines in the game
    private final int amountOfMines;

    // THe amount of cells in the playing field
    public final int cellAmount;

    ArrayList<int[]> mines = new ArrayList<int[]>();

    // contains all fields in the game
    public ArrayList<Field> allFields = new ArrayList<>();

// x = column, y = row
    public Minesweeper(int columns, int rows, int amountOfMines) {
        // Firstly, initialize the game.
        mines.clear();
        allFields.clear();

        this.columns = columns;
        this.rows = rows;
        this.amountOfMines = amountOfMines;
        this.cellAmount = columns * rows;

        // Secondly, get the position for the mines
        setThePositionsForTheMines();

        // Thirdly, prepare the playing field (set mines, etc.).
        prepareGrid();
    }

    private void setThePositionsForTheMines() {

        for(int index = 1; index <= amountOfMines; index++) {
            Random rand = new Random();

            int column = rand.nextInt((columns - 1) + 1) + 1;
            int row = rand.nextInt((rows - 1) + 1) + 1;

            System.out.println(column + " " + row);
            int[] minePair = new int[]{column, row};
            mines.add(minePair);
        }
    }

    // Checks whether the given field is a mine or not and returns accordingly the boolean value
    private Boolean isThisAMine(int positionColumn, int positionRow) {

        for(int index = 0; index < mines.size(); index++) {
            int column = mines.get(index)[0];
            int row = mines.get(index)[1];

            if(column == positionColumn && row == positionRow) {
                return true;
            }
        }
        return false;
    }

    // Prepares the playing field
    private void prepareGrid() {
        for(int row = 1; row <= rows; row++) {

            for(int column = 1; column <= columns; column++) {
                Field field;
                int[] id = new int[]{column, row};

                if(isThisAMine(column, row)){
                     field = new Field(true,column,row,id);
                } else {
                     field = new Field(false,column,row,id);
                }
                allFields.add(field);
            }
        }
    }

    public void checkSurroundingCells(int positionColumn, int positionRow) {
        int newPositionColumn;
        int newPositionRow;

        // TOP
        newPositionColumn = positionColumn -1;
        newPositionRow = positionRow -1;
        tapCellAndCheckIfItsAMine(newPositionColumn, newPositionRow);

        newPositionColumn = positionColumn;
        newPositionRow = positionRow -1;
        tapCellAndCheckIfItsAMine(newPositionColumn, newPositionRow);

        newPositionColumn = positionColumn +1;
        newPositionRow = positionRow -1;
        tapCellAndCheckIfItsAMine(newPositionColumn, newPositionRow);

        // CENTER
        newPositionColumn = positionColumn -1;
        newPositionRow = positionRow;
        tapCellAndCheckIfItsAMine(newPositionColumn, newPositionRow);

        newPositionColumn = positionColumn +1;
        newPositionRow = positionRow;
        tapCellAndCheckIfItsAMine(newPositionColumn, newPositionRow);

        // BOTTOM
        newPositionColumn = positionColumn -1;
        newPositionRow = positionRow +1;
        tapCellAndCheckIfItsAMine(newPositionColumn, newPositionRow);

        newPositionColumn = positionColumn;
        newPositionRow = positionRow +1;
        tapCellAndCheckIfItsAMine(newPositionColumn, newPositionRow);

        newPositionColumn = positionColumn +1;
        newPositionRow = positionRow +1;
        tapCellAndCheckIfItsAMine(newPositionColumn, newPositionRow);

    }

    /**
     * Chooses and checks whether the field is a mine and return true if it is a mine.
     */
    public Boolean tapCellAndCheckIfItsAMine(int positionColumn, int positionRow) {
        for(int index = 0; index < allFields.size(); index++) {
            // First get the right field
            if (allFields.get(index).positionColumn == positionColumn
                    && allFields.get(index).positionRow == positionRow) {

                allFields.get(index).destroy = true;
                return true;
                //if (isThisAMine(positionColumn, positionRow)) {
                    //allFields.get(index).destroy = true;
                  //  return true;
                //}
                //allFields.get(index).isTapped = true;
                //checkSurroundingCells(positionColumn, positionRow);

            }

        }

        return false;
    }

    // Marks or dismarks the given field
    void markField(int positionColumn, int positionRow) {
        for (Field field : allFields) {
            if (field.positionColumn == positionColumn
                    && field.positionRow == positionRow ) {

                if (field.isMarked) {
                    field.isMarked = false;
                } else {
                    field.isMarked = true;
                }
            }
        }
    }
}