package com.example.yoraming.items;

public class NoticeData {
    private String user_id;

    private String notice_keyword;

    public NoticeData(String user_id , String notice_keyword){
        this.user_id = user_id;
        this.notice_keyword = notice_keyword;
    }

    public String getUser_id ()
    {
        return user_id;
    }

    public void setUser_id (String user_id)
    {
        this.user_id = user_id;
    }

    public String getNotice_keyword ()
    {
        return notice_keyword;
    }

    public void setNotice_keyword (String notice_keyword)
    {
        this.notice_keyword = notice_keyword;
    }

    @Override
    public String toString()
    {
        return "noticeData [user_id = "+user_id+", notice_keyword = "+notice_keyword+"]";
    }
}

