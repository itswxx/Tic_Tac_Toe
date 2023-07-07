/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tic_Tac_Toe;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author arpit
 */
public class playingWindow extends javax.swing.JFrame implements ActionListener {

    //private char buttonArray[][] = new char[3][3];
    private boolean firstPlayerAI;
    private JButton[][] buttonArray = new JButton[5][5];
    private int board[][] = new int[5][5], counter = 1;
    ImageIcon tick = new ImageIcon("circle.png");
    ImageIcon cross = new ImageIcon("delete-icon64.png");
    
    /**
     * Creates new form playingWindow
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (e.getSource() == buttonArray[i][j]) {
                    this.clickButton(i, j);
                    if(this.firstPlayerAI){
                        this.playAI();
                    }
                    return;
                }
            }
        }
    }
    
    private void playAI(){
        AI_Player.Move bestMove = AI_Player.findBestMove(this.board);
        this.clickButton(bestMove.row, bestMove.col);
    }

    private void clickButton(int i, int j) {
        
        if (this.counter % 2 == 1) {
            this.buttonArray[i][j].setIcon(tick);
            this.board[i][j] = 1;
        } else {
            this.buttonArray[i][j].setIcon(cross);
            this.board[i][j] = -1;
        }
        this.buttonArray[i][j].setEnabled(false);
        this.counter++;
        this.showWinner();
        
    }

    public playingWindow() {
        initComponents();
        this.initialize();
        if( this.firstPlayerAI){
            this.playAI();
        }
    }

    private void initialize() {
//        String answer = JOptionPane.showInputDialog(null, "Do you want to play with me?(Enter Y) or just type anything else if you're too scared ;P");
//        if( answer.equals("Y")){
//            this.firstPlayerAI = true;
//        }
//        else{
//            this.firstPlayerAI = false;
//        }
        this.firstPlayerAI=true;
        this.playingBoard.setLayout(new GridLayout(5, 5));

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                this.buttonArray[i][j] = new JButton();
                this.buttonArray[i][j].setIcon(null);
                this.buttonArray[i][j].addActionListener(this);
                this.playingBoard.add(this.buttonArray[i][j]);
            }
        }
    }

    private void showWinner(){
        if( this.findWinner() == 1){
            JOptionPane.showMessageDialog(null, "Player One Wins!");
            this.refresh();
        }
        else if( this.findWinner() == -1){
            JOptionPane.showMessageDialog(null, "Player Two Wins!");
            this.refresh();
        }
        else if (!this.isMoveLeft()) {
            JOptionPane.showMessageDialog(null, "It's a Draw!");
            this.refresh();
        }
    }
    
    private boolean isMoveLeft() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (this.buttonArray[i][j].isEnabled()) {
                    return true;
                }
            }
        }
        return false;
    }

    private int findWinner() {
        for (int i = 0; i < 5; i++) {
            if (this.board[i][0] + this.board[i][1] + this.board[i][2]+ this.board[i][3] + this.board[i][4] == 5) {
                this.buttonArray[i][0].setEnabled(true);
                this.buttonArray[i][1].setEnabled(true);
                this.buttonArray[i][2].setEnabled(true);
                this.buttonArray[i][3].setEnabled(true);
                this.buttonArray[i][4].setEnabled(true);
                return 1;
            } else if (this.board[i][0] + this.board[i][1] + this.board[i][2]+ this.board[i][3] + this.board[i][4] == -5) {
                this.buttonArray[i][0].setEnabled(true);
                this.buttonArray[i][1].setEnabled(true);
                this.buttonArray[i][2].setEnabled(true);
                this.buttonArray[i][3].setEnabled(true);
                this.buttonArray[i][4].setEnabled(true);
                return -1;
            }
        }
        for (int i = 0; i < 5; i++) {
            if (this.board[0][i] + this.board[1][i] + this.board[2][i]+ this.board[3][i] + this.board[4][i] == 5) {
                this.buttonArray[0][i].setEnabled(true);
                this.buttonArray[1][i].setEnabled(true);
                this.buttonArray[2][i].setEnabled(true);
                this.buttonArray[3][i].setEnabled(true);
                this.buttonArray[4][i].setEnabled(true);
                return 1;
            }
            if (this.board[0][i] + this.board[1][i] + this.board[2][i]+ this.board[3][i] + this.board[4][i] == -5) {
                this.buttonArray[0][i].setEnabled(true);
                this.buttonArray[1][i].setEnabled(true);
                this.buttonArray[2][i].setEnabled(true);
                this.buttonArray[3][i].setEnabled(true);
                this.buttonArray[4][i].setEnabled(true);
                return -1;
            }
        }

        if (this.board[0][0] + this.board[1][1] + this.board[2][2]+ this.board[3][3] + this.board[4][4] == 5) {
            this.buttonArray[0][0].setEnabled(true);
            this.buttonArray[1][1].setEnabled(true);
            this.buttonArray[2][2].setEnabled(true);
            this.buttonArray[3][3].setEnabled(true);
            this.buttonArray[4][4].setEnabled(true);
            return 1;
        }
        if (this.board[0][0] + this.board[1][1] + this.board[2][2]+ this.board[3][3] + this.board[4][4] == -5) {
            this.buttonArray[0][0].setEnabled(true);
            this.buttonArray[1][1].setEnabled(true);
            this.buttonArray[2][2].setEnabled(true);
            this.buttonArray[3][3].setEnabled(true);
            this.buttonArray[4][4].setEnabled(true);
            return -1;
        }

        if (this.board[0][4] + this.board[1][3] + this.board[2][2]+ this.board[3][1] + this.board[4][0] == 5) {
            this.buttonArray[0][4].setEnabled(true);
            this.buttonArray[1][3].setEnabled(true);
            this.buttonArray[2][2].setEnabled(true);
            this.buttonArray[3][1].setEnabled(true);
            this.buttonArray[4][0].setEnabled(true);
            return 1;
        }
        if (this.board[0][4] + this.board[1][3] + this.board[2][2]+ this.board[3][1] + this.board[4][0] == -5) {
            this.buttonArray[0][4].setEnabled(true);
            this.buttonArray[1][3].setEnabled(true);
            this.buttonArray[2][2].setEnabled(true);
            this.buttonArray[3][1].setEnabled(true);
            this.buttonArray[4][0].setEnabled(true);
            return -1;
        }
        return 0;
    }
    
    private void refresh(){
        this.counter = 1;
        this.board = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                this.buttonArray[i][j].setIcon(null);
                this.buttonArray[i][j].setEnabled(true);
            }
        }
        if( this.firstPlayerAI){
            this.playAI();
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        playingBoard = new javax.swing.JPanel();
        btnExit = new java.awt.Button();
        btnReset = new java.awt.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        playingBoard.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "TIC TAC TOE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18), new java.awt.Color(0, 153, 0))); // NOI18N
        playingBoard.setLayout(new java.awt.GridLayout(1, 0));
        getContentPane().add(playingBoard, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 420, 440));
        playingBoard.getAccessibleContext().setAccessibleName("");

        btnExit.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnExit.setLabel("Thoát");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        getContentPane().add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 500, -1, -1));

        btnReset.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnReset.setLabel("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        getContentPane().add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 500, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        refresh();
        
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
         if(JOptionPane.showConfirmDialog(this, "bạn có muốn thoát?","Thông báo", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
        {
            System.exit(0);
        }
    }//GEN-LAST:event_btnExitActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(playingWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(playingWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(playingWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(playingWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new playingWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button btnExit;
    private java.awt.Button btnReset;
    private javax.swing.JPanel playingBoard;
    // End of variables declaration//GEN-END:variables
}
