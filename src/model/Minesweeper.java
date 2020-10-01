package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Minesweeper {

    final private int columns = 20;
    final private int rows = 20;
    final int amountOfMines = 8;

    //TODO: Key kann nur einmal vorkommen
    //Map<Integer, Integer> mineIndexes = new HashMap<Integer, Integer>();

    ArrayList<Integer>  columnValues = new ArrayList<>();
    ArrayList<Integer>  rowValues = new ArrayList<>();

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

        // Thirdly, prepare the playground (set mines, etc.).
        prepareGrid();
    }

    Boolean isThisAMine(int positionColumn, int positionRow) {

        if(columnValues.contains(positionColumn)) {
            int index = columnValues.indexOf(positionColumn);
            int y = rowValues.get(index);
            return positionRow == y;
        }
            return false;
    }

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

    Boolean chooseAField(int positionColumn, int positionRow) {
        for (Field field : allFields) {
            if (field.positionColumn == positionColumn
                    && field.positionRow == positionRow) {
                field.isTapped = true;
                // TODO: Implementing the logic after clicking on a field
            }
        }
        return false;
    }

    // marks and dis marks a field
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