package player;

import java.util.ArrayList;
import nim.Nim;
import nim.Turn;

/**
 *
 * @author Ben Armstrong & Devin Calado
 * 
 * The Player class is an abstract class that provides the base class for 
 * implemented human or computer players.
 */
public abstract class Player {
    
    protected final int playerNumber;
    
    
    /**
     * Initializes a player for the Nim game.
     * 
     * 
     * @param playerNumber 
     */
    public Player(int playerNumber)
    {
        this.playerNumber = playerNumber;
    }
    
    /**
     * Have this player decide on an action to take this turn. 
     * @return a Turn containing a number of elements to take and a heap to take
     * them from.
     */
    public abstract Turn decideAction(Nim game);
    
    public int getPlayerNumber()
    {
        return this.playerNumber;
    }
    
    
    public String toString()
    {
        return "Player " + this.playerNumber;
    }

    
    /**
     * Prints the current state of the game to the console.
     * 
     * 
     * @param piles 
     */
    public void printPiles(ArrayList<Integer> piles) {

        
        // Would need a 'deep copy' of the array in order to re-order it for better output.
      //  ArrayList stack_output = piles.deepCopy();
        
//        Iterator<Integer> i = piles.iterator();
//        while (i.hasNext()) {
//            Integer value = i.next();
//            if (value == 0) {
//                i.remove();
//            }
//        }
//
//        Collections.sort(this.getStacks());

        System.out.print("Pile:  ");
        for (int i = 0; i < piles.size(); ++i) {
            System.out.print((i + 1) + "  ");
        }
        System.out.print("\nAmount: ");
        for(int i = 0; i < piles.size(); ++i)
        {
            System.out.print(piles.get(i) + "  ");
        }
        System.out.print("\n");
        
    }
}
