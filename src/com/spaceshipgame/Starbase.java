package com.spaceshipgame;

public class Starbase {
    //Attributes ---------------------------------------------------------

    private int maxDefenceStrength;
    private int dockedShips;
    private int maxSBaseHealth;

    private int currentHealth;
    private int currentDefenceStrength;
    private Sector currentSector;



    //constructor that initialises the starbase with values for its attributes

    public Starbase(int maxDefStrength, int maxHealth, Sector currSector) {
        maxDefenceStrength = maxDefStrength;
        maxSBaseHealth = maxHealth;
        currentSector = currSector;
    }

}
