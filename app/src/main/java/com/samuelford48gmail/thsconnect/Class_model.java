package com.samuelford48gmail.thsconnect;

public class Class_model {
    //String subject;
    String date_clasname;
    String  teacher;
    String room_number;
    String uid;
    public Class_model(){};
    public Class_model(String date_classname, String teacher, String room_number, String uid){
        //subject = subject2;
        this.date_clasname = date_classname;
        this.teacher = teacher;
        this.room_number = room_number;
        this.uid = uid;

    }
    /*public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
*/
    public String getDate_clasname() {
        return date_clasname;
    }

    public void setDate_clasname(String date_clasname) {
        this.date_clasname = date_clasname;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getRoom_number() {
        return room_number;
    }

    public void setRoom_number(String room_number) {
        this.room_number = room_number;
    }
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }



}


