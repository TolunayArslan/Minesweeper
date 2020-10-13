package model;

public class Field {

    public Boolean isTapped = false;
    public Boolean isAMine;
    public Boolean isMarked = false;

    public int positionRow;
    public int positionColumn;
    public int[] id;

    public Field(Boolean isAMine, int positionColumn, int positionRow, int[] id) {
        this.isAMine = isAMine;
        this.positionRow = positionRow;
        this.positionColumn = positionColumn;

        this.id = id;
    }

}
