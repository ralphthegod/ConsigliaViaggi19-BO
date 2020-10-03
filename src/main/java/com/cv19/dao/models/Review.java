/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv19.dao.models;

import java.io.Serializable;

/**
 *
 * @author rafdi
 */
public class Review implements Serializable {

    String placeId;
    String userId;
    String text;
    String year;
    String month;
    String day;
    Integer rating;


    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Review(String placeId, String userId, String text, String year, String month, String day, Integer rating) {
        this.placeId = placeId;
        this.userId = userId;
        this.text = text;
        this.year = year;
        this.month = month;
        this.day = day;
        this.rating = rating;
    }


    public Review()
    {

    }

    public String getDate()
    {
        return day+"/"+month+"/"+year;
    }


    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

}