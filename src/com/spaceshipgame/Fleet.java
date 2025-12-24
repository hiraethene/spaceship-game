package com.spaceshipgame;

import java.util.ArrayList;
import java.util.List;

public class Fleet {

    //Attributes ---------------------------------------------------------

    private String player;
    private List<Starship> starships;
    private List<Starbase> starbases;

    public Fleet(String player, int numShips, int numBases,Sector currSector) {
        this.player = player;
        this.starships = new ArrayList<Starship>();
        this.starbases = new ArrayList<Starbase>();
        for (int shipID = 0; shipID < numShips; shipID++) {
            System.out.println(shipID);
            Starship starship = new Starship(this,30.0f,10.0f,10,100.0f, currSector);
            starships.add(starship);
        }
        for (int numBaseID = 0; numBaseID < numBases; numBaseID++) {
            System.out.println(numBaseID);
            Starbase starbase = new Starbase(this,20.0f, 500.0f, currSector);
            starbases.add(starbase);
        }
    }

    //methods
    public void mobiliseToSector() {
        //Moves all ships in a fleet to a specific sector. When a fleet is mobilised, all
        //the ships within it move to the specified sector. Any ships currently docked in a
        //starbase do not get moved.

        //moves ships in a fleet to a specific sector
        //(selection?) when fleet is mobilised all ships within it move to the sector
        //if ship docked, not moved

    }

    public void attackTarget() {
        //All ships within a fleet attack a specified target. When a fleet is ordered to
        //attack, all ships within the fleet (that are also in the same sector as the
        //specified target and that are not docked in a starbase) will each attack the given
        //target.

        //all ships within a fleet attack a specified target
        //when fleet ordered to attack, all ships within the fleet
        //and condition? in the same sector as the target and not docked in a starbase will attack target
    }
}
