package com.spaceshipgame;

public class Starship {
    //Attributes ---------------------------------------------------------
    private float maxAttackStrength;
    private float maxDefenceStrength;
    private int maxCrewMembers;
    private float maxShipHealth;
    private Boolean shipDocked = false;
    private float currentHealth;
    private int currentCrew;
    private float currentAttackStrength;
    private float currentDefenceStrength;
    private Fleet myFleet;
    private Sector currentSector;
    //   private Starbase dockedStarbase;
    private String newSector;
    private int actionsToSkip;

    //empty constructor
    public Starship() {

    }

    //constructor that initialises the starship with values for its attributes
    //add sector later when it's developed
    public Starship(Fleet fleet, float maxAttStrength, float maxDefStrength, int maxCrew, float maxHealth, Sector currSector) {
        myFleet = fleet;
        maxAttackStrength = maxAttStrength;
        maxDefenceStrength = maxDefStrength;
        maxCrewMembers = maxCrew;
        maxShipHealth = maxHealth;
        currentSector = currSector;
    }

    public Sector getSector() {
        return currentSector;
    }

    public Fleet getFleet() {
        return myFleet;
    }

    public float getCurrDefStrength() {
        return currentDefenceStrength;
    }

    public float getCurrAttStrength() {
        return currentAttackStrength;
    }

    //methods
    //Move to a Sector
    //The ship can move into another sector. When a ship moves its current sector
    //changes to reflect the sector it has just moved in to.
    public void moveToSector(Sector newSector) {
        if (!this.isDocked()) {
            currentSector = newSector;
        }
    }

    public Boolean isDocked() {
        return shipDocked;
    }

    //Dock with a starbase
    public void dock(Starbase targetBase) {
        shipDocked = targetBase.dockShip(this);
    }

    //undock from a starbase
    public void undock(Starbase targetBase) {
        shipDocked = targetBase.undockShip(this);
    }


    // When a ship docks with a starbase it can no longer move or attack (until it undocks) but can now
    //repair.  When a ship is docked with a starbase, it cannot be attacked by another starship.

    //repair

    //The ship can be repaired, restoring its health and crew.
    //
    // When a ship is repaired, it’s current health and current crew are restored to their maximum possible value.
    //However, a ship can only be repaired whilst it is docked in a starbase.
    //Furthermore, when a ship is repaired it’s temporarily “out of action” whilst it is
    //being repaired. This means the next X actions that are attempted on the starship
    //will be “Skipped” until the repairs are complete. The number of actions skipped
    //are based on how damaged the ship was, see below:
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
            }
            currentHealth = maxShipHealth;
            currentCrew = maxCrewMembers;
        }

    }

    public Boolean shipCanAct() {
        if (actionsToSkip == 0) {
            return true;
        }
        return false;
    }

    //attack a target
    //CHECK IF OPPONENT TARGET IS IN THE SAME SECTOR IF NOT IT CANT BE ATTACKED YEP
    //
    //CHECK IF YOU ARE A SHIP THAT'S DOCKED YEP
    // CHECKS IF SHIP YOU'RE ATTACKING IS DOCKED B4 ATTACKING YEP
    //CHECK IF SHIP CAN PERFORM ACTIONS YES
    //CAN'T ATTACK DOCKED SHIPS YEP
    //CAN'T ATTACK SHIPS IN THE SAME FLEET


    public void executeAttackShip(Starship targetShip) {
            float damage = this.currentAttackStrength - currentDefenceStrength;
            if (damage < 5) {
                damage = 5;
            }
            targetShip.currentHealth -= damage;
           int incapCrew = (int) Math.ceil((damage / targetShip.maxShipHealth) * targetShip.maxCrewMembers);
           if  (incapCrew > targetShip.currentCrew) {
               currentCrew = 1;
           }
           targetShip.currentCrew -= incapCrew;
        }


    public void executeAttackBase(Starbase targetBase) {
            float damage = this.currentAttackStrength - targetBase.getCurrentDefenceStrength();
            if (damage < 5) {
                damage = 5;
            }
            targetBase.takeDamage(damage);
        }


    public void disable() {
       myFleet.removeStarship(this);
        }

        //calculates current defence strength
    public void calcCurrDefStr() {
        currentDefenceStrength = (float) Math.ceil((maxDefenceStrength * ((currentHealth + currentCrew) /  (maxShipHealth + maxCrewMembers))));
    }

    //calculates current attack strength
    public void calcCurrAttStr() {
        currentAttackStrength = (float) Math.ceil((maxAttackStrength * ((currentHealth / maxShipHealth))));

    }
}






