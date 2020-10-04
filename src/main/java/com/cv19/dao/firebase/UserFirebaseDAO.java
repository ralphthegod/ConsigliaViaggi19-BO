/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv19.dao.firebase;

import com.cv19.dao.DatabaseCallback;
import com.cv19.dao.UserDAO;
import com.cv19.dao.models.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.cloud.FirestoreClient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafdi
 */
public class UserFirebaseDAO implements UserDAO{

    Firestore fDat = FirestoreClient.getFirestore();
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    
    @Override
    public void getUserByEmail(String email, DatabaseCallback callback, int callbackCode) {
        Query query = fDat.collection("userPool").whereEqualTo("email", email);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        try {
            List<QueryDocumentSnapshot> results = querySnapshot.get().getDocuments();
            if(results.size() > 0){
                if(results.size()>1) callback.callback("E' stato trovato più di un risultato. Verrà mostrato il primo.", callbackCode);
                User result = results.get(0).toObject(User.class);
                result.setDatabasebID(results.get(0).getId());
                getUserMetadataByUser(result,callback,callbackCode);
                callback.callback(result,callbackCode);
            }
            else{
              callback.showError(new Exception("Nessun risultato per '"+email+"'."), callbackCode);  
            }
        } catch (InterruptedException ex) {
            callback.showError(ex, callbackCode);
        } catch (ExecutionException ex) {
            callback.showError(ex, callbackCode);
        }
    }

    @Override
    public void getUserByUsername(String username, DatabaseCallback callback, int callbackCode) {
        Query query = fDat.collection("userPool").whereEqualTo("displayName", username);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        try {
            List<QueryDocumentSnapshot> results = querySnapshot.get().getDocuments();
            if(results.size() > 0){
                if(results.size()>1) callback.callback("E' stato trovato più di un risultato. Verrà mostrato il primo.", callbackCode);
                User result = results.get(0).toObject(User.class);
                result.setDatabasebID(results.get(0).getId());
                getUserMetadataByUser(result,callback,callbackCode);
                callback.callback(result,callbackCode);
            }
            else{
              callback.showError(new Exception("Nessun risultato per '"+username+"'."), callbackCode);  
            }
        } catch (InterruptedException ex) {
            callback.showError(ex, callbackCode);
        } catch (ExecutionException ex) {
            callback.showError(ex, callbackCode);
        }
    }
    

    @Override
    public void getUserByID(String ID, DatabaseCallback callback, int callbackCode) {
        Query query = fDat.collection("userPool").whereEqualTo("userID", ID);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        try {
            List<QueryDocumentSnapshot> results = querySnapshot.get().getDocuments();
            if(results.size() > 0){
                if(results.size()>1) callback.callback("E' stato trovato più di un risultato. Verrà mostrato il primo.", callbackCode);
                User result = results.get(0).toObject(User.class);
                result.setDatabasebID(results.get(0).getId());
                getUserMetadataByUser(result,callback,callbackCode);
                callback.callback(result,callbackCode);
            }
            else{
              callback.showError(new Exception("Nessun risultato per '"+ID+"'."), callbackCode);  
            }
        } catch (InterruptedException ex) {
            callback.showError(ex, callbackCode);
        } catch (ExecutionException ex) {
            callback.showError(ex, callbackCode);
        }
    }   

    @Override
    public void getUserMetadataByUser(User user, DatabaseCallback callback, int callbackCode) {
        try {
           user.setLastSignIn(String.valueOf(fAuth.getUser(user.getUserID()).getUserMetadata().getLastSignInTimestamp()));
        } catch (FirebaseAuthException ex) {
            callback.showError(ex, callbackCode);
        }
    }

    @Override
    public void deleteUserByUser(User user, DatabaseCallback callback, int callbackCode) {
        try {
            fAuth.deleteUser(user.getUserID());
            fDat.collection("userPool").document(user.getDatabasebID()).delete();
        } catch (FirebaseAuthException ex) {
            callback.showError(ex, callbackCode);
        }
        callback.callback("Utente eliminato.",callbackCode);
        
    }

    @Override
    public void blacklistUserByUser(User user, DatabaseCallback callback, int callbackCode) {
            if(!user.isBlacklisted()){
                fDat.collection("userPool").document(user.getDatabasebID()).update("blacklisted",true).addListener(new Runnable(){
                    public void run(){
                       callback.callback("L'utente è ora nella blacklist.",callbackCode); 
                    }
                }, MoreExecutors.directExecutor());  
            }
            else{
                fDat.collection("userPool").document(user.getDatabasebID()).update("blacklisted",false).addListener(new Runnable(){
                    public void run(){
                       callback.callback("L'utente è ora fuori dalla blacklist.",callbackCode); 
                    }
                }, MoreExecutors.directExecutor());  
            }
    }

}