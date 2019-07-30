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
                Log.d("setting", "name" + name);
                String grade = dataSnapshot.child("User_info/grade").getValue(String.class);
                Log.d("setting", "grade" + grade);
                String email = dataSnapshot.child("User_info/email").getValue(String.class);
                Log.d("setting", "email" + email);
                TextView user_name = (TextView) findViewById(R.id.date_tv);
                user_name.setText(name);
                TextView user_grade = (TextView) findViewById(R.id.teacher_tv);
                user_grade.setText(grade);
                TextView user_email = (TextView) findViewById(R.id.rn_tv);
                user_email.setText(email);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
