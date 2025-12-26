import com.spaceshipgame.*;
/**
 * <p>
 *     This is the main class of the spaceship game that runs the following sequence of actions:
 *     <ul>
 *         <li>Creates a “Player 1” fleet with 1 starbase and 3 ships in “Sector 1”.</li>
 *         <li>Creates a “Player 2” fleet with 1 starbase and 3 ships in “Sector 4”.</li>
 *         <li>Moves all ships in the “Player 1” fleet to “Sector 4”.</li>
 *         <li>Docks two ships from the “Player 2” fleet into the “Player 2” starbase.</li>
 *         <li> Selects one ship from the “Player 1” fleet and uses it to attack the
 *              remaining undocked starship from the “Player 2” fleet two times.</li>
 *         <li>Docks the remaining undocked starship in the “Player 2” fleet with the
 *             “Player 2” starbase then repairs it.</li>
 *         <li>Commands all starships in the “Player 1” fleet to attack the “Player 2”
 *             starbase (repeatedly, until the “Player 2” starbase is destroyed).</li>
 *     </ul>
 * </p>
 *
 * @author Kelly
 * @version 26/12/2025
 */
public class Main {
    private final Board board;
    private Fleet fleet1;
    private Fleet fleet2;
    private Starbase base2;
    private int dockedCount = 0;

    public Main() {
        // Initialises the spaceship game board.
        board = new Board(2, 2);
    }
    public static void main(String[] args) {
        Main game = new Main();
        game.runTask1();
        game.runTask2();
        game.runTask3();
        game.runTask4();
        game.runTask5();
        game.runTask6();
        game.runTask7();
    }
    public void runTask1() {
        //  TASK 1 Creates a “Player 1” fleet with 1 starbase and 3 ships in “Sector 1”.
        fleet1 = new Fleet("Player1",3,1, board.getSector("sector1"));
    }
    public void runTask2() {
        //  TASK 2 Creates a “Player 2” fleet with 1 starbase and 3 ships in “Sector 4”.
        // Changed sector 2 to sector 4 as it makes more sense visually when I eventually implement a user interface.
        fleet2 = new Fleet("Player2", 3, 1, board.getSector("sector4"));
    }
    public void runTask3() {
        // TASK 3 Moves all ships in the “Player 1” fleet to “Sector 4”.
        fleet1.mobiliseToSector(board.getSector("sector4"));
        System.out.println("Fleet 1 has been mobilised to sector 4");
    }
    public void runTask4() {
        //  TASK 4 Docks two ships from the “Player 2” fleet into the “Player 2” starbase.
        // Gets first two undocked ships from fleet 2
        base2 = fleet2.getStarbases().get(0);
        int dockedCount = 0;
        for (Starship ship : fleet2.getStarships()) {
            ship.dock(base2);
            dockedCount++;

            //stops docking ships after first two ships
            if (dockedCount == 2) {
                break;
            }
        }
        // If there aren't 2 ships to dock a message is printed to the console
        if (dockedCount < 2) {
            System.out.println("Not enough undocked ships to dock");
        } else {
            System.out.println("Two Player 2 ships docked at their starbase.");
        }
    }
    public void runTask5() {
        // TASK 5 Selects one ship from the “Player 1” fleet and uses it to attack the
        // remaining undocked starship from the “Player 2” fleet two times.
        // Select a ship from player 1's fleet
        Starship ship1 = fleet1.getStarships().get(0);
        // Find undocked ships from the player 2 fleet to attack
        Starship targetShip = null;
        for (Starship ship : fleet2.getStarships()) {
            if (!ship.isDocked()) {
                targetShip = ship;
                // breaks after finding the first undocked ship
                break;
            }
        }
        // Attack the ship
        if (ship1 != null && targetShip != null) {
            // Console messages to check if the attack function is working on impacting ship health
            System.out.println("Target ship health BEFORE attack: "

                    + targetShip.getCurrentHealth());
            System.out.println("Number of Crew:" + targetShip.getCurrentCrew());
            // Attack the ship twice, first for critical hit and then for second hit
            // First attack
            ship1.executeAttackShip(targetShip);

            System.out.println("Target ship health AFTER first attack: " + targetShip.getCurrentHealth());
            System.out.println("Number of Crew:" + targetShip.getCurrentCrew());

            // Second attack
            ship1.executeAttackShip(targetShip);

            System.out.println("Target ship health AFTER second attack: " + targetShip.getCurrentHealth());
            System.out.println("Number of Crew:" + targetShip.getCurrentCrew());
            System.out.println("Player 1's ship has attacked player 2's undocked ship twice");
        }
        else {
            System.out.println("Attack failed");
        }
    }
    public void runTask6() {
        // TASK 6 Docks the remaining undocked starship in the “Player 2” fleet with the
        // “Player 2” starbase then repairs it.
        Starship remainingShip = null;
        // Goes through player 2's fleet to find remaining undocked ship.
        for (Starship ship : fleet2.getStarships()) {
            if (!ship.isDocked()) {
                remainingShip = ship;
                break;
            }
        }
        // Docks the remaining ship and repairs it.
        if (remainingShip != null) {
            remainingShip.dock(base2);
            dockedCount++;
            System.out.println("The remaining undocked starship in player's 2 fleet has been docked.");

            remainingShip.repairShip();
            System.out.println("Player 2's ship has been repaired.");
        } else {
            System.out.println("No undocked ships in  player 2's fleet to dock");
        }
    }
    public void runTask7() {
        // TASK 7 Commands all starships in the “Player 1” fleet to attack the “Player 2”
        // starbase (repeatedly, until the “Player 2” starbase is destroyed).
        // Goes through the list of starships in player 1's fleet and calculates their current attack and defence strength.
        for (Starship ship : fleet1.getStarships()) {
            ship.calcCurrAttStr();
            ship.calcCurrDefStr();
        }
        // While player 2's starbase's health is above 0 player 1's fleet will attack it.
        // When it goes below 0, player 2's starbase is destroyed.
        while (base2.getCurrentHealth() > 0) {
            fleet1.attackTargetBase(base2);
            System.out.println("Target base health " + base2.getCurrentHealth());
        }
        System.out.println("Player 2's base has been destroyed");
    }
}






