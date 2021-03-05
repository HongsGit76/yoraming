package com.example.yoraming.items;

public class UserData {
    private String user_id;
    private String user_name;
    private String uid;

    public UserData(String userEmail, String userName, String uid) {
        this.user_id = userEmail;
        this.user_name = userName;
        this.uid = uid;
    }

    public String getUserEmail() {
        return user_id;
    }

    public void setUserEmail(String userEmail) {
        this.user_id = userEmail;
    }

    public String getUserName() {
        return user_name;
    }

    public void setUserName(String userName) {
        this.user_name = userName;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
