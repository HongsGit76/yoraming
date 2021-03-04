package com.example.yoraming.items;

public class YoramData {
    private String yoram_id;

    private String yoram_basicR;

    private String user_id;

    private String yoram_univR;

    private String yoram_total;

    private String yoram_majorR;

    private String yoram_majorS;

    private String yoram_major;

    public YoramData(String user_id, String yoram_id, String yoram_major, String yoram_total, String yoram_majorR, String yoram_majorS, String yoram_univR, String yoram_basicR){
        this.user_id = user_id;
        this.yoram_id = yoram_id;
        this.yoram_major = yoram_major;
        this.yoram_total = yoram_total;
        this.yoram_majorR = yoram_majorR;
        this.yoram_majorS = yoram_majorS;
        this.yoram_univR = yoram_univR;
        this.yoram_basicR =yoram_basicR;
    }

    public String getYoram_id ()
    {
        return yoram_id;
    }

    public void setYoram_id (String yoram_id)
    {
        this.yoram_id = yoram_id;
    }

    public String getYoram_basicR ()
    {
        return yoram_basicR;
    }

    public void setYoram_basicR (String yoram_basicR)
    {
        this.yoram_basicR = yoram_basicR;
    }

    public String getUser_id ()
    {
        return user_id;
    }

    public void setUser_id (String user_id)
    {
        this.user_id = user_id;
    }

    public String getYoram_univR ()
    {
        return yoram_univR;
    }

    public void setYoram_univR (String yoram_univR)
    {
        this.yoram_univR = yoram_univR;
    }

    public String getYoram_total ()
    {
        return yoram_total;
    }

    public void setYoram_total (String yoram_total)
    {
        this.yoram_total = yoram_total;
    }

    public String getYoram_majorR ()
    {
        return yoram_majorR;
    }

    public void setYoram_majorR (String yoram_majorR)
    {
        this.yoram_majorR = yoram_majorR;
    }

    public String getYoram_majorS ()
    {
        return yoram_majorS;
    }

    public void setYoram_majorS (String yoram_majorS)
    {
        this.yoram_majorS = yoram_majorS;
    }

    public String getYoram_major ()
    {
        return yoram_major;
    }

    public void setYoram_major (String yoram_major)
    {
        this.yoram_major = yoram_major;
    }

    @Override
    public String toString()
    {
        return "toramData [yoram_basicR = "+yoram_basicR+", user_id = "+user_id+", yoram_univR = "+yoram_univR+", yoram_total = "+yoram_total+", yoram_majorR = "+yoram_majorR+", yoram_majorS = "+yoram_majorS+", yoram_major = "+yoram_major+"]";
    }
}
