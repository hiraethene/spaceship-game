package com.spaceshipgame;
/**
 * This board class represents the game board in the spaceship game.
 * <p>
 *     The board consists of a grid of sectors, each represented by a sector object.
 *     The width and height of the board define the number of columns and rows of sectors.
 * </p>
 *<p>
 *     The board provides functionality to:
 *     <ul>
 *         <li>Initialise a grid of sectors with unique IDS.</li>
 *         <li>Retrieve a sector by its name.</li>
 *    </ul>
 * </p>
 *<p>
 *    Each sector can contain starships and starbases, and is used to manage movement, attacks, docking and other
 *    potential game mechanics based on sector location.
 *</p>
 *
 * @author Kelly
 * @version 26/12/2025
 */
public class Board {
    // Attributes
    private final int width;
    private final int height;
    Sector[][] myBoard;

    /**
     * This is a constructor that initialises the game board with the specified width and height.
     * It initialises each sector in the grid with a unique ID and name.
     *
     * @param width the number of columns of sectors.
     * @param height the number of rows of sectors.
     */
    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        myBoard = new Sector[height][width];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width ; col++) {
                //System.out.println("Sector[" + row + "][" + col + "] = " + Sector[row][col]);
                int sectorID = row * width + col + 1;
                String sectorName = "sector" + sectorID;

                Sector sector = new Sector(sectorName, row,col);
                myBoard[row][col] = sector;
            }
        }
    }

    /**
     * Retrieves a sector by its name.
     *
     * @param sectorName the name of the sector to retrieve.
     * @return the sector object with the specified name.
     * @throws IllegalArgumentException if the sector is invalid or not found.
     */
    public Sector getSector(String sectorName) {

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (sectorName.equals(myBoard[row][col].getName())) {
                    return myBoard[row][col];
                }
            }
        }

        throw new IllegalArgumentException("Invalid sector");
    }
}
