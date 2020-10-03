package com.cv19.dao;

import com.cv19.dao.firebase.AuthFirebaseDAO;
import com.cv19.dao.firebase.PlaceFirebaseDAO;
import com.cv19.dao.firebase.ReviewFirebaseDAO;
import com.cv19.dao.firebase.UserFirebaseDAO;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rafdi
 */
public class DAOFactory {
    private static DAOFactory dao;
    static String db = "firebase";

    public static synchronized DAOFactory getDAOInstance() {
        if (dao == null)
            dao = new DAOFactory();
        return dao;
    }
    
    public synchronized void initDatabase(){
        switch(db){
            case "firebase":
                FileInputStream serviceAccount = null;
                try {
                    serviceAccount = new FileInputStream("consigliaviaggi19-387f9-firebase-adminsdk-sk2gs-87339a9d2a.json");
                } catch (FileNotFoundException ex) {
                    System.out.println("ServiceAccountKey not found.");
                }

                FirebaseOptions options = null;
                try {
                    options = new FirebaseOptions.Builder()
                            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                            .setDatabaseUrl("https://consigliaviaggi19-387f9.firebaseio.com")
                            .build();
                } catch (IOException ex) {
                    System.out.println("No credentials obtained.");
                }

                FirebaseApp.initializeApp(options);
            break;
        }
    }
    
    public ReviewDAO getReviewDAO() {
        if (db.equals("firebase"))
            return new ReviewFirebaseDAO();
        return null;
    }

    public UserDAO getUserDAO(){
        if(db.equals("firebase"))
            return new UserFirebaseDAO();
        return null;
    }

    public PlaceDAO getPlaceDAO(){
        if(db.equals("firebase"))
            return new PlaceFirebaseDAO();
        return null;
    }

    public AuthDAO getAuthDAO(){
        if(db.equals("firebase"))
            return new AuthFirebaseDAO();
        return null;
    }

}
