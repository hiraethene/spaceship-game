package com.spaceshipgame;
/**
 * This starship class represents a starship object in the spaceship game.
 * <p>
 *     Each starship has maximum attack strength, maximum defence strength, maximum crew, maximum health,
 *     current health, current crew, current attack strength, current defence strength, sector, fleet, and a starbase if it's docked.
 * </p>
 *<p>
 *     A starship can:
 *     <ul>
 *         <li>Move to different sectors unless it's docked using {@link #moveToSector(Sector)}.</li>
 *         <li>Dock and undock from a starbase in the same fleet
 *         using {@link #dock(Starbase)}  and {@link #undock(Starbase)}. </li>
 *         <li>Repair while it's docked at a starbase using {@link #repairShip()}.
 *             Repairing restores its health and crew, skipping actions depending on how low the current health is.</li>
 *         <li>Attack starships in the same sector using {@link #executeAttackShip(Starship)}. </li>
 *         <li>Attack starbases in the same sector using{@link #executeAttackBase(Starbase)}.</li>
 *    </ul>
 * </p>
 *<p>
 *     The starship's current attack and defence strength are calculated based on its current health and crew
 *     compared to their respective maximum values. If a starship's health reaches 0, it is disabled.
 *</p>
 *
 * @author Kelly
 * @version 26/12/2025
 */
public class Starship {
    // Attributes
    private final float maxAttackStrength;
    private final float maxDefenceStrength;
    private final int maxCrew;
    private final float maxShipHealth;
    private boolean shipDocked;
    private float currentHealth;
    private int currentCrew;
    private float currentAttackStrength;
    private float currentDefenceStrength;
    private final Fleet myFleet;
    private Sector currentSector;
    private int actionsToSkip;
    private boolean disabled = false;

    /**
     * <p>
     * This is a constructor that initializes the starship object with specific parameters for the fleet,
     * max attack strength, max defence strength, max crew, max health and the current sector.
     * </p>
     *
     * @param fleet              the fleet the starship's a part of.
     * @param maxAttackStrength  the maximum amount of damage a ship could deal to a target when it attacks.
     * @param maxDefenceStrength the maximum amount of damage a ship could negate when it's attacked.
     * @param maxCrew            the maximum crew count a ship can hold.
     * @param maxShipHealth      the maximum health of the ship.
     * @param currentSector      the sector the ship is currently located in.
     */
    public Starship(Fleet fleet, float maxAttackStrength, float maxDefenceStrength, int maxCrew, float maxShipHealth, Sector currentSector) {
        this.myFleet = fleet;
        this.maxAttackStrength = maxAttackStrength;
        this.maxDefenceStrength = maxDefenceStrength;
        this.maxCrew = maxCrew;
        this.maxShipHealth = maxShipHealth;
        this.currentSector = currentSector;
        this.currentHealth = maxShipHealth;
        this.currentCrew = maxCrew;
        this.calcCurrAttStr();
        this.calcCurrDefStr();
    }

    // Getters
    public Sector getSector() {
        return currentSector;
    }
    public Fleet getFleet() {
        return myFleet;
    }
    public float getCurrDefStrength() {
        return currentDefenceStrength;
    }
    public float getCurrAttStrength() {return currentAttackStrength;}
    public float getCurrentHealth() {
        return currentHealth;
    }
    public int getCurrentCrew() {
        return currentCrew;
    }
    public int getActionsToSkip() {return actionsToSkip;}
    public boolean isDocked() {
        return shipDocked;
    }
    public boolean isDisabled() {return disabled;}

    /**
     * Indicates if a ship can act depending on the number of actions it has to skip.
     *
     * @return a true or false value.
     */
    public boolean shipCanAct() {
        return actionsToSkip == 0;
    }

    public void calcCurrDefStr() {
        currentDefenceStrength = (float) Math.ceil((maxDefenceStrength * ((currentHealth + currentCrew) / (maxShipHealth + maxCrew))));
    }

    public void calcCurrAttStr() {
        currentAttackStrength = (float) Math.ceil((maxAttackStrength * ((currentHealth / maxShipHealth))));

    }

