package com.richard.abigayle.hotelfinder.Helpers;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by LENOVO on 5/10/2018.
 */

@Entity(tableName = "user")
public class User {
    @PrimaryKey
    public String username;
    public String dayOfMonthIn;
    public String dayOfWeekIn;
    public String dayOfMonthOut;
    public String dayOfWeekOut;
    public String age;
    public String userLocation;
    public String locationImage;

    public User(String dayOfMonthIn, String dayOfWeekIn, String dayOfMonthOut, String dayOfWeekOut, String username, String age, String userLocation, String locationImage) {
        this.dayOfMonthIn = dayOfMonthIn;
        this.dayOfWeekIn = dayOfWeekIn;
        this.dayOfMonthOut = dayOfMonthOut;
        this.dayOfWeekOut = dayOfWeekOut;
        this.username = username;
        this.age = age;
        this.userLocation = userLocation;
        this.locationImage = locationImage;
    }
}
