package com.samuelford48gmail.thsconnect;

import android.content.DialogInterface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class admin_create_class extends AppCompatActivity {
    private EditText subject1, ClassName, teacher_name1, room_number1, date1;
    private Button submit;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_class);
        date1 = findViewById(R.id.date);
        subject1 = findViewById(R.id.subject);
        ClassName = findViewById(R.id.Date_class);
        teacher_name1 = findViewById(R.id.Teacher);
        room_number1 = findViewById(R.id.room_number);
        submit = findViewById(R.id.create_class);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Classes");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = subject1.getText().toString().trim();
                String class_name = ClassName.getText().toString().trim();
                String teacher_name = teacher_name1.getText().toString().trim();
                String room_number = room_number1.getText().toString().trim();
                String date = date1.getText().toString().trim();
                if (UtilMethods.isDateValid(date)) {
                    if (subject.equals("Science") || subject.equals("Technology") || subject.equals("Math") || subject.equals("Social Studies") || subject.equals("English") || subject.equals("Other") || subject.equals("Music") || subject.equals("Art")) {
                        String key = myRef.push().getKey();
                        Class_model new_class = new Class_model(class_name, teacher_name, room_number, key, subject);

                        myRef.child(key).child("class_info").setValue(new_class).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    AlertDialog.Builder builder;
                                    builder = new AlertDialog.Builder(admin_create_class.this);
                                    //builder.setIcon(R.drawable.open_browser);
                                    builder.setTitle("      Class Created!");
                                    builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.dismiss();

                                        }
                                    });
                                    builder.setCancelable(true);
                                    builder.show();
                                } else {
                                    AlertDialog.Builder builder;
                                    builder = new AlertDialog.Builder(admin_create_class.this);
                                    //builder.setIcon(R.drawable.open_browser);
                                    builder.setTitle("Error Creating Class!");
                                    builder.setMessage("Please check your wifi/data connection and try again");
                                    builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.dismiss();
                                        }
                                    });
                                    builder.setCancelable(true);
                                    builder.show();
                                }
                            }


                        });


                    } else {
                        AlertDialog.Builder builder;
                        builder = new AlertDialog.Builder(admin_create_class.this);
                        //builder.setIcon(R.drawable.open_browser);
                        builder.setTitle("Wrong Subject!");
                        builder.setMessage("Please make sure you have entered the correct subject");
                        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
                        builder.setCancelable(true);
                        builder.show();
                    }

                    //myRef = database.getReference(subject);
        /*
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
*/
                } else {
                    AlertDialog.Builder builder;
                    builder = new AlertDialog.Builder(admin_create_class.this);
                    //builder.setIcon(R.drawable.open_browser);
                    builder.setTitle("Date is not valid");
                    builder.setMessage("Please fix the date and try again");
                    builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
                    builder.setCancelable(true);
                    builder.show();
                }
            }
        });
    }


}
