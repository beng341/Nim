package nim;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

/**
 * Represents a single state of a given Nim game board. Has a bunch of piles and
 * amounts of blocks in each pile.
 * 
 * @author Ben Armstrong & Devin Calado
 */
public class NimState 
{
    private int numPiles;   //total number of piles
    private int size;       //total number of non-empty piles
    private HashMap<Integer, Integer> piles;
    
    /**
     * multiplicity [i] contains the number of piles of size i.
     * This array has numPiles indexes since no pile has more than that many
     * blocks.
     */
    private int[] multiplicity;
    private Turn bestTurn = null;
    
    /**
     * 
     * @param numPiles 
     */
    public NimState(int numPiles)
    {
        this.numPiles = numPiles;
        this.size = 0;
        piles = new HashMap<Integer, Integer>();
        multiplicity = new int[numPiles+1];
        multiplicity[0] = numPiles;
        
        for(int i = 0; i < numPiles; ++i)
            piles.put(i, 0);
    }
    
    /**
     * Copy constructor. Makes a deep copy of parent.
     * @param parent 
     */
    public NimState(NimState parent)
    {
        this.numPiles = parent.numPiles;
        this.size = parent.size;
        this.piles = (HashMap<Integer, Integer>) parent.piles.clone();
        this.multiplicity = parent.multiplicity.clone();
    }
    
    /**
     * Converts an ArrayList representation of a board game into this form.
     * @param my_piles 
     */
    public NimState(ArrayList<Integer> my_piles)
    {
        this.numPiles = my_piles.size();
        this.size = 0;
        this.piles = new HashMap<Integer, Integer>();
        multiplicity = new int[numPiles+1];
        
        for(int i = 0; i < my_piles.size(); ++i)
        {
            piles.put(i, my_piles.get(i));
            if(piles.get(i) == 0)
                size++;
            multiplicity[piles.get(i)]++;
        }
    }
    
    /**
     * Used to create a possible child state of parent board.
     * @param parent
     * @param pile
     * @param toRemove 
     */
    private NimState(NimState parent, int pile, int toRemove)
    {
        this.numPiles = parent.numPiles;
        this.size = parent.size;
        this.piles = (HashMap<Integer, Integer>) parent.piles.clone();
        this.multiplicity = parent.multiplicity.clone();
        
        multiplicity[piles.get(pile)]--;
        
        int remaining = piles.get(pile)-toRemove;
        if(remaining >= 0)
            piles.put(pile, remaining);
        else
            throw new IllegalArgumentException("Tried to remove more blocks than exist in that pile");
        multiplicity[remaining]++;
        size = numPiles - multiplicity[0];
    }
    
    /**
     * Sets the given pile number to have the given amount of blocks.
     * @param pile
     * @param amount 
     */
    public void setPile(int pile, int amount)
    {
        if(numPiles <= pile)
            throw new IllegalArgumentException(pile + " is beyond the range of possible piles");
        
        if(piles.get(pile) == 0 && amount > 0)
        {
            size++;
        }
        else if(piles.get(pile) > 0 && amount == 0)
        {
            size--;
        }
        multiplicity[piles.get(pile)]--;
        multiplicity[amount]++;
        
        
        piles.put(pile, amount);
    }
    
    /**
     * Gives the child state that this game state should move to. This method 
     * figures out what to do to get to that state (if possible) by finding the
     * pile that can be modified to get to the child in one turn. If no such 
     * single pile exists, a random move is set.
     * @param child 
     */
    public void setMove(NimState child)
    {
        boolean possible = false;
        int pile_num = -1;
        int pile_amount = -1;
        int take_amount = -1;
        for(int i = 0; i < numPiles; ++i)
        {
            if(this.piles.get(i) != child.getPile(i))
            {
                //if we've found a previous pile that was different
                if(pile_num != -1 && take_amount != -1)
                {
                    possible = false;
                    break;
                }
                
                possible = true;
                pile_num = i;
                pile_amount = piles.get(i);
                take_amount = Math.abs(this.piles.get(i) - child.getPile(i));
            }
        }
        
        if(possible)
            bestTurn = new Turn(pile_amount, take_amount);
        else
            bestTurn = randomTurn();
    }
    
