package com.richard.abigayle.hotelfinder.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LENOVO on 4/26/2018.
 */

public class DistanceBetween {

    @SerializedName("destination_addresses")
    @Expose
    private List<Object> destination = new ArrayList<Object>();

    @SerializedName("origin_addresses")
    @Expose
    private List<Object> origin = new ArrayList<Object>();

    @SerializedName("rows")
    @Expose
    private List<Rows> results = new ArrayList();

    @SerializedName("status")
    @Expose
    private String status;
    public List<Object> getDestination() {
        return destination;
    }

    public void setDestination(List<Object> destination) {
        this.destination = destination;
    }

    public List<Object> getOrigin() {
        return origin;
    }

    public void setOrigin(List<Object> origin) {
        this.origin = origin;
    }

    public List<Rows> getResults() {
        return results;
    }

    public void setResults(List<Rows> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




}



