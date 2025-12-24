package com.spaceshipgame;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Long.sum;

public class Starbase {
    //Attributes ---------------------------------------------------------

    private float maxDefenceStrength;
    private float maxSBaseHealth;

    private float currentHealth;
    private float currentDefenceStrength;
    private Sector currentSector;
    private Fleet myFleet;
    private List<Starship> dockedStarships;

    //constructor that initialises the starbase with values for its attributes

    public Starbase(Fleet fleet, float maxDefStrength, float maxHealth, Sector currSector) {
        this.dockedStarships = new ArrayList<Starship>();
        myFleet = fleet;
        maxDefenceStrength = maxDefStrength;
        maxSBaseHealth = maxHealth;
        currentSector = currSector;
    }

    public Sector getSector() {
        return currentSector;
    }

    public Fleet getFleet() {
        return myFleet;
    }

    public float getCurrentHealth() {
        return currentHealth;
    }


    //CHECK IF SHIP IS DOCKED
    //CHECK IF STARBASE IS IN SAME SECTOR AS SHIP
    //CHECK IF STARBASE IS IN THE SAME FLEET
    public Boolean dockShip(Starship ship) {
        if (ship.isDocked() == Boolean.FALSE &&
                ship.getSector() == currentSector &&
                ship.getFleet() == myFleet) {
            dockedStarships.add(ship);
            return true;
        }
        return false;
    }

    public Boolean undockShip(Starship ship) {
        if (ship.isDocked() == Boolean.TRUE &&
                ship.getSector() == currentSector &&
                ship.getFleet() == myFleet) {
            dockedStarships.remove(ship);
            return false;
        }
        return true;
    }

    public void calcCurrDefStr() {
        float sumShipsDefStr = 0f;
        for (Starship ship : dockedStarships) {
            //ship.getCurrDefStrength();
            sumShipsDefStr += ship.getCurrDefStrength();
        }
        int numDockedShips = dockedStarships.size();

        float CurrDefStr = (float) Math.ceil((maxDefenceStrength * (currentHealth / maxSBaseHealth)) + (sumShipsDefStr * ((float) numDockedShips / maxDefenceStrength)));

    }

    public void checkCurrHealth(Starship ship) {
        if (currentHealth == 0) ;
        {

        }
    }
}
