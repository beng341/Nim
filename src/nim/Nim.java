package nim;

import gui.GameFrame;
import gui.GameSetup;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
import player.Computer;
import player.Human;
import player.Player;

/**
 *
 * @author Ben Armstrong & Devin Calado
 */
public class Nim {
    
    /**
     * # milliseconds computer will delay before making a move.
     * TODO: Should actually be easy to use System.nanoTime() to only delay
     * for amount of time not used by computer deciding what to do.
     */
    private int compDelay;
    private int numPiles;
    private ArrayList<Integer> piles;
    private ArrayList<Player> players;
    private final int playerConfig;
    private HashMap<NimState, Boolean> states;
    private static JFrame frame;
    
    /**
     * Generate an initial Nim game.
     * @param playerConfig Player configuration.
     *                  0 - human vs human
     *                  1 - human vs computer
     *                  2 - computer vs computer
     *                  3 - computer vs human
     * 
     * @param numPiles initial number of piles
     * @param compDelay time to delay between computer moves if relevant
     */
    public Nim(int playerConfig, int numPiles, int compDelay)
    {
        this.playerConfig = playerConfig;
        this.compDelay = compDelay;
        this.numPiles = numPiles;
        states = null;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        

        System.out.println("");

        frame = new JFrame();
        frame.add(new GameSetup());
        frame.pack();
        frame.setVisible(true);
        
    }
    
    /**
     * Main game loop. Once the game is created this is called and it will
     * run players turns until the game is complete.
     */
    public void run()
    {
        setupGame();
        
        //GameFrame gameFrame = new GameFrame(piles);
        
        gameLoop:
        while(true)
        {
            for(Player p: players)
            {
                Turn turn = p.decideAction(this);
                updateState(turn);
                
                // Update the GUI
                //gameFrame.setPiles(piles);
                //gameFrame.update();

                if(gameOver())
                {
                    announceLoser(p);
                    break gameLoop;
                }
            }
        }
        
        frame.toFront();
    }
    
    public static void announceLoser(Player p)
    {
        System.out.println(p + " sucks and is a loser!");
    }
    
    private boolean gameOver()
    {
        if(new NimState(piles).isForbiddenState())
            return true;
        
        for(int i: piles)
            if(i != 0)
                return false;
        
        
        return true;
    }
    
    private void updateState(Turn turn)
    {
        for(int i = 0; i < piles.size(); ++i)
        {
            if(piles.get(i) == turn.getPileAmount())
            {
                piles.set(i, turn.getPileAmount()-turn.getTakeAmount());
                return;
            }
        }
        
        throw new IllegalArgumentException("Invalid Move. Please implement better error handling.");
        //stacks.set(turn.getStackAmount(), stacks.get(turn.getStackAmount())-turn.getTakeAmount());
    }
    
    /**
     * Sets up players and piles. In a separate method so it can be called
     * after the constructor has completed.
     */
    public void setupGame()
    {
        piles = new ArrayList<Integer>(numPiles);
        
        for(int i = 0; i < numPiles; ++i)
            piles.add(i+1);
        
        this.players = new ArrayList<Player>();
        switch(playerConfig)
        {
            //TODO: Move this out of constructor so we can pass "this" without killing everything
            case 0: players.add(new Human(1));
                    players.add(new Human(2));
                break;
            case 1: players.add(new Human(1));
                    players.add(new Computer(2));
                break;
            case 2: players.add(new Computer(1));
                    players.add(new Computer(2));
                break;
            case 3: players.add(new Computer(1));
                    players.add(new Human(2));
                break;
            default:
        }
    }
    
    public HashMap<NimState, Boolean> getStates()
    {
        return this.states;
    }
    
    public void setStates(HashMap<NimState, Boolean> new_states)
    {
        this.states = new_states;
    }
    
    public ArrayList<Integer> getPiles()
    {
        return piles;
    }
}