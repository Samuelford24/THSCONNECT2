package com.samuelford48gmail.thsconnect;

public class Listdata {

    String date_class;
     String teacher;
     String rnumber;
     String uid;



    public Listdata(String date_class, String teacher, String rnumber, String uid) {
        this.date_class = date_class;
        this.teacher = teacher;
        this.rnumber = rnumber;
        this.uid = uid;
    }

    public String getDate_class() {
        return date_class;
    }

    public void setDate_class(String date_class) {
        this.date_class = date_class;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getRnumber() {
        return rnumber;
    }

    public void setRnumber(String rnumber) {
        this.rnumber = rnumber;
    }
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}

