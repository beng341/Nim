package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

/**
 *
 * @author ben
 */
public class GameFrame_NotUsed extends JFrame {
    
    public GameFrame_NotUsed(int numPanels)
    {
        initUI(numPanels);
    }
    
    public void initUI(int numPanels)
    {
        setLayout(new GridLayout(1,numPanels));
        
        for(int i = 0; i < numPanels; ++i)
        {
            PilePanel panel = new PilePanel(i);
            add(panel);
        }
        pack();
        
        setTitle("FlowLayout Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
}
