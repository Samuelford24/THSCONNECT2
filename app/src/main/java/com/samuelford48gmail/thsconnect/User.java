package com.samuelford48gmail.thsconnect;

public class User {


    public String name, email, grade;

     public User(){

    }

    public User(String name, String email, String grade) {
        this.name = name;
        this.email = email;
        this.grade = grade;
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
}
