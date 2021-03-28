package com.instaprofiler.app.data.model;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Node {

    @SerializedName("__typename")
    @Expose
    private String typename;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("shortcode")
    @Expose
    private String shortcode;
    @SerializedName("dimensions")
    @Expose
    private Dimensions dimensions;
    @SerializedName("display_url")
    @Expose
    private String displayUrl;
    @SerializedName("edge_media_to_tagged_user")
    @Expose
    private EdgeMediaToTaggedUser edgeMediaToTaggedUser;
    @SerializedName("fact_check_overall_rating")
    @Expose
    private Object factCheckOverallRating;
    @SerializedName("fact_check_information")
    @Expose
    private Object factCheckInformation;
    @SerializedName("gating_info")
    @Expose
    private Object gatingInfo;
    @SerializedName("sharing_friction_info")
    @Expose
    private SharingFrictionInfo sharingFrictionInfo;
    @SerializedName("media_overlay_info")
    @Expose
    private Object mediaOverlayInfo;
    @SerializedName("media_preview")
    @Expose
    private String mediaPreview;
    @SerializedName("owner")
    @Expose
    private Owner owner;
    @SerializedName("is_video")
    @Expose
    private Boolean isVideo;
    @SerializedName("accessibility_caption")
    @Expose
    private String accessibilityCaption;
    @SerializedName("edge_media_to_caption")
    @Expose
    private EdgeMediaToCaption edgeMediaToCaption;
    @SerializedName("edge_media_to_comment")
    @Expose
    private EdgeMediaToComment edgeMediaToComment;
    @SerializedName("comments_disabled")
    @Expose
    private Boolean commentsDisabled;
    @SerializedName("taken_at_timestamp")
    @Expose
    private Integer takenAtTimestamp;
    @SerializedName("edge_liked_by")
    @Expose
    private EdgeLikedBy edgeLikedBy;
    @SerializedName("edge_media_preview_like")
    @Expose
    private EdgeMediaPreviewLike edgeMediaPreviewLike;
    @SerializedName("location")
    @Expose
    private Object location;
    @SerializedName("thumbnail_src")
    @Expose
    private String thumbnailSrc;
    @SerializedName("thumbnail_resources")
    @Expose
    private List<ThumbnailResource> thumbnailResources = null;
    @SerializedName("dash_info")
    @Expose
    private DashInfo dashInfo;
    @SerializedName("has_audio")
    @Expose
    private Boolean hasAudio;
    @SerializedName("tracking_token")
    @Expose
    private String trackingToken;
    @SerializedName("video_url")
    @Expose
    private String videoUrl;
    @SerializedName("video_view_count")
    @Expose
    private Integer videoViewCount;
    @SerializedName("felix_profile_grid_crop")
    @Expose
    private Object felixProfileGridCrop;
    @SerializedName("product_type")
    @Expose
    private String productType;
    @SerializedName("clips_music_attribution_info")
    @Expose
    private Object clipsMusicAttributionInfo;

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShortcode() {
        return shortcode;
    }

    public void setShortcode(String shortcode) {
        this.shortcode = shortcode;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }

    public String getDisplayUrl() {
        return displayUrl;
    }

    public void setDisplayUrl(String displayUrl) {
        this.displayUrl = displayUrl;
    }

    public EdgeMediaToTaggedUser getEdgeMediaToTaggedUser() {
        return edgeMediaToTaggedUser;
    }

    public void setEdgeMediaToTaggedUser(EdgeMediaToTaggedUser edgeMediaToTaggedUser) {
        this.edgeMediaToTaggedUser = edgeMediaToTaggedUser;
    }

    public Object getFactCheckOverallRating() {
        return factCheckOverallRating;
    }

    public void setFactCheckOverallRating(Object factCheckOverallRating) {
        this.factCheckOverallRating = factCheckOverallRating;
    }

    public Object getFactCheckInformation() {
        return factCheckInformation;
    }

    public void setFactCheckInformation(Object factCheckInformation) {
        this.factCheckInformation = factCheckInformation;
    }

    public Object getGatingInfo() {
        return gatingInfo;
    }

    public void setGatingInfo(Object gatingInfo) {
        this.gatingInfo = gatingInfo;
    }

    public SharingFrictionInfo getSharingFrictionInfo() {
        return sharingFrictionInfo;
    }

    public void setSharingFrictionInfo(SharingFrictionInfo sharingFrictionInfo) {
        this.sharingFrictionInfo = sharingFrictionInfo;
    }

    public Object getMediaOverlayInfo() {
        return mediaOverlayInfo;
    }

    public void setMediaOverlayInfo(Object mediaOverlayInfo) {
        this.mediaOverlayInfo = mediaOverlayInfo;
    }

    public String getMediaPreview() {
        return mediaPreview;
    }

    public void setMediaPreview(String mediaPreview) {
        this.mediaPreview = mediaPreview;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Boolean getIsVideo() {
        return isVideo;
    }

    public void setIsVideo(Boolean isVideo) {
        this.isVideo = isVideo;
    }

    public String getAccessibilityCaption() {
        return accessibilityCaption;
    }

    public void setAccessibilityCaption(String accessibilityCaption) {
        this.accessibilityCaption = accessibilityCaption;
    }

    public EdgeMediaToCaption getEdgeMediaToCaption() {
        return edgeMediaToCaption;
    }

    public void setEdgeMediaToCaption(EdgeMediaToCaption edgeMediaToCaption) {
        this.edgeMediaToCaption = edgeMediaToCaption;
    }

    public EdgeMediaToComment getEdgeMediaToComment() {
        return edgeMediaToComment;
    }

    public void setEdgeMediaToComment(EdgeMediaToComment edgeMediaToComment) {
        this.edgeMediaToComment = edgeMediaToComment;
    }

    public Boolean getCommentsDisabled() {
        return commentsDisabled;
    }

    public void setCommentsDisabled(Boolean commentsDisabled) {
        this.commentsDisabled = commentsDisabled;
    }

    public Integer getTakenAtTimestamp() {
        return takenAtTimestamp;
    }

    public void setTakenAtTimestamp(Integer takenAtTimestamp) {
        this.takenAtTimestamp = takenAtTimestamp;
    }

    public EdgeLikedBy getEdgeLikedBy() {
        return edgeLikedBy;
    }

    public void setEdgeLikedBy(EdgeLikedBy edgeLikedBy) {
        this.edgeLikedBy = edgeLikedBy;
    }

    public EdgeMediaPreviewLike getEdgeMediaPreviewLike() {
        return edgeMediaPreviewLike;
    }

    public void setEdgeMediaPreviewLike(EdgeMediaPreviewLike edgeMediaPreviewLike) {
        this.edgeMediaPreviewLike = edgeMediaPreviewLike;
    }

    public Object getLocation() {
        return location;
    }

    public void setLocation(Object location) {
        this.location = location;
    }

    public String getThumbnailSrc() {
        return thumbnailSrc;
    }

    public void setThumbnailSrc(String thumbnailSrc) {
        this.thumbnailSrc = thumbnailSrc;
    }

    public List<ThumbnailResource> getThumbnailResources() {
        return thumbnailResources;
    }

    public void setThumbnailResources(List<ThumbnailResource> thumbnailResources) {
        this.thumbnailResources = thumbnailResources;
    }

    public DashInfo getDashInfo() {
        return dashInfo;
    }

    public void setDashInfo(DashInfo dashInfo) {
        this.dashInfo = dashInfo;
    }

    public Boolean getHasAudio() {
        return hasAudio;
    }

    public void setHasAudio(Boolean hasAudio) {
        this.hasAudio = hasAudio;
    }

    public String getTrackingToken() {
        return trackingToken;
    }

    public void setTrackingToken(String trackingToken) {
        this.trackingToken = trackingToken;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Integer getVideoViewCount() {
        return videoViewCount;
    }

    public void setVideoViewCount(Integer videoViewCount) {
        this.videoViewCount = videoViewCount;
    }

    public Object getFelixProfileGridCrop() {
        return felixProfileGridCrop;
    }

    public void setFelixProfileGridCrop(Object felixProfileGridCrop) {
        this.felixProfileGridCrop = felixProfileGridCrop;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Object getClipsMusicAttributionInfo() {
        return clipsMusicAttributionInfo;
    }

    public void setClipsMusicAttributionInfo(Object clipsMusicAttributionInfo) {
        this.clipsMusicAttributionInfo = clipsMusicAttributionInfo;
    }

}
