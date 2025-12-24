package com.spaceshipgame;

public class Sector {
    private String sectorName;
    private int row;
    private int column;

    public Sector(String sectorName, int row, int column) {
        this.sectorName = sectorName;
        this.row = row;
        this.column = column;

    }

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
