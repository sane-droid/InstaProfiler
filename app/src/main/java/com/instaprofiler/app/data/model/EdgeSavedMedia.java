package com.instaprofiler.app.data.model;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EdgeSavedMedia {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("page_info")
    @Expose
    private PageInfo__ pageInfo;
    @SerializedName("edges")
    @Expose
    private List<Object> edges = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public PageInfo__ getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo__ pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<Object> getEdges() {
        return edges;
    }

    public void setEdges(List<Object> edges) {
        this.edges = edges;
    }

}
