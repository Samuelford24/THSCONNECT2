package com.samuelford48gmail.thsconnect;

//non-parceable replica of class_model
class Class_model2 {

    String teacher;
    String room_number;
    //String subject;
    String classname;
    String subject;
    String id;
    //  String date;

    public Class_model2() {
    }

    public Class_model2(String classname, String teacher, String room_number, String id, String subject) {
        //subject = subject2;
        this.classname = classname;
        this.teacher = teacher;
        this.room_number = room_number;
        this.id = id;
        this.subject = subject;
        // this.date = date;
    }


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDate_clasname() {
        return classname;
    }

    public void setDate_clasname(String date_clasname) {
        this.classname = date_clasname;
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

    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }


}
