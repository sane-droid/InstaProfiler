package com.instaprofiler.app.data.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InstaService {

    @SerializedName("logging_page_id")
    @Expose
    private String loggingPageId;
    @SerializedName("show_suggested_profiles")
    @Expose
    private Boolean showSuggestedProfiles;
    @SerializedName("show_follow_dialog")
    @Expose
    private Boolean showFollowDialog;
    @SerializedName("graphql")
    @Expose
    private Graphql graphql;
    @SerializedName("toast_content_on_load")
    @Expose
    private Object toastContentOnLoad;
    @SerializedName("show_view_shop")
    @Expose
    private Boolean showViewShop;
    @SerializedName("profile_pic_edit_sync_props")
    @Expose
    private Object profilePicEditSyncProps;

    public String getLoggingPageId() {
        return loggingPageId;
    }

    public void setLoggingPageId(String loggingPageId) {
        this.loggingPageId = loggingPageId;
    }

    public Boolean getShowSuggestedProfiles() {
        return showSuggestedProfiles;
    }

    public void setShowSuggestedProfiles(Boolean showSuggestedProfiles) {
        this.showSuggestedProfiles = showSuggestedProfiles;
    }

    public Boolean getShowFollowDialog() {
        return showFollowDialog;
    }

    public void setShowFollowDialog(Boolean showFollowDialog) {
        this.showFollowDialog = showFollowDialog;
    }

    public Graphql getGraphql() {
        return graphql;
    }

    public void setGraphql(Graphql graphql) {
        this.graphql = graphql;
    }

    public Object getToastContentOnLoad() {
        return toastContentOnLoad;
    }

    public void setToastContentOnLoad(Object toastContentOnLoad) {
        this.toastContentOnLoad = toastContentOnLoad;
    }

    public Boolean getShowViewShop() {
        return showViewShop;
    }

    public void setShowViewShop(Boolean showViewShop) {
        this.showViewShop = showViewShop;
    }

    public Object getProfilePicEditSyncProps() {
        return profilePicEditSyncProps;
    }

    public void setProfilePicEditSyncProps(Object profilePicEditSyncProps) {
        this.profilePicEditSyncProps = profilePicEditSyncProps;
    }

}
