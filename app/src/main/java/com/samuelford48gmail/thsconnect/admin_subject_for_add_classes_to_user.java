package com.samuelford48gmail.thsconnect;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class admin_subject_for_add_classes_to_user extends AppCompatActivity implements View.OnClickListener{

    private Button btn, btn2, btn3, btn4, btn5, btn6;
    private String class_name;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_subject_for_add_classes_to_user);
        final String student_name = getIntent().getStringExtra("studentname");
        System.out.println(student_name);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");

        myRef.addChildEventListener(new ChildEventListener() {

            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String student = (String) dataSnapshot.child("User_info/name").getValue();
                if (student.equals(student_name)) {
                    Log.d("admin", "Found student");
                    String user_uid = (String) dataSnapshot.child("User_info/uid").getValue();
                    SharedPreferences mySharedPreferences = getApplicationContext().getSharedPreferences("user_uid2", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = mySharedPreferences.edit();
                    editor.putString("Uid2", user_uid);
                    editor.apply();
                    Log.d("admin", "Got uid" + user_uid);
                }
            }

                @Override
                public void onChildChanged (@NonNull DataSnapshot dataSnapshot, @Nullable String s){

                }

                @Override
                public void onChildRemoved (@NonNull DataSnapshot dataSnapshot){

                }

                @Override
                public void onChildMoved (@NonNull DataSnapshot dataSnapshot, @Nullable String s){

                }

                @Override
                public void onCancelled (@NonNull DatabaseError databaseError){

                }
            });
        btn = (Button) findViewById(R.id.Science);
        btn2 = (Button) findViewById(R.id.mat);
        btn3 = (Button) findViewById(R.id.eng);
        btn4 = (Button) findViewById(R.id.ss);
        btn5 = (Button) findViewById(R.id.oth);
        btn6 = (Button) findViewById(R.id.tech);
        btn.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.Science:
                i = new Intent(this, admin_add_class_to_user.class);
                class_name = "Science";
                i.putExtra("class_type", class_name);
                startActivity(i);break;
            case R.id.mat: i = new Intent(this, admin_add_class_to_user.class);
                class_name = "Math";
                i.putExtra("class_type", class_name);
                startActivity(i);break;
            case R.id.ss: i = new Intent(this, admin_add_class_to_user.class);
                class_name = "Social Studies";
                i.putExtra("class_type", class_name);
                startActivity(i);break;
            case R.id.eng: i = new Intent(this, admin_add_class_to_user.class);
                class_name = "English";
                i.putExtra("class_type", class_name);
                startActivity(i);break;
            case R.id.tech: i = new Intent(this,admin_add_class_to_user.class);
                class_name = "Technology";
                i.putExtra("class_type", class_name);startActivity(i);break;
            case R.id.oth: i = new Intent(this, admin_add_class_to_user.class);
                class_name = "Other";
                i.putExtra("class_type", class_name);
                startActivity(i);break;
            default:break;
        }
    }
}