    /**
     * Returns the best turn for the current NimState.
     * 
     * @return                  The current best next turn
     */
    public Turn getTurn()
    {
        if(bestTurn == null)
            return randomTurn();
        
        return bestTurn;
    }
    
    /**
     * Called when this is not a state that we can win from. Removes a random
     * amount from the first non-empty pile.
     * @return                  A random turn
     */
    public Turn randomTurn()
    {
        Random r = new Random();
        
        int pile = 0;
        int pile_amount = -1;
        int take_amount = 1;
        for(int i = 0; i < numPiles; ++i)
        {
            if(piles.get(i) != 0)
            {
                pile = i;
                pile_amount = piles.get(i);
                take_amount = r.nextInt(piles.get(i))+1;
                break;
            }
        }
        
        return new Turn(pile_amount, take_amount);
    }
    
    
    
    /**
     * Returns the children states of the current NimState
     * 
     * @return                 A list of the children states
     */
    public ArrayList<NimState> getChildren()
    {
        
        ArrayList<NimState> children = new ArrayList<>();
        
        for(int pile = 0; pile < numPiles; ++pile)
        {
            for(int j = 1; j <= piles.get(pile); ++j)
            {
                
                children.add(new NimState(this, pile, j));
            }
        }
        
        return children;
    }
    
    
    /**
     * Returns true iff the given state is forbidden. Currently, forbidden states
     * are 1,2,3 and 1,1,2,2.
     * @param state
     * @return                      Whether or not the state is a forbidden state
     */
    public boolean isForbiddenState()
    {
        //multiplicity matrices for each forbidden state
        int[] bad1 = new int[this.getNumPiles()+1];//1,2,3
        int[] bad2 = new int[this.getNumPiles()+1];//1,1,2,2
        bad1[0] = this.getNumPiles();
        bad2[0] = this.getNumPiles();
        
        if(this.getNumPiles() < 3)
            return false;
        if(this.getNumPiles() >= 4)
        {
            bad2[0] -= 4;
            bad2[1] = 2;
            bad2[2] = 2;
            if(Arrays.equals(bad2, this.getMultipicity()))
                return true;
            
        }
        
        bad1[0] -= 3;
        bad1[1] = 1;
        bad1[2] = 1;
        bad1[3] = 1;
        
        if(Arrays.equals(bad1, this.getMultipicity()))
            return true;
        
        return false;
    }
    
    /**
     * True iff the boards have the same number of piles and the same number of
     * piles for each size of pile.
     * @param o
     * @return                     True if the objects are the same NimState
     */
    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof NimState))
            return false;
        
        NimState other = (NimState)o;
        
        return Arrays.equals(this.multiplicity, other.getMultipicity());
    }
    
    public int[] getMultipicity()
    {
        return this.multiplicity;
    }
    
    /**
     * Returns size of pile i if pile i exists.
     * @param i
     * @return                      Size of pile i
     */
    public int getPile(int i)
    {
        if(i > numPiles)
            throw new IllegalArgumentException(i + " is beyond the range of possible piles");
        
        return piles.get(i);
    }
    
    /**
     * 
     * @return The total number of piles, including empty ones.
     */
    public int getNumPiles()
    {
        return numPiles;
    }
    
    /**
     * 
     * @return The number of non-empty piles.
     */
    public int getSize()
    {
        return size;
    }
    
    
    /**
     * Returns the hash code of the NimState
     * 
     * @return              hash code value
     */
    @Override
    public int hashCode()
    {
        // #hashmaster
//        //TODO: Is this really correct? 
//        int hash = 17;
//        //hash += 59 * multiplicity.hashCode();
//        for(int i = 0; i < multiplicity.length; ++i)
//            hash += 13 * multiplicity[i];
//        //hash += 37 * piles.hashCode();
//        
//        return hash;
        
        return Arrays.hashCode(multiplicity);
    }
    
    @Override
    public String toString()
    {
        String s = "";
        
        for(int i = 0; i < numPiles; ++i)
        {
            s += piles.get(i) + ",";
        }
        return s;
    }
}
