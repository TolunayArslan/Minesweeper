package model;

public class Field {

    Boolean isTapped = false;
    Boolean mine;
    Boolean isMarked = false;
    int positionRow;
    int positionColumn;


    public Field(Boolean mine, int positionColumn, int positionRow) {
        this.mine = mine;
        this.positionRow = positionRow;
        this.positionColumn = positionColumn;
    }

}
