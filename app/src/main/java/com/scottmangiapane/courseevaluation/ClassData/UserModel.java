package com.scottmangiapane.courseevaluation.ClassData;


import androidx.annotation.NonNull;

import com.alibaba.fastjson.JSON;

public class UserModel {
    private String userID;
    private String password;
    private String nickname;
    private int schoolID;
    private int majorID;

    public UserModel(String userID, String password, String nickname, int schoolID, int majorID) {
        this.userID = userID;
        this.password = password;
        this.nickname = nickname;
        this.schoolID = schoolID;
        this.majorID = majorID;
    }

    public UserModel(String userID, String password) {
        this.userID = userID;
        this.password = password;
    }

    public UserModel() {
    }

    public int getMajorID() {
        return majorID;
    }

    public void setMajorID(int majorID) {
        this.majorID = majorID;
    }

    public int getSchoolID() {
        return schoolID;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @NonNull
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
