package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Klasse Minesweeper
 * @author Tolunay Arslan
 * Die Klasse Minesweeper beinhaltet die Game Logik und ist UI unabhängig.
 */
public class Minesweeper {


    final private int columns = 20;
    final private int rows = 20;

    // The total amount of mines in the game
    final int amountOfMines = 8;


    //Map<Integer, Integer> mineIndexes = new HashMap<Integer, Integer>();

    // The column numbers of the mines.
    ArrayList<Integer>  columnValues = new ArrayList<>();

    // The row numbers of the mines.
    ArrayList<Integer>  rowValues = new ArrayList<>();

    // Beinhaltet alle Felder des Spielfelds
    ArrayList<Field> allFields = new ArrayList<>();

// x = column, y = row
    public Minesweeper() {
        // Firstly, initialize the game.
          //  mineIndexes.clear();
         //allFields.clear();

        // Secondly, get the position for the mines
        for(int index = 1; index <= amountOfMines; index++) {
            int column = (int)(Math.random() * columns);
            int row = (int)(Math.random() * rows);
            columnValues.add(column);
            rowValues.add(row);
            //mineIndexes.put(column,row);

        }

        // Thirdly, prepare the playing field (set mines, etc.).
        prepareGrid();
    }

    // Checks whether the fiven field is a mine or not and returns accordingly the boolean value
    private Boolean isThisAMine(int positionColumn, int positionRow) {

        if(columnValues.contains(positionColumn)) {
            int index = columnValues.indexOf(positionColumn);
            int y = rowValues.get(index);
            return positionRow == y;
        }
            return false;
    }

    // Prepares the playing field
    private void prepareGrid() {
        for(int column = 1; column <= columns; column++) {
            for(int row = 1; row <= rows; row++) {
                Field field;
                if(isThisAMine(column, row)){
                     field = new Field(true,column,row);
                } else {
                     field = new Field(false,column,row);
                }
                allFields.add(field);
            }
        }
    }

    /**
     * Chooses and checks whether the field is a mine and return true if it is a mine.
     */
    Boolean chooseAndCheckTheField(int positionColumn, int positionRow) {
        for (Field field : allFields) {
            // First get the right field
            if (field.positionColumn == positionColumn
                    && field.positionRow == positionRow) {
                field.isTapped = true;

                return isThisAMine(positionColumn, positionRow);

            }
        }

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