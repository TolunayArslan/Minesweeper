package model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Klasse Minesweeper
 * @author Tolunay Arslan
 * Die Klasse Minesweeper beinhaltet die Game Logik und ist UI unabhängig.
 */
public class Minesweeper {
    
    final private int columns;
    final private int rows;

    // The total amount of mines in the game
    final int amountOfMines;

    // THe amount of cells in the playing field
    final public int cellAmount;

    //Map<Integer, Integer> mineIndexes = new HashMap<Integer, Integer>();

    // The column numbers of the mines. First index here matches with the first index in rowValues
    ArrayList<Integer>  columnValues = new ArrayList<>();

    // The row numbers of the mines.
    ArrayList<Integer>  rowValues = new ArrayList<>();

    // contains all fields in the game
    public ArrayList<Field> allFields = new ArrayList<>();

// x = column, y = row
    public Minesweeper(int columns, int rows, int amountOfMines) {
        // Firstly, initialize the game.
          columnValues.clear();
          rowValues.clear();
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

            columnValues.add(column);
            rowValues.add(row);
        }
    }

    // Checks whether the given field is a mine or not and returns accordingly the boolean value
    private Boolean isThisAMine(int positionColumn, int positionRow, boolean activeGame) {
        if(columnValues.contains(positionColumn)) {

            int index = columnValues.indexOf(positionColumn);
            if (activeGame) {
                columnValues.remove(index);
                rowValues.remove(index);
            }

            //TODO: Wenn zweiter angeklickt wurde!
            return true;
        }
            return false;
    }

    // Prepares the playing field
    private void prepareGrid() {
        for(int column = 1; column <= columns; column++) {
            for(int row = 1; row <= rows; row++) {
                Field field;
                int[] id = new int[]{column, row};
                if(isThisAMine(column, row, false)){
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
        chooseAndCheckTheField(newPositionColumn, newPositionRow);

        newPositionColumn = positionColumn;
        newPositionRow = positionRow -1;
        chooseAndCheckTheField(newPositionColumn, newPositionRow);

        newPositionColumn = positionColumn +1;
        newPositionRow = positionRow -1;
        chooseAndCheckTheField(newPositionColumn, newPositionRow);

        // CENTER
        newPositionColumn = positionColumn -1;
        newPositionRow = positionRow;
        chooseAndCheckTheField(newPositionColumn, newPositionRow);

        newPositionColumn = positionColumn +1;
        newPositionRow = positionRow;
        chooseAndCheckTheField(newPositionColumn, newPositionRow);

        // BOTTOM
        newPositionColumn = positionColumn -1;
        newPositionRow = positionRow +1;
        chooseAndCheckTheField(newPositionColumn, newPositionRow);

        newPositionColumn = positionColumn;
        newPositionRow = positionRow +1;
        chooseAndCheckTheField(newPositionColumn, newPositionRow);

        newPositionColumn = positionColumn +1;
        newPositionRow = positionRow +1;
        chooseAndCheckTheField(newPositionColumn, newPositionRow);

    }

    /**
     * Chooses and checks whether the field is a mine and return true if it is a mine.
     */
    public Boolean chooseAndCheckTheField(int positionColumn, int positionRow) {
        for (Field field : allFields) {
            // First get the right field
            if (field.positionColumn == positionColumn
                    && field.positionRow == positionRow) {
                if (!field.isTapped) {
                    field.isTapped = true;
                    //checkSurroundingCells(positionColumn, positionRow);
                    return !isThisAMine(positionColumn, positionRow, true);
                }
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