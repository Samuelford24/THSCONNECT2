package com.samuelford48gmail.thsconnect;

import android.content.DialogInterface;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;


public class adminChangePassword extends AppCompatActivity {
    Button b1;
    EditText et;

    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_change_pssword);
        b1 = findViewById(R.id.button);
        et = findViewById(R.id.editText);


        FirebaseFirestore.getInstance().collection("Password").document("password").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error == null) {
                    password = value.get("password").toString();
                }
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = et.getText().toString().trim();
                FirebaseFirestore.getInstance().collection("Password").document("password").update("password", s).addOnCompleteListener(new OnCompleteListener<Void>() {
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