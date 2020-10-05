/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv19.ui;

import com.cv19.dao.DAOFactory;
import com.cv19.dao.DatabaseCallback;
import com.cv19.dao.HandshakeRequest;
import com.cv19.dao.PlaceDAO;
import com.cv19.dao.models.Place;
import com.cv19.dao.models.Review;
import com.cv19.dao.models.User;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rafdi
 */
public class ReviewsResultUI extends javax.swing.JFrame implements DatabaseCallback {

    /**
     * Creates new form ReviewsResultUI
     */
    
    List<Review> toShow;
    PlaceDAO placeDao = DAOFactory.getDAOInstance().getPlaceDAO();
    List<String> findPlacesName = new ArrayList<>();
    DefaultTableModel dtb;
    
    public ReviewsResultUI(List<Review> reviews) {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        initComponents();
        tabResults.setEnabled(false);
        toShow = reviews;
        
        initTableWithResults();
        findPlacesName();
        
    }
    
    private void initTableWithResults(){
        dtb  = (DefaultTableModel) tabResults.getModel();
        int i = 0;
        for(Review r : toShow){
            dtb.addRow(new Object[]{r.getDate(),r.getPlaceId(),"0",r.getRating(),r.getText()});
            findPlacesName.add(r.getPlaceId());
        }
    }
    
    private void findPlacesName(){
        for(int i = 0;i<findPlacesName.size();i++){
            placeDao.getPlaceByID(findPlacesName.get(i), i, this, 0);
        }
    }
    
    public ReviewsResultUI() {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tabResults = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabResults.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Data", "ID Struttura", "Nome Struttura", "Rating", "Testo"
            }
        ));
        jScrollPane1.setViewportView(tabResults);

        jLabel1.setText("Recensioni");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 1021, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(ReviewsResultUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReviewsResultUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReviewsResultUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReviewsResultUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReviewsResultUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabResults;
    // End of variables declaration//GEN-END:variables

    @Override
    public void callback(int callbackCode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void callback(String message, int callbackCode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void callback(HandshakeRequest hreq, String id, int callbackCode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void callback(boolean response, int callbackCode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void callback(List<Review> reviews, int callbackCode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        dtb.setValueAt(place.getName(), pos, 2);
    }

    @Override
    public void showError(Exception e, int callbackCode) {
        JOptionPane.showMessageDialog(this,
                e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }
}