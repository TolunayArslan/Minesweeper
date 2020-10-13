package controller;

import model.Field;
import model.Minesweeper;
import view.*;

import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @author Tolunay Arslan
 * This class represents the controller of this program. It oberserves the users actions and respons to it accordingly.
 */
public class Controller implements ActionListener, MenuListener {

    // Model. Logic of the game.
    Minesweeper minesweeper;

    // The playing field.
    PlayingField playingField;

    // Array containing all cells in the game
    ArrayList<Cell> allCells = new ArrayList<Cell>();

    public Controller() {

        minesweeper = new Minesweeper(40,40, 8);
        playingField = new PlayingField(600,600);

        // Add all Cells
        for(Field field : minesweeper.allFields) {
            // Create the cell
            Cell cell = new Cell(field.isAMine, field.id, field.positionColumn, field.positionRow);

            // Add the cell the the array
            allCells.add(cell);

            // Assign the cell to the listener
            cell.addActionListener(this);

            // Add the cell to the JPanel
            playingField.container.add(cell);
        }

        playingField.pack();
        playingField.setVisible(true);
        playingField.setResizable(false);
        playingField.setSize(800,800);

        //ImageIcon gameIcon = new ImageIcon("//C:/Users/kai_r/eclipse-workspace/Minsweeper/src/Images/ic_main.png");
        //playingField.setIconImage(gameIcon.getImage());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // First get the right cell
        for(Cell cell : allCells) {
            if (cell == e.getSource()) {
                if (minesweeper.chooseAndCheckTheField(cell.positionColumn, cell.positionRow)) {
                    updateViewFromModel();
                } else {
                    System.out.print("Verloren, du looser.");
                }
            }
        }
    }

    public void updateViewFromModel(){

        int indices = allCells.size();
        for (int index = 0; index < indices; index++) {

            if (minesweeper.allFields.get(index).isTapped) {
                allCells.get(index).setVisible(false);
            } else {
                allCells.get(index).setBackground(Color.black);
            }

            if (minesweeper.allFields.get(index).isMarked) {
                allCells.get(index).setBackground(Color.yellow);
            } else {
                allCells.get(index).setBackground(Color.black);
            }
        }

    }


    public void viewCell(){

    }


    @Override
    public void menuSelected(MenuEvent e) {
        // Der findet die Menus nicht weil sie in einer anderenKlasse sind und dann funktioniert equals wohl nicht
        if(playingField.restartButton == e.getSource()) {
            System.out.print("HELLO WORLD");
        }
        if(e.getSource().equals(playingField.restartButton)) { // restart
            //Main.controller = new Controller();

        }
        if(e.getSource().equals(playingField.exitButton)) { // exit
            //System.exit(0);
        }
        if(e.getSource().equals(playingField.easyButton)) { // easy

        }
        if(e.getSource().equals(playingField.hardButton)) { // hard

        }
    }

    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }
}

