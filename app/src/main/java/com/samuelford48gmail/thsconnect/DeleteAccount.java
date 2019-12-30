package com.samuelford48gmail.thsconnect;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

                database = FirebaseDatabase.getInstance();
                final String key = FirebaseAuth.getInstance().getCurrentUser().getUid();
                myRef = database.getReference("Users").child(key);
                System.out.println(myRef);
                myRef.removeValue();
                final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                AuthCredential authCredential = EmailAuthProvider.getCredential(email, password);

                firebaseUser.reauthenticate(authCredential).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        firebaseUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {

                                    Intent intent = new Intent(DeleteAccount.this, LoginActivity.class);
                                    startActivity(intent);
                                    Log.d("setting", "User account deleted!");
                                }
                            }
                        });
                    }
                });
            }
        });
    }
}
