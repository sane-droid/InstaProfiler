package com.instaprofiler.app.data.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Node_ {

    @SerializedName("user")
    @Expose
    private User_ user;
    @SerializedName("x")
    @Expose
    private Double x;
    @SerializedName("y")
    @Expose
    private Double y;

    public User_ getUser() {
        return user;
    }

    public void setUser(User_ user) {
        this.user = user;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

}
