package com.example.yoraming.items;

public class SpecEtcData {
    private String etc_content;

    private String user_id;

    private String etc_date;

    private String etc_title;

    public SpecEtcData(String user_id, String etc_title, String etc_content, String etc_date) {
        this.user_id = user_id;
        this.etc_title = etc_title;
        this.etc_content = etc_content;
        this.etc_date = etc_date;
    }

    public String getEtc_content ()
    {
        return etc_content;
    }

    public void setEtc_content (String etc_content)
    {
        this.etc_content = etc_content;
    }

    public String getUser_id ()
    {
        return user_id;
    }

    public void setUser_id (String user_id)
    {
        this.user_id = user_id;
    }

    public String getEtc_date ()
    {
        return etc_date;
    }

    public void setEtc_date (String etc_date)
    {
        this.etc_date = etc_date;
    }

    public String getEtc_title ()
    {
        return etc_title;
    }

    public void setEtc_title (String etc_title)
    {
        this.etc_title = etc_title;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [etc_content = "+etc_content+", user_id = "+user_id+", etc_date = "+etc_date+", etc_title = "+etc_title+"]";
    }
}
