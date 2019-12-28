package com.samuelford48gmail.thsconnect;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class your_info extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_info);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("User_info/name").getValue(String.class);

                String grade = dataSnapshot.child("User_info/grade").getValue(String.class);

                String studentid = dataSnapshot.child("User_info/studentID").getValue(String.class);

                String email = dataSnapshot.child("User_info/email").getValue(String.class);
                TextView user_name = (TextView) findViewById(R.id.name);
                user_name.setText("Name: " + name);
                TextView user_grade = (TextView) findViewById(R.id.grade);
                user_grade.setText("Grade: " + grade);
                TextView user_email = (TextView) findViewById(R.id.email);
                user_email.setText("Email: " + email);
TextView user_id = findViewById(R.id.studentID);
user_id.setText("ID: " + studentid);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
