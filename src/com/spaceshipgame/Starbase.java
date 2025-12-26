package com.spaceshipgame;
import java.util.ArrayList;
import java.util.List;

/**
 * This starbase class represents a starbase object in the spaceship game.
 * <p>
 *     Each starbase has a maximum defence strength, a fleet,maximum health,
 *     sector, current health and current defence strength.
 * </p>
 *<p>
 *     A starbase can:
 *     <ul>
 *         <li>Dock and undock starships that are in the same fleet and sector 
 *             using {@link #dockShip(Starship)} and {@link #undockShip(Starship)} .</li>
 *         <li>Calculate its current defence strength using {@link #calcCurrDefStr()} </li>
 *         <li>Check its current health using {@link #checkCurrHealth()}</li>
 *         <li>Take damage using {@link #takeDamage(float)}</li>
 *    </ul>
 * </p>
 *<p>
 *     If a starbase's health reaches 0, the starbase and starships docked at the starbase are disabled.
 *     The defensive strength of a starbase is increased when ships are docked with it.
 *</p>
 *
 * @author Kelly
 * @version 26/12/2025
 */

public class Starbase {
    // Attributes
    private final float maxDefenceStrength;
    private final float maxSBaseHealth;

    private float currentHealth;
    private float currentDefenceStrength;
    private final Sector currentSector;
    private final Fleet myFleet;
    private final List<Starship> dockedStarships;
    private boolean disabled = false;

    /**
     * <p>
     * This is a constructor that initializes the starbase object with specific parameters for the fleet,
     * max defence strength, max health and the current sector.
     * </p>
     *
     * @param fleet              the fleet the starbase is a part of.
     * @param maxDefenceStrength the maximum amount of damage a starbase could negate when it's attacked.
     * @param maxSBaseHealth     the maximum health of the starbase.
     * @param currentSector      the sector the starbase is currently located in.
     */
    public Starbase(Fleet fleet, float maxDefenceStrength, float maxSBaseHealth, Sector currentSector) {
        this.dockedStarships = new ArrayList<>();
        this.myFleet = fleet;
        this.maxDefenceStrength = maxDefenceStrength;
        this.maxSBaseHealth = maxSBaseHealth;
        this.currentSector = currentSector;
        this.currentHealth = maxSBaseHealth;
        this.currentDefenceStrength = maxDefenceStrength;
        calcCurrDefStr();
    }

    // Getters
    public Sector getSector() {
        return currentSector;
    }
    public Fleet getFleet() {
        return myFleet;
    }
    public float getCurrentHealth() {
        return currentHealth;
    }
    public float getCurrentDefenceStrength() {
        return currentDefenceStrength;
    }

    /**
     * Docks a starship into the starbase if it's not already docked, in the same sector and in the same fleet.
     * @param ship the ship to be docked.
     * @return returns a true or false Boolean value that indicates whether the ship's docked or not.
     */
    public Boolean dockShip(Starship ship) {
        if (!ship.isDocked() &&
                ship.getSector().equals(currentSector) &&
                ship.getFleet().equals(myFleet)){
            dockedStarships.add(ship);
            calcCurrDefStr(); // Update defence
            return true;
        }
        return false;
    }

    /**
     * Undocks a starship from the starbase if it's docked, in the same sector and in the same fleet.
     * @param ship the ship to be undocked.
     * @return returns a true or false Boolean value that indicates whether the ship's docked or not.
     */
    public Boolean undockShip(Starship ship) {
        if (ship.isDocked() &&
                ship.getSector().equals(currentSector) &&
                ship.getFleet().equals(myFleet)) {
            dockedStarships.remove(ship);
            calcCurrDefStr();// Update defence
            return false;
        }
        return true;
    }

    /**
     * Calculates the starbase's current defence strength using the following formula:
     * Current Defence Strength = ⌊Max Defence Strength × (Current Health / Max Health )
     * + ((∑Docked ships current defence strength) × Number of docked ships / Max Defence Strength)⌋
     *
     */
    public void calcCurrDefStr() {
        float sumShipsDefStr = 0f;
        for (Starship ship : dockedStarships) {
            sumShipsDefStr += ship.getCurrDefStrength();
        }
        int numDockedShips = dockedStarships.size();

        this.currentDefenceStrength = (float) Math.ceil((maxDefenceStrength * (currentHealth / maxSBaseHealth)) +
                (sumShipsDefStr * ((float) numDockedShips / maxDefenceStrength)));
    }

    /**
     * Reduces the starbase's health by a specified amount and checks if the base should be disabled.
     * @param damage the damage dealt to the starbase
     */
    public void takeDamage(float damage) {
        currentHealth -= damage;
        calcCurrDefStr(); // Update defence after taking damage
        checkCurrHealth();
    }

    /**
     * Checks the current health of the starbase.
     * <p>
     *     If health is <= 0, the starbase and all docked ships at the starbase are disabled.
     *     The starships and starbase will be removed from the fleet.
     * </p>
     */
    public void checkCurrHealth() {
        if (!disabled && currentHealth <= 0) {
            disabled = true;

            // Docked starships in the starbase are disabled and removed from play
            for (Starship starship : dockedStarships) {
                starship.disableShip();
            }
            dockedStarships.clear();

            // Starbase is disabled and removed from play
            // Removes the starbase from the fleet
            myFleet.removeStarbase(this);
        }
    }
    /**
     *  Disables the starbase by removing it from its fleet.
     */
    public void disableBase() {
        myFleet.removeStarbase(this);
    }
}
