package com.richard.abigayle.hotelfinder.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by LENOVO on 4/3/2018.
 */

public class Geometry {
    @SerializedName("location")
    @Expose

    private Location location;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
