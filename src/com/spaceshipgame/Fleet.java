package com.spaceshipgame;

import java.util.ArrayList;
import java.util.List;

public class Fleet {

    //Attributes ---------------------------------------------------------

    private String player;
    private List<Starship> starships;
    private List<Starbase> starbases;

    public Fleet(String player, int numShips, int numBases, Sector currSector) {
        this.player = player;
        this.starships = new ArrayList<Starship>();
        this.starbases = new ArrayList<Starbase>();
        for (int shipID = 0; shipID < numShips; shipID++) {
            System.out.println(shipID);
            Starship starship = new Starship(this, 30.0f, 10.0f, 10, 100.0f, currSector);
            starships.add(starship);
        }
        for (int numBaseID = 0; numBaseID < numBases; numBaseID++) {
            System.out.println(numBaseID);
            Starbase starbase = new Starbase(this, 20.0f, 500.0f, currSector);
            starbases.add(starbase);
        }
    }

    public void removeStarbase(Starbase base) {
        starbases.remove(base);
    }

    public void removeStarship(Starship ship) {
        starships.remove(ship);
    }

    //methods
    //Moves all ships in a fleet to a specific sector. When a fleet is mobilised, all
    //the ships within it move to the specified sector. Any ships currently docked in a
    //starbase do not get moved.

    //moves ships in a fleet to a specific sector
    //(selection?) when fleet is mobilised all ships within it move to the sector
    //if ship docked, not moved
    public void mobiliseToSector(Sector targetSector) {
        for (Starship ship : starships) {
            ship.moveToSector(targetSector);
        }
    }

    public void attackTargetShip(Starship targetShip) {
        for (Starship ship : starships) {
            if (!ship.isDocked() &&
                    !targetShip.isDocked() &&
                    ship.getSector() == targetShip.getSector() &&
                    ship.getFleet() != targetShip.getFleet() &&
                    ship.shipCanAct()) {

                ship.executeAttackShip(targetShip);
            }

        }

    }

    public void attackTargetBase(Starbase targetBase) {
        for (Starship ship : starships) {
            if (ship.getSector() == targetBase.getSector() &&
                    ship.getFleet() != targetBase.getFleet() &&
                    ship.shipCanAct()) {
                ship.executeAttackBase(targetBase);
            }
        }
    }

    public List<Starbase> getStarbases() {
        return starbases;
    }

    public List<Starship> getStarships() {
        return starships;
    }

}


