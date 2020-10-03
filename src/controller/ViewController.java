package controller;

import model.Minesweeper;

/**
 * @author Tolunay Arslan
 * This class represents the controller of this program. It oberserves the users actions and respons to it accordingly.
 */
public class ViewController  {

    private Minesweeper minesweeper;
    // Array of all the fields


    public ViewController(Minesweeper minesweeper) {

        this.minesweeper = minesweeper;
    }
}
