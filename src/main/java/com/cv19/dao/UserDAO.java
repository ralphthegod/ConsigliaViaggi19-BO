/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv19.dao;

import com.cv19.dao.models.User;

/**
 *
 * @author rafdi
 */
public interface UserDAO {
    public void getUserByEmail(String email, DatabaseCallback callback, int callbackCode);
    public void getUserByUsername(String username, DatabaseCallback callback, int callbackCode);
    public void getUserByID(String ID, DatabaseCallback callback, int callbackCode);
    public void getUserMetadataByUser(User user,DatabaseCallback callback, int callbackCode);
    public void deleteUserByUser(User user,DatabaseCallback callback,int callbackCode);
    public void blacklistUserByUser(User user, DatabaseCallback callback, int callbackCode);
}
