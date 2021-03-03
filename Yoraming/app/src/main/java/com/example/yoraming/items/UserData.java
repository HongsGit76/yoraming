package com.example.yoraming.items;

public class UserData {
    private String userEmail;
    private String userName;
    private String uid;

    public UserData(String userEmail, String userName, String uid) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.uid = uid;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
