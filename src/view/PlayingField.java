package view;

import javax.swing.*;
import java.awt.*;

public class PlayingField extends JFrame {

    public JMenuBar menuBar;
    public JMenu restartButton, exitButton, easyButton, hardButton;

    // The container of our field and sets the grid within the playingField
    public JPanel container;

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

        //There you can change the gamefield dimensions
        easyButton = new JMenu("Easy");
        menuBar.add(easyButton);

        //There you can change the gamefield dimensions
        hardButton = new JMenu("Hard");
        menuBar.add(hardButton);

        //Quit the game
        exitButton = new JMenu("Exit");
        menuBar.add(exitButton);

        setJMenuBar(menuBar);
    }


}
