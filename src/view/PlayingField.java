package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @author Tolunay Arslan and Kai Ringeis
 * This class represents the controller of this program. It oberserves the users actions and respons to it accordingly.
 */
public class PlayingField extends JFrame {
    /**
     * {@link JMenuBar} contains {@link JMenu}
     */
    public JMenuBar menuBar;

    /**
     * Das {@link JMenu} contains the restart button
     */
    public JMenu restartButton;

    // The container of our field and sets the grid within the playingField
    /**
     * {@link JPanel} is the container of the playing field. It has all the cells as children and specifies the grid.
     */
    public JPanel container;

    /** In dieser Methode wird das JPanel {@link #container} erstellt. In diesem befinden sich dementsprechend die {@link Cell}s
     * 2. Befindet sich in dieser Methode die {@link #menuBar} welche oberhalb des JFrames plaziert ist
     * @param width gibt die Breite des Spielfeldes
     * @param height gibt die Höhe des Spielfeldes
     */
    public PlayingField(int width, int height) {

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Minesweeper");

        container = new JPanel();
        container.setLayout(new GridLayout(20,20,1,1));
        container.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20), null));
        this.add(container);

        //MENU BAR
        menuBar = new JMenuBar();

        restartButton = new JMenu("Restart");
        menuBar.add(restartButton);
        setJMenuBar(menuBar);
    }
}
