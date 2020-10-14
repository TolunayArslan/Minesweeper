package controller;

import model.Field;
import model.Minesweeper;
import view.*;

import javax.swing.*;
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

        // First initialize the model
        minesweeper = new Minesweeper(20,20, 8);

        // Second initialize the playing field
        playingField = new PlayingField(250,250);

        // Thirdly initialize all cells for the game
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

        // To some UI adjustments to the playing field view
        playingField.pack();
        playingField.setVisible(true);
        playingField.setResizable(false);
        playingField.setSize(600,600);
        ImageIcon gameIcon = new ImageIcon("../images/ic_main.png");
        playingField.setIconImage(gameIcon.getImage());

        // Last update the view (playing field)
        updateViewFromModel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // First get the right cell
        for(Cell cell : allCells) {
            if (cell == e.getSource()) {

                if (!minesweeper.tapCellAndCheckIfItsAMine(cell.positionColumn, cell.positionRow)) {
                    // Means it is not a mine
                    updateViewFromModel();
                } else {
                    // The tapped cell is a mine
                    updateViewFromModel();
                }
            }
        }
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        JOptionPane.showMessageDialog(null, "<your message here...>", "Alert", JOptionPane.ERROR_MESSAGE);
    }

    public void updateViewFromModel(){

        int indices = allCells.size();
        for (int index = 0; index < indices; index++) {

            if (minesweeper.allFields.get(index).destroy) {

                System.out.print("Hi");
                ImageIcon destroy = new ImageIcon("../images/Icon.png");
                allCells.get(index).setIcon(destroy);
                JOptionPane.showMessageDialog(null, "Lose. Please press restart to continue.", "Minesweeper", JOptionPane.ERROR_MESSAGE);

            }

            if (minesweeper.allFields.get(index).isTapped) {
                //ImageIcon pressedButton = new ImageIcon("//C:/Users/kai_r/eclipse-workspace/Minsweeper/src/Images/pressed_2.png");
                //field.setPressedIcon(pressedButton);
                allCells.get(index).setVisible(false);
            } else {

                //ImageIcon normal = new ImageIcon("C:\\Users\\i516464\\Desktop\\TolunayMinesweeper\\src\\view\\icons\\title_unpressed.png");
                //allCells.get(index).setIcon(normal);
            }

            if (minesweeper.allFields.get(index).isMarked) {
                ImageIcon flag = new ImageIcon("../images/icon6_2.png");
                allCells.get(index).setIcon(flag);
            } else {
                ImageIcon normal = new ImageIcon("../images/unpressed.png");
                allCells.get(index).setIcon(normal);
            }
        }

    }

    public void viewCell(){

    }


    @Override
    public void menuSelected(MenuEvent e) {

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

