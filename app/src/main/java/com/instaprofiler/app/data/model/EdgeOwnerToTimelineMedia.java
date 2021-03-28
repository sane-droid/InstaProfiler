package com.instaprofiler.app.data.model;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EdgeOwnerToTimelineMedia {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("page_info")
    @Expose
    private PageInfo_ pageInfo;
    @SerializedName("edges")
    @Expose
    private List<Edge> edges = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public PageInfo_ getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo_ pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

}
