package com.instaprofiler.app.data.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Node__ {

    @SerializedName("text")
    @Expose
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
