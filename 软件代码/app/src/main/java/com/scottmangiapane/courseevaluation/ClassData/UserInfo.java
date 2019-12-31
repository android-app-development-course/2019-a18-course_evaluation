package com.scottmangiapane.courseevaluation.ClassData;

public class UserInfo {
    private Integer userID;//唯一标识ID
    //账号名和密码用于登录
    private String account;//账号名
    private String password;//密码
    private String userName;//昵称
    private int schoolID;//学校
    private int majorID;//专业

    public UserInfo(Integer userID,String account,String password,String userName,int schoolID,int majorID){
        this.userID=userID;
        this.account=account;
        this.password=password;
        this.userName=userName;
        this.schoolID=schoolID;
        this.majorID=majorID;
    }

    public Integer getUserID(){
        return userID;
    }

    public String getAccount(){
        return account;
    }

    public String getUserName(){
        return userName;
    }

    public String getPassword(){
        return password;
    }

    public int getSchoolID(){
        return schoolID;
    }

    public int getMajorID(){
        return majorID;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public void setUserName(String userName){
        this.userName=userName;
    }

    public void setSchoolID(int schoolID){
        this.schoolID=schoolID;
    }

    public void setMajorID(int majorID){
        this.majorID=majorID;
    }

}
