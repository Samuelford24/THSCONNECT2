package com.samuelford48gmail.thsconnect;

public class User {


    public String name;
    public String email;
    public String grade;
    public String uid;

    public String studentID;

     public User(){

    }
    public User(String name, String email, String grade, String uid, String studentID) {
        this.name = name;
        this.email = email;
        this.grade = grade;
        this.uid = uid;
        this.studentID = studentID;

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getStudentID() {
        return studentID;
    }
}
