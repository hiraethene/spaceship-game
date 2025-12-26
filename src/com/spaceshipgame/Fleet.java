package com.spaceshipgame;
import java.util.ArrayList;
import java.util.List;
/**
 * This fleet class represents a fleet in the spaceship game.
 * <p>
 *     A fleet is a collection of starships and starbases controlled by a single player.
 *     Fleets are used to move starships, engage in combat and return the list of starbases and starships in the fleet.
 * </p>
 *<p>
 *     A fleet can:
 *     <ul>
 *         <li>Remove starships from the fleet using {@link #removeStarship(Starship)} </li>
 *         <li>Remove starbases from the fleet using {@link #removeStarbase(Starbase)}</li>
 *         <li>Move all starships to a specific sector using {@link #mobiliseToSector(Sector)}.</li>
 *         <li>Attack a target starship in the same sector using {@link #attackTargetShip(Starship)}</li>
 *         <li>Attack a target starbase in the same sector using {@link #attackTargetBase(Starbase)}</li>
 *    </ul>
 * </p>
 *
 * @author Kelly
 * @version 26/12/2025
 */
public class Fleet {
    // Attributes
    private final String player;
    private final List<Starship> starships;
    private final List<Starbase> starbases;

    /**
     * Constructs a fleet with a specified player, number of starships, number of bases, and an initial sector
     * where the fleet is placed.
     * @param player the player controlling the fleet.
     * @param numShips the number of starships to create in the fleet.
     * @param numBases the number of starbases to create in the fleet.
     * @param currSector the initial sector for the fleet of starships and starbases.
     */
    public Fleet(String player, int numShips, int numBases, Sector currSector) {
        this.player = player;
        this.starships = new ArrayList<>();
        this.starbases = new ArrayList<>();
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

    // Getters
    public List<Starbase> getStarbases() {
        return starbases;
    }
    public List<Starship> getStarships() {
        return starships;
    }


    /** Removes a starbase from the fleet. */
    public void removeStarbase(Starbase base) {
        starbases.remove(base);
    }

    /** Removes a starship from the fleet. */
    public void removeStarship(Starship ship) {
        starships.remove(ship);
    }

    /**
     * Moves all starships in the fleet to a specified sector.
     *
     * @param targetSector the sector to move all non-docked ships into.
     */
    public void mobiliseToSector(Sector targetSector) {
        for (Starship ship : starships) {
            ship.moveToSector(targetSector);
        }
    }


    /**
     * Attacks a target starship in the same sector with all eligible starships in the fleet.
     * This method validates whether a ship can attack before calling the executeAttackShip method to deal the damage.
     *
     * @param targetShip the starship to attack.
     */
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

    /**
     * Attacks a target starbase in the same sector with all eligible starships in the fleet.
     * This method validates whether a ship can attack before calling the executeAttackBase method to deal the damage.
     *
     * @param targetBase the starbase to attack.
     */
    public void attackTargetBase(Starbase targetBase) {
        for (Starship ship : starships) {
            if (ship.getSector() == targetBase.getSector() &&
                    ship.getFleet() != targetBase.getFleet() &&
                    ship.shipCanAct()) {
                ship.executeAttackBase(targetBase);
            }
        }
    }
}


