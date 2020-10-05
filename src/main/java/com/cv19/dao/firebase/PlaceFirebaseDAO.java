/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv19.dao.firebase;

import com.cv19.dao.DatabaseCallback;
import com.cv19.dao.PlaceDAO;
import com.cv19.dao.models.Place;
import com.cv19.dao.models.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.cloud.FirestoreClient;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafdi
 */
public class PlaceFirebaseDAO implements PlaceDAO {
    
    Firestore fDat = FirestoreClient.getFirestore();
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    
    public void getPlaceByID(String id, int pos, DatabaseCallback callback, int callbackCode){

        DocumentReference docRef = fDat.collection("places").document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        // ...
        // future.get() blocks on response
        DocumentSnapshot document;
        try {
            document = future.get();
            if (document.exists()) {
                callback.callback(document.toObject(Place.class),pos,callbackCode);
            } 
            else {
                callback.showError(new Exception("Nessun risultato per: "+id), callbackCode);
            }
        } catch (InterruptedException ex) {
            callback.showError(ex, callbackCode);
        } catch (ExecutionException ex) {
            callback.showError(ex, callbackCode);
        }
        
    }

    @Override
    public void getPlaceByID(String id, DatabaseCallback callback, int callbackCode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
