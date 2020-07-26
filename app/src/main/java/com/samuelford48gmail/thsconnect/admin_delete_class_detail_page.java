package com.samuelford48gmail.thsconnect;

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
import com.google.firebase.firestore.FirebaseFirestore;


public class admin_delete_class_detail_page extends AppCompatActivity {
    private Button remove_class;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_delete_class_detail_page);
        Class_model class_model = getIntent().getParcelableExtra("class");

        final String post_key = getIntent().getStringExtra("post_key");


        TextView display_class_name = findViewById(R.id.date_tv);
        display_class_name.setText(class_model.getDate_clasname());
        TextView display_teacher = findViewById(R.id.teacher_tv);
        display_teacher.setText(class_model.getTeacher());
        TextView display_room_number = findViewById(R.id.rn_tv);
        display_room_number.setText(class_model.getRoom_number());

        //myRef = database.getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Classes");

        remove_class = findViewById(R.id.add_class_2);
        //  final Query query = myRef.orderByChild("classes").equalTo(post_key);
        //System.out.println(query);
        remove_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                FirebaseFirestore.getInstance().collection("Classes").document(post_key).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            AlertDialog.Builder builder;
                            builder = new AlertDialog.Builder(admin_delete_class_detail_page.this);
                            //builder.setIcon(R.drawable.open_browser);
                            builder.setTitle("      Class Deleted");
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
                            builder = new AlertDialog.Builder(admin_delete_class_detail_page.this);
                            //builder.setIcon(R.drawable.open_browser);
                            builder.setTitle("Error Deleting Class!");
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
