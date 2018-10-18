package com.example.hp.studentfees;

public class DedLine {
    String id;
    String checkDate;
    String MobileNo;

    public DedLine(String id, String checkDate, String mobileNo) {
        this.id = id;
        this.checkDate = checkDate;
        MobileNo = mobileNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }
}
