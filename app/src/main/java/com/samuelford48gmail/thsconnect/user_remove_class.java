package com.samuelford48gmail.thsconnect;

import android.content.Context;
import android.content.DialogInterface;

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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class user_remove_class extends AppCompatActivity {
    private Button remove_class;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_remove_class);
        final String date_class2 = getIntent().getStringExtra("date_class");
        final String teacher = getIntent().getStringExtra("teacher");
        final String room_number = getIntent().getStringExtra("room_number");
        final String post_key = getIntent().getStringExtra("post_key");
        Log.i("Post Key", post_key);
        TextView display_class_name = findViewById(R.id.date_tv);
        display_class_name.setText(date_class2);
        TextView display_teacher = findViewById(R.id.teacher_tv);
        display_teacher.setText(teacher);
        TextView display_room_number = findViewById(R.id.rn_tv);
        display_room_number.setText(room_number);
        context = getApplicationContext();
        //myRef = database.getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Classes");


        DocumentReference documentReference = FirebaseFirestore.getInstance().collection("Toggle_Classes").document("Classes");
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {


                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        if (document.get("classes").toString().equals("Classes are closed")) {
                            // ClassesClosed();
                            AlertDialog.Builder builder;
                            builder = new AlertDialog.Builder(user_remove_class.this);
                            //builder.setIcon(R.drawable.open_browser);
                            builder.setTitle("Classes cannot be edited at this time");
                            builder.setMessage("Ask a teacher to change your classes");
                            builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                    finish();
                                }
                            });
                            builder.setCancelable(false);
                            builder.show();
                        } else {

                        }
                    } else {
                        // UtilMethods.showErrorMessage(context, "Error", "Check your connection and try again");
                        AlertDialog.Builder builder;
                        builder = new AlertDialog.Builder(user_remove_class.this);
                        //builder.setIcon(R.drawable.open_browser);
                        builder.setTitle("      Error");
                        builder.setMessage("Check your connection and try again");
                        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                                finish();
                            }
                        });
                        builder.setCancelable(true);
                        builder.show();
                    }
                }


            }
        });

        remove_class = findViewById(R.id.add_class_2);
        //  final Query query = myRef.orderByChild("classes").equalTo(post_key);
        //System.out.println(query);
        remove_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                FirebaseFirestore.getInstance().collection("Classes").document(post_key).collection("Students").document(uid).delete();

                remove_class_from_student();

            }
        });
    }

    public void remove_class_from_student() {
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        final String post_key = getIntent().getStringExtra("post_key");
        FirebaseFirestore.getInstance().collection("Users").document(uid).collection("Classes").document(post_key).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    AlertDialog.Builder builder;
                    builder = new AlertDialog.Builder(user_remove_class.this);
                    //builder.setIcon(R.drawable.open_browser);
                    builder.setTitle("      Class Removed");
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
                    builder = new AlertDialog.Builder(user_remove_class.this);
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
}
