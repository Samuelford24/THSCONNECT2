package com.samuelford48gmail.thsconnect.teacher;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.samuelford48gmail.thsconnect.Class_model;
import com.samuelford48gmail.thsconnect.R;
import com.samuelford48gmail.thsconnect.UtilMethods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class admin_create_class extends AppCompatActivity {
    private static final String TAG = "admin_create_class";
    private EditText subject1, ClassName, teacher_name1, room_number1, date1;
    private Button submit;

    String classKey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_class);
        date1 = findViewById(R.id.editTextDate);
        subject1 = findViewById(R.id.subject);
        ClassName = findViewById(R.id.Date_class);
        teacher_name1 = findViewById(R.id.Teacher);
        room_number1 = findViewById(R.id.room_number);
        submit = findViewById(R.id.create_class);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = subject1.getText().toString().trim();
                String class_name = ClassName.getText().toString().trim();
                String teacher_name = teacher_name1.getText().toString().trim();
                String room_number = room_number1.getText().toString().trim();
                String date = date1.getText().toString().trim();
                if (checkDatesandConvertToArray(date) != null) {
                    //   if (UtilMethods.isDateValid(date)) {
                    if (subject.equals("Science") || subject.equals("Technology") || subject.equals("Math") || subject.equals("Social Studies") || subject.equals("English") || subject.equals("Other") || subject.equals("Music") || subject.equals("Art")) {
                        classKey = FirebaseFirestore.getInstance().collection("Classes").document().getId();

                        final Class_model new_class = new Class_model(class_name, teacher_name, room_number, classKey, subject, new ArrayList<>(Arrays.asList(checkDatesandConvertToArray(date))), date);
//String key2 = FirebaseFirestore.getInstance().collection("Classes").getId();
                        FirebaseFirestore.getInstance().collection("Classes").document(classKey).set(new_class).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()) {
                                    AlertDialog.Builder builder;
                                    builder = new AlertDialog.Builder(admin_create_class.this);
                                    //builder.setIcon(R.drawable.open_browser);
                                    builder.setTitle("      Class Created!");
                                    addClassToTeacher(new_class);
                                    builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.dismiss();
                                            finish();
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
                /*} else {
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
                }*/
                } else {
                }
            }
        });
    }

    private void addClassToTeacher(Class_model class_model) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", classKey);
        map.put("timestamp", FieldValue.serverTimestamp());
        FirebaseFirestore.getInstance().collection("Users").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).collection("Classes").document(classKey).set(map);
    }

    private String[] checkDatesandConvertToArray(String date) {
        String[] dates = date.split("/");
        for (String s : dates) {

            if (UtilMethods.isDateValid(s) && s.length()<=5) {

            } else {
                AlertDialog.Builder builder;
                builder = new AlertDialog.Builder(admin_create_class.this);
                //builder.setIcon(R.drawable.open_browser);
                builder.setTitle("Date " + s + " is invalid");
                builder.setMessage("Please fix the date and try again. All dates must be separated by a /. An example of a correct entry is 08-09/09-10 ");
                builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                builder.setCancelable(true);
                builder.show();
                return null;
            }
        }
        return dates;
    }


}
