/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv19.ui;

import com.cv19.dao.AuthDAO;
import com.cv19.dao.DAOFactory;
import com.cv19.dao.DatabaseCallback;
import com.cv19.dao.HandshakeRequest;
import com.cv19.dao.firebase.AuthFirebaseDAO;
import com.cv19.dao.models.Place;
import com.cv19.dao.models.Review;
import com.cv19.dao.models.User;
import java.awt.Component;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author rafdi
 */
public class AuthUI extends javax.swing.JFrame implements DatabaseCallback{

    /**
     * Creates new form MainUI
     */
    public AuthUI() {
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

        eUsername = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        bSignin = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        tHandshakeStatus = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        eUsername.setToolTipText("");
        eUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eUsernameActionPerformed(evt);
            }
        });

        jLabel1.setLabelFor(eUsername);
        jLabel1.setText("Username");

        bSignin.setText("Sign in");
        bSignin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSigninActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("Benvenuto in ConsigliaViaggi19. Autenticati prima di continuare.");

        tHandshakeStatus.setForeground(new java.awt.Color(0, 0, 0));
        tHandshakeStatus.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        tHandshakeStatus.setText("Pronto per la connessione.");

        jLabel2.setLabelFor(eUsername);
        jLabel2.setText("PIN");

        jPasswordField1.setText("jPasswordField1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tHandshakeStatus)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPasswordField1)
                            .addComponent(eUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE))
                        .addGap(12, 12, 12)
                        .addComponent(bSignin, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bSignin)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(tHandshakeStatus)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    AuthDAO authDao = null;
    
    private void eUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_eUsernameActionPerformed

    private void bSigninActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSigninActionPerformed
        bSignin.setEnabled(false);
        authDao = DAOFactory.getDAOInstance().getAuthDAO();
        authDao.authentication(eUsername.getText(), this, 0);
    }//GEN-LAST:event_bSigninActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AuthUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bSignin;
    private javax.swing.JTextField eUsername;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JLabel tHandshakeStatus;
    // End of variables declaration//GEN-END:variables

    @Override
    public void callback(int callbackCode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void callback(String message, int callbackCode) {
        tHandshakeStatus.setText(message);
    }

    @Override
    public void callback(List<Review> reviews, int callbackCode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    volatile private boolean isHandshakeAccepted = false;
    volatile private boolean isHandshakeCodeWrong = false;
    
    public void callback(HandshakeRequest hreq, String id, int callbackCode) {
        
        tHandshakeStatus.setText("Inserire nel client il seguente codice: "+hreq.getRequestCode()+". Il codice ha una validit� di 30 secondi.");
        new Thread(new Runnable(){
            public void run(){
                authDao.handleHandshakeResponses(hreq,id, AuthUI.this, 0);
            }
        }).start();
        ScheduledExecutorService s = Executors.newSingleThreadScheduledExecutor();     
        s.schedule(new Runnable() {
            public void run() {
                if(!isHandshakeAccepted){
                    if(!isHandshakeCodeWrong){
                        
                        showError(new Exception("Richiesta handshake scaduta. Riprovare."),0);
                    }
                    authDao.cancelHandshake(id);
                }      
            }
        }, 30, TimeUnit.SECONDS);

    }

    @Override
    public void showError(Exception e, int callbackCode) {
        if(callbackCode == 99){
            JOptionPane.showMessageDialog(this,
                e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            tHandshakeStatus.setText("In attesa della terminazione dell'handshake gi� esistente. (30)");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                Logger.getLogger(AuthUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            bSignin.setEnabled(true);
            tHandshakeStatus.setText("Pronto per la connessione");
        }
        else{
            JOptionPane.showMessageDialog(this,
                e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            tHandshakeStatus.setText("Attendere cooldown (10) prima di effettuare un'ulteriore richiesta.");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                Logger.getLogger(AuthUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            bSignin.setEnabled(true);
            tHandshakeStatus.setText("Pronto per la connessione");
        }
    }

   
    public void callback(boolean response, int callbackCode) {
        if(response){ 
            isHandshakeAccepted = true;
            tHandshakeStatus.setText("Handshake effettuato con successo.");
            new HomeUI().setVisible(true);
            dispose();
        }
        else
        {
            isHandshakeCodeWrong = true;
            showError(new Exception("Codice errato."),0);
        }
    }

    @Override
    public void callback(User user, int callbackCode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void callback(Place place, int callbackCode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void callback(Place place, int pos, int callbackCode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
