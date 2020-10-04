package com.cv19.dao.models;


import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rafdi
 */
/**La classe User detiene localmente le informazioni relative ad un utente.
 * La classe maniente una propria istanza (LocalInstance), che fa riferimento all'utente corrente.*/
public class User implements Serializable {

    private String displayName;
    private String databasebID;

    public String getDatabasebID() {
        return databasebID;
    }

    public void setDatabasebID(String databasebID) {
        this.databasebID = databasebID;
    }
    private String email;
    private String avatar;
    private String userID;
    private boolean blacklisted;
    private Integer nReview;
    private Float avgReview;
    private int sumReviews;
    private String registerDate;
    private String lastSignIn;

    public String getLastSignIn() {
        return lastSignIn;
    }

    public void setLastSignIn(String lastSignIn) {
        this.lastSignIn = lastSignIn;
    }
    private String firstName;
    private String lastName;

    public User(){
        
    }
    
    public User(String displayName, String email, String userID, boolean blacklisted, Integer nReview, Float avgReview, String registerDate,String avatar,String firstName,String lastName, int sumReviews) {
        this.displayName = displayName;
        this.email = email;
        this.userID = userID;
        this.blacklisted = blacklisted;
        this.nReview = nReview;
        this.avgReview = avgReview;
        this.registerDate = registerDate;
        this.avatar=avatar;
        this.firstName=firstName;
        this.lastName=lastName;
        this.sumReviews=sumReviews;
    }

    public int getSumReviews() {
        return sumReviews;
    }

    public void setSumReviews(int sumReviews) {
        this.sumReviews = sumReviews;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public boolean isBlacklisted() {
        return blacklisted;
    }

    public void setBlacklisted(boolean blacklisted) {
        blacklisted = blacklisted;
    }

    public int getnReview() {
        return nReview;
    }

    public void setnReview(int nReview) {
        this.nReview = nReview;
    }

    public float getAvgReview() {
        return avgReview;
    }

    public void setAvgReview(float avgReview) {
        this.avgReview = avgReview;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }


    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

}
