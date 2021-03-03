package com.example.yoraming;

import java.util.ArrayList;

public class UserDataHandler {
    private static UserDataHandler myUserDataHandler = null;
    public ArrayList<Subject> subjectList = new ArrayList<Subject>();

    public static UserDataHandler getInstance() {

        if(myUserDataHandler == null) {
            myUserDataHandler = new UserDataHandler();
        }

        return myUserDataHandler;
    }
}
