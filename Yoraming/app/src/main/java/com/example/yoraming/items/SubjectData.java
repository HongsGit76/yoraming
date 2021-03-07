package com.example.yoraming.items;

public class SubjectData {
    private String user_id;

    private String yoram_id;

    public SubjectData(String user_id, String yoram_id) {
        this.user_id = user_id;
        this.yoram_id = yoram_id;
    }

    public String getUser_id ()
    {
        return user_id;
    }

    public void setUser_id (String user_id)
    {
        this.user_id = user_id;
    }

    public String getYoram_id ()
    {
        return yoram_id;
    }

    public void setYoram_id (String yoram_id)
    {
        this.yoram_id = yoram_id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [user_id = "+user_id+", yoram_id = "+yoram_id+"]";
    }
}
