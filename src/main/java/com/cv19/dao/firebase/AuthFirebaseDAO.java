/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv19.dao.firebase;

import com.cv19.dao.AuthDAO;
import com.cv19.dao.DatabaseCallback;
import com.cv19.dao.HandshakeRequest;
import com.cv19.ui.AuthUI;
import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.EventListener;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreException;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseReference.CompletionListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.internal.NonNull;
import java.awt.Component;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author rafdi
 */
public class AuthFirebaseDAO implements AuthDAO{

    
    
    private FirebaseAuth fAuth = FirebaseAuth.getInstance();
    private FirebaseDatabase fDat = FirebaseDatabase.getInstance();
    private Firestore firestore = FirestoreClient.getFirestore();
 
    @Override
    public void authentication(String username,DatabaseCallback callback, int callbackCode) {
        try {
            getIdByUsername(username,callback,callbackCode);
        } catch (Exception ex) {
            callback.showError(ex,callbackCode);
        }
    }

    private void getIdByUsername(String username, DatabaseCallback callback, int callbackCode) throws InterruptedException, ExecutionException {
        System.out.println("Obtaining user.");
        Query query = firestore.collection("userPool").whereEqualTo("email",username);
        ApiFuture<UserRecord> userSnapshot = fAuth.getUserByEmailAsync(username);
        checkPermissions(userSnapshot.get().getUid(),callback,callbackCode); 
    }
    
    public static final int PERMISSIONS_ADMIN = 1;
    public static final int PERMISSIONS_MOD = 0;
    
    private void checkPermissions(String userID, DatabaseCallback callback, int callbackCode){
        callback.callback("Checking permissions...",callbackCode);
        ValueEventListener listener = new ValueEventListener(){
            public void onDataChange(DataSnapshot ds){
                if(ds.child("highUsers").hasChild(userID)){
                    String role = (String) ds.child("highUsers").child(userID).child("role").getValue();
                    switch(role){
                        case "admin":
                                requestHandshake(userID,PERMISSIONS_ADMIN,callback,callbackCode);
                            break;
                        case "mod":
                                requestHandshake(userID,PERMISSIONS_MOD,callback,callbackCode);
                            break;
                    }
                    
                }
                else{
                    callback.showError(new Exception("This user has no permissions."), callbackCode);
                }
            }

            public void onCancelled(DatabaseError de){
                callback.showError(de.toException(), callbackCode);
            }
        };
        fDat.getReference().addListenerForSingleValueEvent(listener);
    }
    
    public void cancelHandshake(String uid){
        fDat.getReference().child("backendTokens").child(uid).removeValueAsync();
    }
    
    public void deleteExpiredHandshakeResponse(String uid){
        fDat.getReference().child("backendResponseTokens").child(uid).removeValueAsync();
    }
    
    public void requestHandshake(String userID, int permissionLevel, DatabaseCallback callback, int callbackCode){
        
        deleteExpiredHandshakeResponse(userID);
        
        SecureRandom sr = null;
        try {
            sr = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AuthFirebaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        int handshakeCode = sr.nextInt(9000000) + 1000000;
        
        ValueEventListener listener = new ValueEventListener(){
            public void onDataChange(DataSnapshot ds){
                if(ds.child("backendTokens").hasChild(userID)){
                    cancelHandshake(userID);
                    callback.showError(new Exception("Un handshake per l'ID: "+userID+" è già stato richiesto. Verrà impostato lo stato di attesa entro il quale si potrà soddisfare l'handshake già esistente, in caso contrario l'handshake sarà eliminato."), 99);
                    return;
                }
                else{
                    callback.callback("Requesting handshake...",callbackCode);
                       HandshakeRequest hreq = null;
                    try {
                        hreq = new HandshakeRequest(userID,fAuth.createCustomToken(userID));
                        hreq.setRequestCode(handshakeCode);
                        ApiFuture<Void> uploadHandshake = fDat.getReference().child("backendTokens").child(userID).setValueAsync(hreq);
                         
                    } catch (FirebaseAuthException ex) {
                        Logger.getLogger(AuthFirebaseDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    callback.callback("Succesfully requested handshake.",callbackCode);
                        callback.callback(hreq,userID,callbackCode);
                }
            }
            
            public void onCancelled(DatabaseError de){
                callback.showError(de.toException(), callbackCode);
            }
        };
        
        fDat.getReference().addListenerForSingleValueEvent(listener);

    }
    
    
    @Override
    public void handleHandshakeResponses(HandshakeRequest hreq, String userID, DatabaseCallback callback, int callbackCode){
        ChildEventListener listener = new ChildEventListener(){
            public void onChildAdded(DataSnapshot ds, String string){
                new Thread(new Runnable(){
                    public void run(){
                        if(ds.getKey().equals(userID))
                        {
                            System.out.println("Response found. " + hreq.getRequestCode());
                            if(((String) ds.child("token").getValue()).equals(hreq.getRequestToken())){
                                System.out.println("Token agreed.");
                                String responseCode = ds.child("responseCode").getValue().toString();
                                if(responseCode.equals(String.valueOf(hreq.getRequestCode()))){
                                    System.out.println("Successfully logged in.");
                                    cancelHandshake(userID);
                                    deleteExpiredHandshakeResponse(userID);
                                    callback.callback(true, callbackCode);
                                }
                                else{
                                    System.out.println("Failed to login.");
                                    deleteExpiredHandshakeResponse(userID);
                                    cancelHandshake(userID);
                                    callback.callback(false,callbackCode);
                                }
                            }
                            else System.out.println("Not this token.");
                        }
                        else System.out.println("No responses yet.");
                    }
                }).start();  
            }

            public void onChildChanged(DataSnapshot ds, String string){}

            public void onChildRemoved(DataSnapshot ds){}

            public void onChildMoved(DataSnapshot ds, String string){}

            public void onCancelled(DatabaseError de){}
        };
        fDat.getReference().child("backendResponseTokens").addChildEventListener(listener);
        System.out.println("Waiting for responses for id "+userID+"...");
    }
    

}
