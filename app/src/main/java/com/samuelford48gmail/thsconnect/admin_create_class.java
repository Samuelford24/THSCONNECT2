package com.samuelford48gmail.thsconnect;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class admin_create_class extends AppCompatActivity {
private EditText subject1, date_class1, teacher_name1, room_number1;
private Button submit;
private FirebaseDatabase database;
private DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_class);
    subject1 = (EditText) findViewById(R.id.subject);
    date_class1 = (EditText) findViewById(R.id.Date_class);
    teacher_name1 = (EditText) findViewById(R.id.Teacher);
    room_number1 = (EditText) findViewById(R.id.room_number);
    submit = (Button) findViewById(R.id.create_class);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
    submit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String subject = subject1.getText().toString().trim();
            String date_class = date_class1.getText().toString().trim();
            String teacher_name = teacher_name1.getText().toString().trim();
            String room_number = room_number1.getText().toString().trim();
            String key = myRef.push().getKey();
            Class_model new_class = new Class_model(date_class, teacher_name, room_number, key);
            //myRef = database.getReference(subject);
            //String key = myRef.push().getKey();
            if (subject.equals("Math")) {
                myRef = database.getReference(subject);
               // String key2 = myRef.push().getKey();
                myRef.child(key).setValue(new_class);
            } else if (subject.equals("English")) {
                myRef = database.getReference(subject);
                //String key2 = myRef.push().getKey();
                myRef.child(key).setValue(new_class);
            } else if (subject.equals("Science")) {
                myRef = database.getReference(subject);
                //String key2 = myRef.push().getKey();
                myRef.child(key).setValue(new_class);
            } else if (subject.equals("Social Studies")) {
                myRef = database.getReference(subject);
                //String key2 = myRef.push().getKey();
                myRef.child(key).setValue(new_class);
            } else if (subject.equals("Other")) {
                myRef = database.getReference(subject);
                //String key2 = myRef.push().getKey();
                myRef.child(key).setValue(new_class);
            } else if (subject.equals("Technology")) {
                myRef = database.getReference(subject);
                //String key2 = myRef.push().getKey();
                myRef.child(key).setValue(new_class);
            } else {
                AlertDialog alertDialog = new AlertDialog.Builder(admin_create_class.this).create();
                alertDialog.setTitle("Error");
                alertDialog.setMessage("The subject must be Math,English, Science, Other, Social Studies, or Technology");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        }
    });
    }

}
