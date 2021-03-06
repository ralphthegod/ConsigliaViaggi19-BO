/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv19.dao;

import com.cv19.dao.models.Place;
import com.cv19.dao.models.Review;
import com.cv19.dao.models.User;
import java.util.List;

/**
 *
 * @author rafdi
 */
public interface DatabaseCallback {
    public void callback(int callbackCode);
    public void callback(String message, int callbackCode);
    public void callback(HandshakeRequest hreq, String id, int callbackCode);
    public void callback(boolean response, int callbackCode);
    public void callback(List<Review> reviews, int callbackCode);
    public void callback(User user, int callbackCode);
    public void callback(Place place, int callbackCode);
    public void callback(Place place, int pos, int callbackCode);
    public void showError(Exception e, int callbackCode);
}
