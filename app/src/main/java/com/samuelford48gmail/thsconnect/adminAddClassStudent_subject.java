package com.samuelford48gmail.thsconnect;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;


public class adminAddClassStudent_subject extends AppCompatActivity implements View.OnClickListener {

    private Button btn, btn2, btn3, btn4, btn5, btn6;
    private String class_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_subject_for_add_classes_to_user);
        final String studentid = getIntent().getStringExtra("studentid");
        System.out.println(studentid);

        FirebaseFirestore.getInstance().collection("Users").whereEqualTo("studentID", studentid).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error == null) {
                    if (!value.isEmpty()) {
                        for (DocumentSnapshot documentSnapshot : value) {
                            if (documentSnapshot.getId().equals(studentid)) {
                                String user_uid = documentSnapshot.getId();
                                SharedPreferences mySharedPreferences = getApplicationContext().getSharedPreferences("user_uid2", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = mySharedPreferences.edit();
                                editor.putString("Uid2", user_uid);
                                editor.apply();
                                //  getUsersClasses(documentSnapshot.getId());
                                break;
                            }
                        }
                    } else {
                        studentNotFound(studentid);
                    }
                }
            }
        });


        // }


        btn = findViewById(R.id.Science);
        btn2 = findViewById(R.id.mat);
        btn3 = findViewById(R.id.eng);
        btn4 = findViewById(R.id.ss);
        btn5 = findViewById(R.id.oth);
        btn6 = findViewById(R.id.tech);
        btn.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
    }
    public void studentFound(){
        Log.d("adminAddClassStudent","student Found");
        final AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(adminAddClassStudent_subject.this);
        //builder.setIcon(R.drawable.open_browser);
        builder.setTitle("Student Found");
        builder.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });

        builder.setCancelable(true);
        builder.show();
    }
    public void studentNotFound(String studentid){
        Log.d("adminAddClassStudent","student not found");
        final AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(adminAddClassStudent_subject.this);
        //builder.setIcon(R.drawable.open_browser);
        builder.setTitle("Student Not Found");
        builder.setMessage("The studentID " + studentid + " does not match any on file");
        builder.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
                // Intent intent = new Intent(adminAddClassStudent_subject.this, admin_edit_student_classes.class);
                //startActivity(intent);
                //finish();
            }
        });

        builder.setCancelable(false);
        builder.show();
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

