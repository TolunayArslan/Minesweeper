package model;

public class Field {

    // Tapped means left click
    public Boolean isTapped = false;

    public Boolean isAMine;

    // Marked means right click
    public Boolean isMarked = false;

    public int positionRow;
    public int positionColumn;
    public Boolean destroy = false;

    // column and row is the index
    public int[] id;

    public Field(Boolean isAMine, int positionColumn, int positionRow, int[] id) {
        this.isAMine = isAMine;
        this.positionColumn = positionColumn;
        this.positionRow = positionRow;


        this.id = id;

    }



}
