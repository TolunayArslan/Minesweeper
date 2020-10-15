package controller;

import model.Field;
import model.Minesweeper;
import view.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * @author Tolunay Arslan
 * This class represents the controller of this program. It oberserves the users actions and respons to it accordingly.
 */
public class Controller implements ActionListener, MenuListener, MouseListener {

    /**
     * Reference to the model
     */
    Minesweeper minesweeper;

    /**
     * Reference to the view
     */
    PlayingField playingField;

    /**
     * The {@link ArrayList} contains all {@link Cell}s
     */
    ArrayList<Cell> allCells = new ArrayList<Cell>();


    /**
     * after pressing restart the app needs to initialize the values and references.
     * */
    public void initializeGame() {
        //allCells.clear();
        //playingField.container.removeAll();
        minesweeper.mines.clear();
        minesweeper.setThePositionsForTheMines();

        for(int index = 0; index < allCells.size(); index++) {
            minesweeper.allFields.get(index).destroy = false;
            minesweeper.allFields.get(index).isTapped = false;
            minesweeper.allFields.get(index).isMarked = false;
            minesweeper.allFields.get(index).amountOfMinesSurrounded = 0;

            allCells.get(index).setIcon(null);
            allCells.get(index).setEnabled(true);
            allCells.get(index).setText("");
            allCells.get(index).isAMine = minesweeper.allFields.get(index).isAMine;
            try {
                Image unpressed = ImageIO.read(getClass().getResource("../images/title_unpressed.png"));
                allCells.get(index).setIcon(new ImageIcon(unpressed));

            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        updateViewFromModel();
        playingField.setVisible(true);
    }

    /**
     * Es werden die Anzahl der Minen, Reihen und Spalten definiert
     * Das Spielfeld wird dort erzeugt. Auf diesem finden die Interaktionen statt.
     * Die Zellen des Feldes werden hinzugefügt
     * Dem {@link javax.swing.JFrame} werden Eigenschaften zugewießen
     * Das Icon wird dem JFrame hinzugefügt
     * */
    public Controller() {

        minesweeper = new Minesweeper(20,20, 8);
        // First initialize the playing field
        playingField = new PlayingField(250,250);

        for(Field field : minesweeper.allFields) {
            // Create the cell
            Cell cell = new Cell(field.isAMine, field.id, field.positionColumn, field.positionRow);

            // Add the cell the the array
            allCells.add(cell);

            // Assign the cell to the listener
            cell.addActionListener(this);
            cell.addMouseListener(this);
            // Add the cell to the JPanel
            playingField.container.add(cell);
        }
        // Last update the view (playing field)
        updateViewFromModel();
        //Menu Bar
        playingField.restartButton.addMenuListener(this);

        // To some UI adjustments to the playing field view
        playingField.pack();

        playingField.setResizable(false);
        playingField.setSize(600,600);
        ImageIcon gameIcon = new ImageIcon("../images/ic_main.png");
        playingField.setIconImage(gameIcon.getImage());

        // Second initialize the model

        playingField.setVisible(true);

    }

    /**
     * @param e erfasst die Reaktion des Spielers
     * Wenn der Spieler ein flasches Feld aufdeckt kommt die Meldung in der Console, dass er das Match verloren hat
     * */
    @Override
    public void actionPerformed(ActionEvent e) {
        // First get the right cell
        for(Cell cell : allCells) {
            if (cell == e.getSource()) {
                minesweeper.tapCellAndCheckIfItsAMine(cell.positionColumn, cell.positionRow, true);
                updateViewFromModel();
            }
        }
    }


    /**
     * Updates the view according to the values of the model.
     */
    public void updateViewFromModel(){

        int indices = allCells.size();
        for (int index = 0; index < indices; index++) {

            //****************************************************
            // Did the user press on a mile?
            if (minesweeper.allFields.get(index).destroy) {
                try {
                    Image destroy = ImageIO.read(getClass().getResource("../images/Icon.png"));
                    allCells.get(index).setIcon(new ImageIcon(destroy));
                } catch (Exception ex) {
                    System.out.println(ex);
                }

                // Show sthe Pup up
                JOptionPane.showMessageDialog(null, "Please press restart to play again.",
                        "Lose", JOptionPane.ERROR_MESSAGE);
            }

            //****************************************************
            // Did the user press on a cell which is not a mine?
            if (minesweeper.allFields.get(index).isTapped) {
                allCells.get(index).setIcon(null);
                if(minesweeper.allFields.get(index).amountOfMinesSurrounded != 0) {
                    allCells.get(index).setText(String.valueOf(minesweeper.allFields.get(index).amountOfMinesSurrounded));
                }

                allCells.get(index).setEnabled(false);

            }

            //****************************************************
            // Did the user mark a field?
            if (minesweeper.allFields.get(index).isMarked) {
                try {
                    Image flag = ImageIO.read(getClass().getResource("../images/flag.png"));
                    allCells.get(index).setIcon(new ImageIcon(flag));
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            } else {
                // User dismarked a cell
                try {
                        Image unpressed = ImageIO.read(getClass().getResource("../images/unpressedCell.png"));
                        allCells.get(index).setIcon(new ImageIcon(unpressed));

                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
            }
        }

    /**
     * Receives the interaction of the user with the menu bar and fires of an action accordingly.
     * @param e hat the particular view which was clicked by the user.
     * */
    @Override
    public void menuSelected(MenuEvent e) {

        if(playingField.restartButton == e.getSource()) {
            Main.controller.initializeGame();
        }

    }

    /**
     * Receives the mouseclick of the user and does only change the state of isMarked when user right click.
     * */
    @Override
    public void mouseClicked(MouseEvent e) {
        // First get the right cell
        for(Cell cell : allCells) {
            if (cell == e.getSource()) {
                if (SwingUtilities.isRightMouseButton(e)) {

                    int index = minesweeper.getIndexOfField(cell.positionColumn, cell.positionRow);


                    if(minesweeper.allFields.get(index).isMarked) {
                        System.out.println("Seasdsada");
                        minesweeper.allFields.get(index).isMarked = false;
                    } else {
                        System.out.println("Seasdsada");
                        minesweeper.allFields.get(index).isMarked = true;
                    }
                    updateViewFromModel();
                }

            }
        }
    }

    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

