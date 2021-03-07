package com.example.yoraming.items;

public class SpecLanguageData {
    private String language_date;

    private String user_id;

    private String language_grade;

    private String language_name;

    public SpecLanguageData(String user_id, String language_name, String language_grade, String language_date) {
        this.user_id = user_id;
        this.language_name = language_name;
        this.language_grade = language_grade;
        this.language_date = language_date;
    }

    public String getLanguage_date ()
    {
        return language_date;
    }

    public void setLanguage_date (String language_date)
    {
        this.language_date = language_date;
    }

    public String getUser_id ()
    {
        return user_id;
    }

    public void setUser_id (String user_id)
    {
        this.user_id = user_id;
    }

    public String getLanguage_grade ()
    {
        return language_grade;
    }

    public void setLanguage_grade (String language_grade)
    {
        this.language_grade = language_grade;
    }

    public String getLanguage_name ()
    {
        return language_name;
    }

    public void setLanguage_name (String language_name)
    {
        this.language_name = language_name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [language_date = "+language_date+", user_id = "+user_id+", language_grade = "+language_grade+", language_name = "+language_name+"]";
    }
}
