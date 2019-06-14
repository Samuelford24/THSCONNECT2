package com.samuelford48gmail.thsconnect;

public class Listdata {

    public String date_class;
    public String teacher;
    public String rnumber;

    public Listdata(String date_class, String teacher, String rnumber) {
        this.date_class = date_class;
        this.teacher = teacher;
        this.rnumber = rnumber;
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
}