    /**
     * The ship can move into another sector if it isn't docked.
     *
     * @param newSector the sector the ship is moving into.
     */
    public void moveToSector(Sector newSector) {
        if (!this.isDocked()) {
            currentSector = newSector;
        }
    }

    /**
     *  Docks the ship with a starbase by calling the dockShip method in starbase.
     *
     * @param targetBase the base the ship wants to dock with.
     */
    public void dock(Starbase targetBase) {
        shipDocked = targetBase.dockShip(this);
    }


    /**
     *  Undocks a docked ship from a starbase by calling the undockShip method in starbase.
     *
     * @param targetBase the base the ship is undocking from.
     */
    public void undock(Starbase targetBase) {
        shipDocked = targetBase.undockShip(this);
    }


    /**
     * Repairs a starship docked at a starbase.
     *
     * <p>
     *     When a starship is repaired, its current health and current crew
     *     are restored to their maximum possible value.
     * </p>
     *
     * <p>
     *     After repairs begin the ship is unable to act.This method calculates how many actions
     *     the starship will skip until the repairs are complete.This is based on how damaged the ship was.
     * </p>
     */
    public void repairShip() {
        if (shipDocked) {

            int healthBand = (int) (currentHealth / 25.0f);
            switch (healthBand) {
                case 0:
                    //skip 4 actions
                    actionsToSkip = 4;
                    break;
                case 1:
                    //Skip 3 actions
                    actionsToSkip = 3;
                    break;
                case 2:
                    //skip 2 actions
                    actionsToSkip = 2;
                    break;
                case 3:
                    //skip 1 action
                    actionsToSkip = 1;
                    break;
                case 4:
                    //skip 0 actions
                    actionsToSkip = 0;
                    break;
            }
            currentHealth = maxShipHealth;
            currentCrew = maxCrew;
        }

    }

    /**
     * Executes a one-off attack against a target ship in the same sector.
     * <p>
     *     Damage is calculated using the attacking ship's current attack strength
     *      and the target ship's current defence strength, with a minimum damage value 5.
     * </p>
     *<p>
     *     When a ship is attacked, crew members are incapacitated based on the damage dealt.
     *     The target's crew count will never be reduced below 1.
     *</p>
     *
     * @param targetShip the starship being attacked.
     */
    public void executeAttackShip(Starship targetShip) {
        if (targetShip.isDisabled()) {
            return;
        }

        // Calculates the damage dealt.
        float damage = this.currentAttackStrength - targetShip.currentDefenceStrength;
        if (damage < 5) {
            damage = 5;
        }
        // Deals the damage to the target ship.
        targetShip.currentHealth = Math.max(0, targetShip.currentHealth - damage);

        // Calculates the number of incapacitated crew and subtracts it from the starship's current crew.
        int incapCrew = (int) Math.ceil((damage / targetShip.maxShipHealth) * targetShip.maxCrew);
        targetShip.currentCrew = Math.max(1, targetShip.currentCrew - incapCrew);


        // Recalculate derived stats
        targetShip.calcCurrAttStr();
        targetShip.calcCurrDefStr();

        // Disables the ship if its health reaches 0
        if (targetShip.getCurrentHealth() <= 0) {
            targetShip.disableShip();
            System.out.println("Ship has been disabled");
        }
    }

    /**
     * Executes a one-off attack against a target starbase in the same sector.
     * <p>
     *     Damage is calculated using the attacking ship's current attack strength
     *      and the target starbase's current defence strength, with a minimum damage value 5.
     * </p>
     *
     * @param targetBase the starbase being attacked.
     */
    public void executeAttackBase(Starbase targetBase) {
        // Calculates the damage dealt.
        float damage = this.currentAttackStrength - targetBase.getCurrentDefenceStrength();
        if (damage < 5) {
            damage = 5;
        }
        // Deals the damage to the target base
        targetBase.takeDamage(damage);


        // Recalculate derived stats
        targetBase.calcCurrDefStr();

        // Disables the base if its health reaches 0
        if (targetBase.getCurrentHealth() <= 0) {
            targetBase.disableBase();
        }
    }
    /**
     *  Disables the ship by removing it from its fleet.
     */
    public void disableShip() {
        disabled = true;
        myFleet.removeStarship(this);
    }
}






