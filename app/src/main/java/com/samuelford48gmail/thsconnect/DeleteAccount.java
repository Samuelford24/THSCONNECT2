package com.samuelford48gmail.thsconnect;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class DeleteAccount extends AppCompatActivity {
    private EditText et, et1;
    private Button b;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_account);
        et = (EditText) findViewById(R.id.editText2);
        et1 = (EditText) findViewById(R.id.editText3);
        b = (Button) findViewById(R.id.b);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = et.getText().toString().trim();
                String password = et1.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }
                database = FirebaseDatabase.getInstance();
                final String key = FirebaseAuth.getInstance().getCurrentUser().getUid();
                //myRef = database.getReference("Users").child(key);
                //  System.out.println(myRef);
                //  myRef.removeValue();
                final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                AuthCredential authCredential = EmailAuthProvider.getCredential(email, password);

                firebaseUser.reauthenticate(authCredential).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        myRef = database.getReference("Users").child(key);
                        // System.out.println(myRef);
                        myRef.removeValue();
                        firebaseUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {

                                    Intent intent = new Intent(DeleteAccount.this, LoginActivity.class);
                                    startActivity(intent);
                                    Log.d("setting", "User account deleted!");
                                } else {
                                    AlertDialog.Builder builder;
                                    builder = new AlertDialog.Builder(DeleteAccount.this);
                                    //builder.setIcon(R.drawable.open_browser);
                                    builder.setTitle("Incorrect Email or Password");
                                    builder.setMessage("Please enter your correct Email and Password to delete your account");
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
        });
    }
}
