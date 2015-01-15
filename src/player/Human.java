package player;

import java.util.ArrayList;
import java.util.Scanner;
import nim.Nim;
import nim.Turn;

/**
 *
 * @author ben
 */
public class Human extends Player {

    public Human(int playerNumber) {
        super(playerNumber);
    }

    @Override
    public Turn decideAction(Nim game)
    {
        ArrayList<Integer> piles = game.getPiles();
        int numStacks = piles.size();
        System.out.println("--------------------------------------------------");
        System.out.println("Welcome " + this +", please decide a move. The piles are as follows:");
        printPiles(game.getPiles());
        
        Turn turn = null;
        while(true)
        {
            turn = getTurnFromUser(game.getPiles());
            if(!isValidTurn(turn, game))
                System.out.println("Invalid move. Please try again.");
            else
                break;
        }
        
        return turn;
    }
    
    private Turn getTurnFromUser(ArrayList<Integer> piles)
    {
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Take from which pile?");
        int pile = scan.nextInt()-1;
        
        System.out.println("Take how many?");
        int amount = scan.nextInt();
        
        return new Turn(piles.get(pile), amount);
    }

    private boolean isValidTurn(Turn turn, Nim game)
    {
        for(int i = 0; i < game.getPiles().size(); ++i)
        {
            if(game.getPiles().get(i) == turn.getPileAmount()
                    && game.getPiles().get(i) >= turn.getTakeAmount())
                return true;
        }
        
        return false;
    }
    
}
