import com.spaceshipgame.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //we would like you to
        //have some runnable code that, when run, will perform the following sequence of
        //actions (the following sequence does not need to be turn based, you can just
        //trigger each action in the given sequence):
        Board board = new Board(2, 2);
        //• Creates a “Player 1” fleet with 1 starbase and 3 ships in “Sector 1”.
        Fleet fleet1 = new Fleet("Player1",3,1, board.getSector("sector1"));
        //• Creates a “Player 2” fleet with 1 starbase and 3 ships in “Sector 2”.
        Fleet fleet2 = new Fleet("Player2", 3, 1, board.getSector("sector4"));
        //• Moves all ships in the “Player 1” fleet to “Sector 2”.
        fleet1.mobiliseToSector(board.getSector("sector4"));
        System.out.println("Fleet 1 has been mobilised to sector 4");

        //• Docks two ships from the “Player 2” fleet into the “Player 2” starbase.
        // Gets first two undocked ships from fleet 2
        Starbase base2 = fleet2.getStarbases().get(0);
        int dockedCount = 0;
        for (Starship ship : fleet2.getStarships()) {
            ship.dock(base2);
            dockedCount++;

            //stops docking after two ships
            if (dockedCount == 2) {
                break;
            }
        }

          if (dockedCount < 2) {
                System.out.println("Not enough undocked ships to dock");
            } else {
              System.out.println("Two Player 2 ships docked at their starbase.");
        }
        //• Selects one ship from the “Player 1” fleet and uses it to attack the
        //remaining undocked starship from the “Player 2” fleet two times.

        //select ship
        Starship ship1 = fleet1.getStarships().get(0);

        //Find undocked ships from the player 2 fleet

        Starship targetShip = null;
        for (Starship ship : fleet2.getStarships()) {
            targetShip = ship;
        }

        //attack  the ships
        if (ship1 != null && targetShip != null) {
            //attack the ships twice, first for critical hit and then for second hit
            fleet1.attackTargetShip(targetShip);
            fleet1.attackTargetShip(targetShip);
            System.out.println("Player 1's ship has attacked player 2's undocked ship twice");
        }
        else {
            System.out.println("Attack failed");
        }

        //• Docks the remaining undocked starship in the “Player 2” fleet with the
        //“Player 2” starbase then repairs it.

        Starship remainingShip = null;

        for (Starship ship : fleet2.getStarships()) {
            if (!ship.isDocked()) {
                remainingShip = ship;
                break;
            }
        }

        if (remainingShip != null) {
            remainingShip.dock(base2);
            dockedCount++;
            System.out.println("The remaining undocked starship in player's 2 fleet has been docked.");

            remainingShip.repairShip();
            System.out.println("Player 2's ship has been repaired.");
        } else {
            System.out.println("No undocked ships in  player 2's fleet to dock");
        }


        //• Commands all starships in the “Player 1” fleet to attack the “Player 2”
        //starbase (repeatedly, until the “Player 2” starbase is destroyed).
        while (base2.getCurrentHealth() > 0) {
            fleet1.attackTargetBase(base2);
        }
        System.out.println("Player 2's base has been destroyed");

    }
}