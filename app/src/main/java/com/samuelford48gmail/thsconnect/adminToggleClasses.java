package com.samuelford48gmail.thsconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class adminToggleClasses extends AppCompatActivity {
    TextView tx;
    Button b1, b2;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_toggle_classes);
        tx = findViewById(R.id.openClpsed);
        b1 = findViewById(R.id.CloseClasses);
        b2 = findViewById(R.id.OpenClasses);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("Toggle_Classes");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String condition = (String) dataSnapshot.getValue();
                tx.setText(condition);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRef.setValue("Classes Closed");

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRef.setValue("Classes are open");
            }
        });
    }
}
