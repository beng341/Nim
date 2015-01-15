package gui;

import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author ben
 */
public class PilePanel extends JPanel{
    
    private JTextArea text;
    
    public PilePanel(int numPieces)
    {
        text = new JTextArea(numPieces + " pieces");
        text.setPreferredSize(new Dimension(100,100));
        text.setEditable(false);
        this.add(text);
    }
}
