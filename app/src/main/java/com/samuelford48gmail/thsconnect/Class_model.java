package com.samuelford48gmail.thsconnect;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.health.TimerStat;

import java.sql.Timestamp;
/*
public class Class_model {

    String teacher;
    String room_number;
    //String subject;
    String classname;
    String subject;
    String id;
  //  String date;

    public Class_model() {
    }

    public Class_model(String classname, String teacher, String room_number, String id, String subject) {
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
*/


public class Class_model implements Parcelable {
    public static final Creator<Class_model> CREATOR = new Creator<Class_model>() {
        @Override
        public Class_model createFromParcel(Parcel in) {
            return new Class_model(in);
        }

        @Override
        public Class_model[] newArray(int size) {
            return new Class_model[size];
        }
    };
    String teacher;
    String room_number;
    //String subject;
    String classname;
    String subject;
    String id;
    String date;

    public Class_model() {
    }

    public Class_model(String classname, String teacher, String room_number, String id, String subject) {
        //subject = subject2;
        this.classname = classname;
        this.teacher = teacher;
        this.room_number = room_number;
        this.id = id;
        this.subject = subject;

    }

    //constructor used for parcel
    public Class_model(Parcel parcel) {
        classname = parcel.readString();
        id = parcel.readString();
        teacher = parcel.readString();
        room_number = parcel.readString();
        subject = parcel.readString();
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    public void writeToParcel(Parcel dest, int flags) {
        //write all properties to the parcle
        dest.writeString(classname);
        dest.writeString(teacher);
        dest.writeString(room_number);
        dest.writeString(id);
        dest.writeString(subject);
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

    public void setid(String iid) {
        this.id = id;
    }


}

