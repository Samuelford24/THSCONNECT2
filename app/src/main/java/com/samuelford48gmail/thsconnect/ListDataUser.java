package com.samuelford48gmail.thsconnect;

public class ListDataUser {
    public String studentname;
    public String grade;
    public String studnetID;
    public String uid;


    ListDataUser(String studentname, String grade, String studnetID, String uid) {
        this.studentname = studentname;
        this.grade = grade;
        this.studnetID = studnetID;
        this.uid = uid;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getStudnetID() {
        return studnetID;
    }

    public void setStudnetID(String studnetID) {
        this.studnetID = studnetID;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

}
