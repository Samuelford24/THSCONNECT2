package com.samuelford48gmail.thsconnect;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class adminChangePassword extends AppCompatActivity {
    Button b1;
    EditText et;
    FirebaseDatabase database;
    DatabaseReference myref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_change_pssword);
        b1 = (Button) findViewById(R.id.button);
        et = (EditText) findViewById(R.id.editText);
        database = FirebaseDatabase.getInstance();
        myref = database.getReference().child("Password");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = et.getText().toString().trim();
                myref.setValue(s).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            AlertDialog.Builder builder;
                            builder = new AlertDialog.Builder(adminChangePassword.this);
                            //builder.setIcon(R.drawable.open_browser);
                            builder.setTitle("      Password successfully changed");
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
                            builder = new AlertDialog.Builder(adminChangePassword.this);
                            //builder.setIcon(R.drawable.open_browser);
                            builder.setTitle("Error changing Password");
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


            }
        });
    }
}