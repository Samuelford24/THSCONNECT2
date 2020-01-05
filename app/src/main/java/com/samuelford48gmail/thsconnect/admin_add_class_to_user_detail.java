package com.samuelford48gmail.thsconnect;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class admin_add_class_to_user_detail extends AppCompatActivity {
    private Button add_class;
    private FirebaseDatabase database;
    private DatabaseReference myRef, myRef2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_class_to_user_detail);
        final String date_class2 = getIntent().getStringExtra("date_class");
        final String teacher = getIntent().getStringExtra("teacher");
        final String room_number = getIntent().getStringExtra("room_number");
        final String post_key = getIntent().getStringExtra("post_key");
        Log.d("admin_remove", post_key);
        SharedPreferences mySharedPreferences = getApplicationContext().getSharedPreferences("user_uid2", Context.MODE_PRIVATE);
        final String uid = mySharedPreferences.getString("Uid2", "");
        Log.d("admin_remove", uid);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users").child(uid).child("Classes");
        //String name = getIntent().getExtra("date_class");
        //String city = getIntent().getExtra("City");
        //System.out.println(value);
        TextView display_class_name = (TextView) findViewById(R.id.date_tv);
        display_class_name.setText(date_class2);
        TextView display_teacher = (TextView) findViewById(R.id.teacher_tv);
        display_teacher.setText(teacher);
        TextView display_room_number = (TextView) findViewById(R.id.rn_tv);
        display_room_number.setText(room_number);
        database = FirebaseDatabase.getInstance();
        //myRef = database.getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Classes");

        add_class = (Button) findViewById(R.id.add_class_2);
        //  final Query query = myRef.orderByChild("classes").equalTo(post_key);
        //System.out.println(query);
        add_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myRef = database.getReference("Classes").child(post_key).child("Students").child(uid);
                System.out.println(myRef);
                myRef.setValue(uid);
                myRef = database.getReference("Users").child(uid).child("Classes").child(post_key);
                myRef.setValue(post_key).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            AlertDialog.Builder builder;
                            builder = new AlertDialog.Builder(admin_add_class_to_user_detail.this);
                            //builder.setIcon(R.drawable.open_browser);
                            builder.setTitle("      Class Added to User");
                            builder.setNegativeButton("Ok",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            });
                            builder.setCancelable(true);
                            builder.show();
                        } else {
                            AlertDialog.Builder builder;
                            builder = new AlertDialog.Builder(admin_add_class_to_user_detail.this);
                            //builder.setIcon(R.drawable.open_browser);
                            builder.setTitle("Error Adding Class!");
                            builder.setMessage("User doesn't exist or problem with connection");
                            builder.setNegativeButton("Ok",new DialogInterface.OnClickListener() {
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
        });
    }

}