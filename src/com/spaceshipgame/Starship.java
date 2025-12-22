package com.spaceshipgame;

public class Starship {
    //Attributes ---------------------------------------------------------

    private int maxAttackStrength;
    private int maxDefenceStrength;
    private int maxCrewMembers;
    private int maxShipHealth;

    private int currentHealth;
    private int currentCrew;
    private int currentAttackStrength;
    private int currentDefenceStrength;

    private Sector currentSector;
 //   private Starbase dockedStarbase;

    //empty constructor
    public Starship() {

    }

    //constructor that initialises the starship with values for its attributes
    //add sector later when it's developed
    public Starship(int maxAttStrength, int maxDefStrength, int maxCrew, int maxHealth, Sector currSector) {
        maxAttackStrength = maxAttStrength;
        maxDefenceStrength = maxDefStrength;
        maxCrewMembers = maxCrew;
        maxShipHealth = maxHealth;
        currentSector = currSector;
    }


    //methods
    //TODO
    //Move to a Sector
    //Dock with a starbase
    //undock from a starbase
    //repair
    //attack a target

}


