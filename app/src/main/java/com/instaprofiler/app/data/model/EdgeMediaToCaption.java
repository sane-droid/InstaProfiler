package com.instaprofiler.app.data.model;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EdgeMediaToCaption {

    @SerializedName("edges")
    @Expose
    private List<Edge__> edges = null;

    public List<Edge__> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge__> edges) {
        this.edges = edges;
    }

}
