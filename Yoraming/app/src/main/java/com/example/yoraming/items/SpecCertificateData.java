package com.example.yoraming.items;

public class SpecCertificateData {
    private String certificate_date;

    private String user_id;

    private String certificate_endDate;

    private String certificate_name;

    public SpecCertificateData(String user_id, String certificate_name, String certificate_date, String certificate_endDate) {
        this.user_id = user_id;
        this.certificate_name = certificate_name;
        this.certificate_date = certificate_date;
        this.certificate_endDate = certificate_endDate;
    }

    public String getCertificate_date ()
    {
        return certificate_date;
    }

    public void setCertificate_date (String certificate_date)
    {
        this.certificate_date = certificate_date;
    }

    public String getUser_id ()
    {
        return user_id;
    }

    public void setUser_id (String user_id)
    {
        this.user_id = user_id;
    }

    public String getCertificate_endDate ()
    {
        return certificate_endDate;
    }

    public void setCertificate_endDate (String certificate_endDate)
    {
        this.certificate_endDate = certificate_endDate;
    }

    public String getCertificate_name ()
    {
        return certificate_name;
    }

    public void setCertificate_name (String certificate_name)
    {
        this.certificate_name = certificate_name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [certificate_date = "+certificate_date+", user_id = "+user_id+", certificate_endDate = "+certificate_endDate+", certificate_name = "+certificate_name+"]";
    }
}
