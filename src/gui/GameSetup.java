package gui;

import nim.Nim;

/**
 *
 * @author Ben Armstrong & Devin Calado
 */
public class GameSetup extends javax.swing.JPanel {


    /**
     * An Enumeration that represents the various game play types.
     */
    public enum GameType {
        HUM_HUM, HUM_COMP, COMP_COMP, COMP_HUM;
    }
      
    /**
     * Creates new form GameSetup
     */
    public GameSetup() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        playerButtonGroup = new javax.swing.ButtonGroup();
        humanVsHumanButton = new javax.swing.JRadioButton();
        humanVsComputerButton = new javax.swing.JRadioButton();
        computerVsComputerButton = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        compDelayField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        numPilesField = new javax.swing.JTextField();
        startGameButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        computerVsHumanButton = new javax.swing.JRadioButton();

        playerButtonGroup.add(humanVsHumanButton);
        humanVsHumanButton.setText("Human vs Human");
        humanVsHumanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                humanVsHumanButtonActionPerformed(evt);
            }
        });

        playerButtonGroup.add(humanVsComputerButton);
        humanVsComputerButton.setSelected(true);
        humanVsComputerButton.setText("Human vs Computer");

        playerButtonGroup.add(computerVsComputerButton);
        computerVsComputerButton.setText("Computer vs Computer");

        jLabel1.setText("Game Type:");

        jLabel2.setText("Computer Delay (ms):");

        compDelayField.setText("500");
        compDelayField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compDelayFieldActionPerformed(evt);
            }
        });

        jLabel3.setText("Number of Piles:");

        numPilesField.setText("5");

        startGameButton.setText("Start Game");
        startGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startGameButtonActionPerformed(evt);
            }
        });

        jLabel4.setText("Welcome to Nim!");

        playerButtonGroup.add(computerVsHumanButton);
        computerVsHumanButton.setText("Computer vs Human");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(humanVsHumanButton)
                            .addComponent(humanVsComputerButton)
                            .addComponent(computerVsComputerButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(compDelayField)
                            .addComponent(numPilesField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(computerVsHumanButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(startGameButton)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(237, 237, 237)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(humanVsHumanButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(humanVsComputerButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(computerVsComputerButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(computerVsHumanButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(compDelayField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(numPilesField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addComponent(startGameButton)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void humanVsHumanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_humanVsHumanButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_humanVsHumanButtonActionPerformed

    private void compDelayFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compDelayFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_compDelayFieldActionPerformed

    private void startGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startGameButtonActionPerformed
        
        int numPiles = Integer.parseInt(numPilesField.getText());
        int compDelay = Integer.parseInt(compDelayField.getText());
        GameType gameType = GameType.HUM_HUM;
        
        if(computerVsComputerButton.isSelected())
            gameType = GameType.COMP_COMP;
        else if(humanVsComputerButton.isSelected())
            gameType = GameType.HUM_COMP;
        else if(humanVsHumanButton.isSelected())
            gameType = GameType.HUM_HUM;
        else if(computerVsHumanButton.isSelected())
            gameType = GameType.COMP_HUM;
        
        Nim game = new Nim(gameType.ordinal(), numPiles, compDelay);
        game.run();
        
    }//GEN-LAST:event_startGameButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField compDelayField;
    private javax.swing.JRadioButton computerVsComputerButton;
    private javax.swing.JRadioButton computerVsHumanButton;
    private javax.swing.JRadioButton humanVsComputerButton;
    private javax.swing.JRadioButton humanVsHumanButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField numPilesField;
    private javax.swing.ButtonGroup playerButtonGroup;
    private javax.swing.JButton startGameButton;
    // End of variables declaration//GEN-END:variables
}
