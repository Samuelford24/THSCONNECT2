package com.samuelford48gmail.thsconnect;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class admin_add_class_to_user extends AppCompatActivity {
    private EditText date_class1, teacher_name1, room_number1;
    private Button submit;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_class_to_user);
        date_class1 = (EditText) findViewById(R.id.Date_class);
        teacher_name1 = (EditText) findViewById(R.id.Teacher);
        room_number1 = (EditText) findViewById(R.id.room_number);
        submit = (Button) findViewById(R.id.create_class);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Classes");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String subject = subject1.getText().toString().trim();
                String date_class = date_class1.getText().toString().trim();
                String teacher_name = teacher_name1.getText().toString().trim();
                String room_number = room_number1.getText().toString().trim();
                Class_model_without_uid new_class = new Class_model_without_uid(date_class, teacher_name, room_number);
                String key = myRef.push().getKey();
                myRef.child(key).setValue(new_class);
            }
        });
    }
}
