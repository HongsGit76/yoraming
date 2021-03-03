package com.example.yoraming;

public class Subject {
    private String majorName;
    private int major;
    private int basic;

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public void setMajor(int major) {
        this.major = major;
    }

    public void setBasic(int basic) {
        this.basic = basic;
    }

    public String getMajorName() {
        return majorName;
    }

    public int getMajor() {
        return major;
    }

    public int getBasic() {
        return basic;
    }

    public int getTotal() {
        return getMajor() + getBasic();
    }
}
