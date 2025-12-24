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
        Starbase base = fleet2.getStarbases().getFirst();

        // Get all undocked ships
        List<Starship> undockedShips = new ArrayList<>();
        for (Starship s : fleet2.getStarships()) {
            if (!s.isDocked()) {
                undockedShips.add(s);
            }
        }

        // Pick the first two ships
        Starship ship1 = undockedShips.get(0);
        Starship ship2 = undockedShips.get(1);

        ship1.dock(base);
        ship2.dock(base);
        System.out.println("Two Player 2 ships docked at their starbase.");

        //• Selects one ship from the “Player 1” fleet and uses it to attack the
        //remaining undocked starship from the “Player 2” fleet two times.


        //• Docks the remaining undocked starship in the “Player 2” fleet with the
        //“Player 2” starbase then repairs it.

        //• Commands all starships in the “Player 1” fleet to attack the “Player 2”
        //starbase (repeatedly, until the “Player 2” starbase is destroyed).
    }
}