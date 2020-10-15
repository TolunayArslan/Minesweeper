package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

/**
 * @author Tolunay Arslan and Kai Ringeis
 * This class represents the controller of this program. It oberserves the users actions and respons to it accordingly.
 */
public class Cell extends JButton{

    private static final int width = 50;
    private static final int height = 50;

    public Boolean isMarked;
    public Boolean isAMine;
    public Boolean isTapped = false;
    public int[] id;

    public int positionRow;
    public int positionColumn;

    public Boolean exploded = false;

    /**
     * Marks or dismarks the given field
     * @param isAMine zeigt auf ob es sich um eine Mine handelt.
     * @param id weist dem Feld einen eindeutigen Wert zu
     * @param positionColumn die Position der Spalten
     * @param positionRow zeigt die Position der Reihen auf
     */
    public Cell(Boolean isAMine, int[] id, int positionColumn, int positionRow) {

        this.isAMine = isAMine;
        this.setBackground(Color.DARK_GRAY);
        //ImageIcon unpressedButton = new ImageIcon("//C:/Users/kai_r/eclipse-workspace/Minsweeper/src/Images/title_unpressed.png");
        //field = new JButton(unpressedButton);
        //ImageIcon pressedButton = new ImageIcon("//C:/Users/kai_r/eclipse-workspace/Minsweeper/src/Images/pressed_2.png");
        //field.setPressedIcon(pressedButton);
        this.setSize(width, height);
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        try {
            Image unpressed = ImageIO.read(getClass().getResource("../images/title_unpressed.png"));
            this.setIcon(new ImageIcon(unpressed));

        } catch (Exception ex) {
            System.out.println(ex);
        }
        this.id = id;
        this.positionColumn = positionColumn;
        this.positionRow = positionRow;




    }

}
