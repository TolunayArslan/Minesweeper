package model;

import java.util.Objects;

/**
 * Class Minesweeper
 * @author Tolunay Arslan
 * The Field in respect of the model
 */
public class Field {

    /**
     * Tapped means left click
     */
    public Boolean isTapped = false;

    /**
     * Is a mine or not
     */
    public Boolean isAMine;

    /**
     * Marked means right click
     */
    public Boolean isMarked = false;

    /**
     * column and row is the index
     */
    public int[] id;

    public int positionRow;
    public int positionColumn;
    public Boolean destroy = false;
    public int amountOfMinesSurrounded = 0;

    @Override
    public int hashCode() {
        return Objects.hash(positionColumn, positionRow);
    }

    /**
     * Initializes a Field
     * @param isAMine zeigt auf ob es sich um eine Mine handelt.
     * @param positionColumn die Position der Spalten
     * @param positionRow zeigt die Position der Reihen auf
     * @param id weist dem Feld einen eindeutigen Wert zu
     */
    public Field(Boolean isAMine, int positionColumn, int positionRow, int[] id) {
        this.isAMine = isAMine;
        this.positionColumn = positionColumn;
        this.positionRow = positionRow;
        this.id = id;

    }



}
