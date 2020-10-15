package model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class Minesweeper
 * @author Tolunay Arslan
 * Minesweeper contains the game logic and is independent.
 */
public class Minesweeper {

    /**
     * Value for the amount of columns and rows of the playing field.
     */
    private final int columns;
    private final  int rows;

    /**
     * The total amount of mines in the game.
     */
    private final int amountOfMines;

    /**
     * The amount of cells in the playing field
     */
    public final int cellAmount;

    /**
     * Contains the indices of the mines.
     */
    public ArrayList<int[]> mines = new ArrayList<int[]>();

    /**
     * {@link ArrayList} contains all {@link Field}s.
     */
    public ArrayList<Field> allFields = new ArrayList<>();

    /**
     * Das Spiel wird initialisiert, die Minen werden gesetzt und das Spielfeld wird erstellt
     * @param columns gibt die anzahl der Spalten an
     * @param rows gibt die anzahl der Reihen an
     * @param amountOfMines anzahl der Minen
     * */
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

    /**
     * Calculates the position of the mines.
     * */
    public void setThePositionsForTheMines() {

        for(int index = 1; index <= amountOfMines; index++) {
            Random rand = new Random();

            int column = rand.nextInt((columns - 1) + 1) + 1;
            int row = rand.nextInt((rows - 1) + 1) + 1;

            int[] minePair = new int[]{column, row};
            mines.add(minePair);
        }
    }


