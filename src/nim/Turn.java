package nim;

/**
 * Represents a single turn made by a Player.
 * Will take the specified number of blocks from a pile which currently has the
 * specified number of blocks.
 * @author Ben
 */
public class Turn {
    
    private final int pile_amount;
    private final int take_amount;
    
    public Turn(int pile_amount, int take_amount)
    {
        this.pile_amount = pile_amount;
        this.take_amount = take_amount;
    }

    public int getPileAmount()
    {
        return pile_amount;
    }

    public int getTakeAmount()
    {
        return take_amount;
    }
    
    public String toString()
    {
        return "Taking " + take_amount + " from Pile " + (pile_amount+1) + ".";
    }
}
