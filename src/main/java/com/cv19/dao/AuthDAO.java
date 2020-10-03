/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv19.dao;

import com.cv19.ui.AuthUI;
import javax.swing.JDialog;

/**
 *
 * @author rafdi
 */
public interface AuthDAO {

    public void authentication(String username,DatabaseCallback callback, int callbackCode);
    public void handleHandshakeResponses(HandshakeRequest hreq, String userID, DatabaseCallback callback, int callbackCode);
    public void cancelHandshake(String uid);
}
