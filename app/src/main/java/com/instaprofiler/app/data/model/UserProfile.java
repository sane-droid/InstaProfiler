package com.instaprofiler.app.data.model;

import android.system.StructTimespec;

public class UserProfile {
    //TODO class to store details of instagram user

    private String userId;
    private String followers;
    private String following;
    private String posts;
    private String profilePic;

    //GETTERS
    public String getUserId() {
        return userId;
    }
    public String getFollowers(){
        return followers;
    }
    public String getFollowing(){
        return following;
    }
    public String getPosts(){
        return posts;
    }
    public String getProfilePic(){
        return profilePic;
    }

    //SETTER

    public void setUserId(String newUserId){
        this.userId = newUserId;
    }
    public void setFollowers(String newFollowers){
        this.followers = newFollowers;
    }
    public void setFollowing(String newFollowing){
        this.following = newFollowing;
    }
    public void setPosts(String newPosts){
        this.posts = newPosts;
    }
    public void setProfilePic(String newProfilePic){
        this.profilePic = newProfilePic;
    }

}

