package com.spaceshipgame;
/**
 * This sector class represents a sector on the game board in the spaceship game.
 * <p>
 *     Each sector has a unique name and a position on the board defined by a row and column.
 *     Sectors are used to place fleets, starships and starbases and are used to track movements and combat
 *     between players.
 * </p>
 *<p>
 *
 * @author Kelly
 * @version 26/12/2025
 */
public class Sector {
    // Attributes
    private final String sectorName;
    private final int row;
    private final int column;

    /**
     * Constructs a sector with a specified name, row and column.
     * @param sectorName the name of the sector.
     * @param row        the row position of the sector on the game board.
     * @param column     the column position of the sector on the game board.
     */
    public Sector(String sectorName, int row, int column) {
        this.sectorName = sectorName;
        this.row = row;
        this.column = column;

    }

    // Getters
    public String getName() {

        return sectorName;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

}
