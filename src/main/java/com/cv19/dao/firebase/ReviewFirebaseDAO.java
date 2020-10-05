/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv19.dao.firebase;

import com.cv19.dao.DatabaseCallback;
import com.cv19.dao.ReviewDAO;
import com.cv19.dao.models.Place;
import com.cv19.dao.models.Review;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.EventListener;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreException;
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
import javax.annotation.Nullable;

/**
 *
 * @author rafdi
 */
public class ReviewFirebaseDAO implements ReviewDAO {

    Firestore fDat = FirestoreClient.getFirestore();
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    
    @Override
    public void getReviewsByUserID(String uid, DatabaseCallback callback, int callbackCode) {
        List<Review> resultRev;
        Query query = fDat.collection("reviewPool").whereEqualTo("userId", uid);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        try {
            if(querySnapshot.get().getDocuments().size() > 0){
                 resultRev = querySnapshot.get().toObjects(Review.class);
                callback.callback(resultRev,callbackCode);
            }
            else{
              callback.showError(new Exception("Nessun risultato per '"+uid+"'."), callbackCode);  
            }
        } catch (InterruptedException ex) {
            callback.showError(ex, callbackCode);
        } catch (ExecutionException ex) {
            callback.showError(ex, callbackCode);
        }   
    }

    @Override
    public void deleteReviewsByUserID(String uid, DatabaseCallback callback, int callbackCode) {
        ApiFuture<QuerySnapshot> future = fDat.collection("reviewPool").whereEqualTo("userId", uid).get();
        List<QueryDocumentSnapshot> documents;
        try {
            documents = future.get().getDocuments();
            for (DocumentSnapshot document : documents) {
                document.getReference().delete();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ReviewFirebaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(ReviewFirebaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
