/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv19.dao;

/**
 *
 * @author rafdi
 */
public interface ReviewDAO {
    public void getReviewsByUserID(String uid, DatabaseCallback callback, int callbackCode);
}
