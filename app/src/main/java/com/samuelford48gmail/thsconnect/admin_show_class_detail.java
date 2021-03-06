package com.samuelford48gmail.thsconnect;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;


public class admin_show_class_detail extends AppCompatActivity {
    private Button remove_class;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_show_class_detail);
        final String date_class2 = getIntent().getStringExtra("date_class");
        final String teacher = getIntent().getStringExtra("teacher");
        final String room_number = getIntent().getStringExtra("room_number");
        final String post_key = getIntent().getStringExtra("post_key");
        Log.d("admin_remove", post_key);
        SharedPreferences mySharedPreferences = getApplicationContext().getSharedPreferences("user_uid", Context.MODE_PRIVATE);
        final String uid = mySharedPreferences.getString("Uid", "");
        Log.d("admin_remove", uid);

        // myRef = database.getReference("Users").child(uid).child("Classes");

        //String name = getIntent().getExtra("date_class");
        //String city = getIntent().getExtra("City");
        //System.out.println(value);
        TextView display_class_name = findViewById(R.id.date_tv);
        display_class_name.setText(date_class2);
        TextView display_teacher = findViewById(R.id.teacher_tv);
        display_teacher.setText(teacher);
        TextView display_room_number = findViewById(R.id.rn_tv);
        display_room_number.setText(room_number);

        //myRef = database.getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Classes");

        remove_class = findViewById(R.id.add_class_2);
        //  final Query query = myRef.orderByChild("classes").equalTo(post_key);
        //System.out.println(query);
        remove_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                FirebaseFirestore.getInstance().collection("Classes").document(post_key).collection("Students").document(uid).delete();


                FirebaseFirestore.getInstance().collection("Users").document(uid).collection("Classes").document(post_key).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            AlertDialog.Builder builder;
                            builder = new AlertDialog.Builder(admin_show_class_detail.this);
                            //builder.setIcon(R.drawable.open_browser);
                            builder.setTitle("      Class Removed");
                            builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            });
                            builder.setCancelable(true);
                            builder.show();
                        } else {
                            AlertDialog.Builder builder;
                            builder = new AlertDialog.Builder(admin_show_class_detail.this);
                            //builder.setIcon(R.drawable.open_browser);
                            builder.setTitle("Error Removing Class!");
                            builder.setMessage("Please check your wifi/data connection and try again");
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
