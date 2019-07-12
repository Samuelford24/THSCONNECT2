package com.samuelford48gmail.thsconnect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class admin_user_remove_class extends AppCompatActivity {
    private Button remove_class;
    private FirebaseDatabase database;
    private DatabaseReference myRef, myRef2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_remove_class);
        final String date_class2 = getIntent().getStringExtra("date_class");
        final String teacher = getIntent().getStringExtra("teacher");
        final String room_number = getIntent().getStringExtra("room_number");
        final String post_key = getIntent().getStringExtra("post_key");
        TextView display_class_name = (TextView) findViewById(R.id.date_tv);
        display_class_name.setText(date_class2);
        TextView display_teacher = (TextView) findViewById(R.id.teacher_tv);
        display_teacher.setText(teacher);
        TextView display_room_number = (TextView) findViewById(R.id.rn_tv);
        display_room_number.setText(room_number);
        database = FirebaseDatabase.getInstance();
        //myRef = database.getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Classes");

        remove_class = (Button) findViewById(R.id.add_class_2);
      //  final Query query = myRef.orderByChild("classes").equalTo(post_key);
        //System.out.println(query);
        remove_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String key = FirebaseAuth.getInstance().getCurrentUser().getUid();
                myRef = database.getReference("Classes").child(post_key).child("Students").child(key);
                System.out.println(myRef);
                myRef.removeValue();
                remove_class_from_student();
            }
        });
    }
    public void remove_class_from_student(){
        String key = FirebaseAuth.getInstance().getCurrentUser().getUid();
        final String post_key = getIntent().getStringExtra("post_key");
        myRef = database.getReference("Users").child(key).child("Classes").child(post_key);
        myRef.removeValue();
    }
}