package com.example.yoraming.items;

public class SpecCareerData {
    private String career_content;

    private String career_date;

    private String career_office;

    private String user_id;
    public SpecCareerData(String user_id, String career_content, String career_office, String career_date) {
        this.user_id = user_id;
        this.career_content = career_content;
        this.career_date = career_date;
        this.career_office = career_office;
    }

    public String getCareer_content ()
    {
        return career_content;
    }

    public void setCareer_content (String career_content)
    {
        this.career_content = career_content;
    }

    public String getCareer_date ()
    {
        return career_date;
    }

    public void setCareer_date (String career_date)
    {
        this.career_date = career_date;
    }

    public String getCareer_office ()
    {
        return career_office;
    }

    public void setCareer_office (String career_office)
    {
        this.career_office = career_office;
    }

    public String getUser_id ()
    {
        return user_id;
    }

    public void setUser_id (String user_id)
    {
        this.user_id = user_id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [career_content = "+career_content+", career_date = "+career_date+", career_office = "+career_office+", user_id = "+user_id+"]";
    }
}
