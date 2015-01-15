package player;

import java.util.ArrayList;
import java.util.HashMap;
import nim.Nim;
import nim.NimState;
import nim.Turn;

/**
 *
 * @author ben 
 */
public class Computer extends Player {

    /**
     * Maps a state to whether it is a winning or losing state.
     */
    private HashMap<NimState, Boolean> winners;
    
    /**
     * true if and only if we have searched all moves.
     */
    private boolean discovered = false;
    
    
    /**
     * Creates a Computer Player.
     * Initially, the Computer player has not discovered its optimal moves. 
     * @param playerNumber              
     */
    public Computer(int playerNumber)
    {
        super(playerNumber);
        discovered = false;
    }
    
    /**
     * Decides for each state what move should be taken and stores info for later.
     * 
     * @param numPiles 
     */
    public void discoverMoves(int numPiles)
    {
        winners = new HashMap<NimState, Boolean>();
        HashMap discovered_states = new HashMap<NimState, Boolean>();
        NimState new_state = new NimState(numPiles);  // n piles of items
        
        //insert info about base case. Insert into pile with largest capacity
        //to ensure we find all possible states.
        new_state.setPile(new_state.getNumPiles()-1, 1);
        winners.put(new_state, false);
        discovered_states = (HashMap) winners.clone();
        
        boolean new_state_found = false;
        HashMap<NimState, Boolean> unsearched_states;
        long numIterations = 0;
        
        while(true)
        {
            new_state_found = false;
            unsearched_states = (HashMap<NimState, Boolean>) discovered_states.clone();
            discovered_states.clear();
            
            for(Object object: unsearched_states.keySet())
            {
                NimState current_state = new NimState((NimState)object);
                
                //very important to traverse backwards, otherwise we do not search
                //states that do not have a pile of 1.
                for(int pile = current_state.getNumPiles()-1; pile >= 0; --pile)
                {
                    new_state = new NimState(current_state);
                    int pile_amount = new_state.getPile(pile);
                    
                    if(pile_amount >= pile+1)
                        continue;         
                    
                    new_state.setPile(pile, pile_amount+1);
                    
                    if(winners.get(new_state) != null || discovered_states.get(new_state) != null)
                            continue; // We have already learned about this state
                    else
                        new_state_found = true;
                    
                    boolean winning = false;
                    
                    if(new_state.isForbiddenState())//if forbidden, always put true
                    {
                        winning = true;
                    }
                    else
                    {
                        for(NimState child : new_state.getChildren())
                        {
                            numIterations++;
                            
                            if(winners.get(child) == null)
                                continue;

                            if((Boolean)winners.get(child) == false)
                            {
                                winning = true;
                                new_state.setMove(child);
                                break;
                            }
                        }
                    }
                    discovered_states.put(new_state, winning);
                }
            }
            if(!new_state_found)
                break;
            
            winners.putAll(discovered_states);
            //System.out.println("# iterations so far: " + numIterations);
            //System.out.println("# states so far: " + winners.size());
        }
        
        discovered = true;
    }
    
    
    /**
     * Chooses the best action based on the moves previously discovered and the
     * current state of the game.
     * 
     * @param game
     * @return 
     */
    @Override
    public Turn decideAction(Nim game)
    {
        System.out.println("-------------------------------------------------");
        System.out.println("Starting Computer turn. Piles are:");
        printPiles(game.getPiles());
        
        // Nim sum is not used in the algorithm
        int nimSum =  computeNimSum(game.getPiles());
        System.out.println("Nim Sum = " + nimSum);
        
        //get moves searched by other computer or search moves
        if(!discovered)
        {
            if(game.getStates() != null)
            {
                winners = game.getStates();//other computer searched moves
                discovered = true;
            }
            else
            {
                discoverMoves(game.getPiles().size());
                game.setStates(winners);
            }
        }
        
        //find this state in the map of winners, use it's move.
        NimState new_state = new NimState(game.getPiles());//current state of board
        for(Object o: winners.keySet())
        {
            NimState board = (NimState)o;
            if(new_state.equals(board))
            {
                new_state = board;
                break;
            }
        }
        
        Turn turn = new_state.getTurn();
        
        return turn;
    }
    
    private void setWinners(HashMap<NimState, Boolean> map)
    {
        winners = map;
    }
    
    /**
     * Computes the Nim Sum of the piles.
     * The nim sum is the bitwise XOR of all the pile values.
     *
     * @param piles
     * @return
     */
    private int computeNimSum(ArrayList<Integer> piles){
        int nimSum = 0;

        for(int i = 0 ; i < piles.size() ; i++){
            nimSum ^= piles.get(i);
        }
        
        return nimSum;
    }
}