    /**
     * Checks whether the given field is a mine or not and returns accordingly the boolean value.
     * @param positionColumn Position of the column in the playing field.
     * @param positionRow Position of the row in the playing field.
     * @return if the cell is a mine, return true otherwise false.
     * */
    public Boolean isThisAMine(int positionColumn, int positionRow) {

        for(int index = 0; index < mines.size(); index++) {
            int column = mines.get(index)[0];
            int row = mines.get(index)[1];

            if(column == positionColumn && row == positionRow) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method creates the layout and the fields.
     * */
    public void prepareGrid() {
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
    /**
     * Gets the index of the given cell in the allFields array.
     * @param positionColumn position of the column.
     * @param positionRow position of the row.
     * @return The index of the given field in allFields array.
     */
    public int getIndexOfField(int positionColumn, int positionRow) {
        for(int index = 0; index < allFields.size(); index++) {

            if (allFields.get(index).positionColumn == positionColumn
                    && allFields.get(index).positionRow == positionRow) {
                return index;
            }
        }
        System.out.println("No index found for: " + positionColumn + " " + positionRow);
        return 0;
    }

    private void checkSurroundingCells(int positionColumn, int positionRow) {
        int surroundedPositionColumn = 0;
        int surroundedPositionRow = 0;
        int amountOfMinesSurrounded = 0;
        int index = 0;

        ArrayList<Integer> surroundedFieldsIndices = new ArrayList<>();

        // TOP
        surroundedPositionColumn = positionColumn -1;
        surroundedPositionRow = positionRow -1;
        if (surroundedPositionColumn >= 1 && surroundedPositionRow >= 1 && surroundedPositionColumn <= 20 && surroundedPositionRow <= 20) {
            // since we don't have any position lower than 1
            index = getIndexOfField(surroundedPositionColumn, surroundedPositionRow);
            System.out.println(surroundedPositionColumn + " " + surroundedPositionColumn + "\n");
            surroundedFieldsIndices.add(index);
            if (isThisAMine(surroundedPositionColumn, surroundedPositionRow)) amountOfMinesSurrounded += 1;
        }

        surroundedPositionColumn = positionColumn;
        surroundedPositionRow = positionRow -1;
        if (surroundedPositionColumn >= 1 && surroundedPositionRow >= 1 && surroundedPositionColumn <= 20 && surroundedPositionRow <= 20) {
            // since we don't have any position lower than 1
            index = getIndexOfField(surroundedPositionColumn, surroundedPositionRow);
            System.out.println(surroundedPositionColumn + " " + surroundedPositionColumn + "\n");
            surroundedFieldsIndices.add(index);
            if (isThisAMine(surroundedPositionColumn, surroundedPositionRow)) amountOfMinesSurrounded += 1;
        }

        surroundedPositionColumn = positionColumn +1;
        surroundedPositionRow = positionRow -1;
        if (surroundedPositionColumn >= 1 && surroundedPositionRow >= 1 && surroundedPositionColumn <= 20 && surroundedPositionRow <= 20) {
            // since we don't have any position lower than 1
            index = getIndexOfField(surroundedPositionColumn, surroundedPositionRow);
            System.out.println(surroundedPositionColumn + " " + surroundedPositionColumn + "\n");
            surroundedFieldsIndices.add(index);
            if (isThisAMine(surroundedPositionColumn, surroundedPositionRow)) amountOfMinesSurrounded += 1;
        }

        // CENTER
        surroundedPositionColumn = positionColumn -1;
        surroundedPositionRow = positionRow;
        if (surroundedPositionColumn >= 1 && surroundedPositionRow >= 1 && surroundedPositionColumn <= 20 && surroundedPositionRow <= 20) {
            // since we don't have any position lower than 1
            index = getIndexOfField(surroundedPositionColumn, surroundedPositionRow);
            surroundedFieldsIndices.add(index);
            if (isThisAMine(surroundedPositionColumn, surroundedPositionRow)) amountOfMinesSurrounded += 1;
        }

        surroundedPositionColumn = positionColumn +1;
        surroundedPositionRow = positionRow;
        if (surroundedPositionColumn >= 1 && surroundedPositionRow >= 1 && surroundedPositionColumn <= 20 && surroundedPositionRow <= 20) {
            // since we don't have any position lower than 1
            index = getIndexOfField(surroundedPositionColumn, surroundedPositionRow);
            surroundedFieldsIndices.add(index);
            if (isThisAMine(surroundedPositionColumn, surroundedPositionRow)) amountOfMinesSurrounded += 1;
        }

        // BOTTOM
        surroundedPositionColumn = positionColumn -1;
        surroundedPositionRow = positionRow +1;
        if (surroundedPositionColumn >= 1 && surroundedPositionRow >= 1 && surroundedPositionColumn <= 20 && surroundedPositionRow <= 20) {
            // since we don't have any position lower than 1
            index = getIndexOfField(surroundedPositionColumn, surroundedPositionRow);
            surroundedFieldsIndices.add(index);
            if (isThisAMine(surroundedPositionColumn, surroundedPositionRow)) amountOfMinesSurrounded += 1;
        }

        surroundedPositionColumn = positionColumn;
        surroundedPositionRow = positionRow +1;
        if (surroundedPositionColumn >= 1 && surroundedPositionRow >= 1 && surroundedPositionColumn <= 20 && surroundedPositionRow <= 20) {
            // since we don't have any position lower than 1
            index = getIndexOfField(surroundedPositionColumn, surroundedPositionRow);
            surroundedFieldsIndices.add(index);
            if (isThisAMine(surroundedPositionColumn, surroundedPositionRow)) amountOfMinesSurrounded += 1;
        }

        surroundedPositionColumn = positionColumn +1;
        surroundedPositionRow = positionRow +1;
        if (surroundedPositionColumn >= 1 && surroundedPositionRow >= 1 && surroundedPositionColumn <= 20 && surroundedPositionRow <= 20) {
            // since we don't have any position lower than 1
            index = getIndexOfField(surroundedPositionColumn, surroundedPositionRow);
            surroundedFieldsIndices.add(index);
            if (isThisAMine(surroundedPositionColumn, surroundedPositionRow)) amountOfMinesSurrounded += 1;
        }

        if(amountOfMinesSurrounded > 1) {
            // Just show the number. Dont open anyone since there are more than one mine
            if (positionColumn >= 1 && positionRow >= 1 && positionColumn <= 20 && positionRow <= 20) {
                int x = getIndexOfField(positionColumn, positionRow);
                allFields.get(x).amountOfMinesSurrounded = amountOfMinesSurrounded;
            }

            return;
        } else if(amountOfMinesSurrounded == 0) {
            // there are no mines
            // surroundedFieldsIndices hier stecken die indexe
            for(int i = 0; i < surroundedFieldsIndices.size(); i++) {
                    allFields.get(surroundedFieldsIndices.get(i)).isTapped = true;
                    tapCellAndCheckIfItsAMine(allFields.get(surroundedFieldsIndices.get(i)).positionColumn,
                            allFields.get(surroundedFieldsIndices.get(i)).positionRow, false);
            }
            return;
        } else {
            // only one mine in the surrounding area therefore do nothing
            return;
        }

    }


    /**
     * Chooses and checks whether the field is a mine and return true if it is a mine.
     * @param positionColumn position of the column.
     * @param positionRow position of the row.
     * @return Return true if the cell is a mine.
     */
    public Boolean tapCellAndCheckIfItsAMine(int positionColumn, int positionRow, boolean activeGame) {
        // First get the right index in allFields
        if (positionColumn >= 1 && positionRow >= 1 && positionColumn <= 20 && positionRow <= 20) {
            int index = getIndexOfField(positionColumn, positionRow);
            if (isThisAMine(positionColumn, positionRow)) {
                // Ths user tapped a mine
                // Should we end the game?
                if(activeGame) {
                    allFields.get(index).destroy = true;
                }
                return true;

            } else {
                // Field is not a mine
                allFields.get(index).isTapped = true;
                //checkSurroundingCells(positionColumn, positionRow);
            }

            return false;
        }
        System.out.println("wieder position!");
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