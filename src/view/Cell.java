package view;

import javax.swing.*;
import java.awt.*;

/**
 * @author Tolunay Arslan and Kai Ringeis
 * This class represents the controller of this program. It oberserves the users actions and respons to it accordingly.
 */
public class Cell extends JButton{

    private static final int width = 50;
    private static final int height = 50;

    public Boolean isMarked = false;
    public Boolean isAMine;
    public Boolean isTapped = false;
    public int[] id;

    public int positionRow;
    public int positionColumn;

    public Boolean exploded = false;

    public Cell(Boolean isAMine, int[] id, int positionColumn, int positionRow) {

        this.isAMine = isAMine;
        this.setBackground(Color.DARK_GRAY);
        //ImageIcon unpressedButton = new ImageIcon("//C:/Users/kai_r/eclipse-workspace/Minsweeper/src/Images/tile_unpressed.png");
        //field = new JButton(unpressedButton);
        //ImageIcon pressedButton = new ImageIcon("//C:/Users/kai_r/eclipse-workspace/Minsweeper/src/Images/pressed_2.png");
        //field.setPressedIcon(pressedButton);
        this.setSize(width, height);
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        this.id = id;
        this.positionColumn = positionColumn;
        this.positionRow = positionRow;

    }
}
