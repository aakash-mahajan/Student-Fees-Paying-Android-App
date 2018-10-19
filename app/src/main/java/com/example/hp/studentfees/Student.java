package com.example.hp.studentfees;

public class Student {
    String id;
    String Name;
    String Tution_Fees;
    String STP_Fees;
    String ExtraActivity_Fees;
    String Hostel_Fees;
    String Total_Fees;



    public Student(){
    }

    public Student(String id,String Name,String Tution_Fees,String STP_Fees,String ExtraActivity_Fees,String Hostel_Fees,String Total_Fees){
        this.id = id;
        this.Tution_Fees = Tution_Fees;
        this.STP_Fees = STP_Fees;
        this.ExtraActivity_Fees = ExtraActivity_Fees;
        this.Hostel_Fees = Hostel_Fees;
        this.Total_Fees = Total_Fees;
        this.Name = Name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getId() {
        return id;
    }

    public String getTotal_Fees() {
        return Total_Fees;
    }

    public void setTotal_Fees(String total_Fees) {
        Total_Fees = total_Fees;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTution_Fees() {
        return Tution_Fees;
    }

    public void setTution_Fees(String tution_Fees) {
        Tution_Fees = tution_Fees;
    }

    public String getSTP_Fees() {
        return STP_Fees;
    }

    public void setSTP_Fees(String STP_Fees) {
        this.STP_Fees = STP_Fees;
    }

    public String getExtraActivity_Fees() {
        return ExtraActivity_Fees;
    }

    public void setExtraActivity_Fees(String extraActivity_Fees) {
        ExtraActivity_Fees = extraActivity_Fees;
    }

    public String getHostel_Fees() {
        return Hostel_Fees;
    }

    public void setHostel_Fees(String hostel_Fees) {
        Hostel_Fees = hostel_Fees;
    }
}
