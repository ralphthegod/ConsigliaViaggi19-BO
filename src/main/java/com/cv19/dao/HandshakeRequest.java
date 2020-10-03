/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv19.dao;

import java.io.Serializable;

/**
 *
 * @author rafdi
 */
public class HandshakeRequest implements Serializable{
        private String userID;
        private String requestToken;
        private int requestCode;

    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }
        private boolean isAccepted = false;
        
        public HandshakeRequest(String uid, String rTok){
            userID = uid;
            requestToken = rTok;
        }

        public String getUserID() {
            return userID;
        }

        public void setUserID(String userID) {
            this.userID = userID;
        }

        public String getRequestToken() {
            return requestToken;
        }

        public void setRequestToken(String requestToken) {
            this.requestToken = requestToken;
        }

 
        
    }