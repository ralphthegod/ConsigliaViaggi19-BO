/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv19.main;

import com.cv19.dao.DAOFactory;
import com.cv19.ui.AuthUI;
import com.cv19.ui.HomeUI;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.FirebaseApp;
import com.google.auth.oauth2.GoogleCredentials;
import java.io.IOException;

/**
 *
 * @author rafdi
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // java swing init design.
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AuthUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        DAOFactory.getDAOInstance().initDatabase();
        //new AuthUI().setVisible(true);
        new HomeUI().setVisible(true);
    }
}
