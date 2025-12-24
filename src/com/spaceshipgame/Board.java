package com.spaceshipgame;

public class Board {
    private int width;
    private int height;
    Sector[][] myBoard;

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
