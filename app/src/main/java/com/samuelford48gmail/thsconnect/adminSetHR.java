package com.samuelford48gmail.thsconnect;

import android.content.DialogInterface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class adminSetHR extends AppCompatActivity {

    TextView h;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_set_hr);
        h = findViewById(R.id.editText4);
        b = findViewById(R.id.button4);
        /*
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                final String s = h.getText().toString().trim();
                System.out.println("refrence" + s);
                myRef = database.getReference(s);
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (!dataSnapshot.exists()) {
                            AlertDialog.Builder builder;
                            builder = new AlertDialog.Builder(adminSetHR.this);
                            //builder.setIcon(R.drawable.open_browser);
                            builder.setTitle("Homeroom doesn't exist");
                            builder.setMessage("Please try entering a correct homeroom reference; if the problem persists, please email svhsdev@vigoschools.org");
                            builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            });
                            builder.setCancelable(true);
                            builder.show();
                        } else {

                            database.getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("HR").setValue(s);
                            AlertDialog.Builder builder;
                            builder = new AlertDialog.Builder(adminSetHR.this);
                            //builder.setIcon(R.drawable.open_browser);
                            builder.setTitle("Homeroom added successfully");
                            builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            });
                            builder.setCancelable(true);
                            builder.show();


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }


                });
            }
        });

    }
}*/
    }
}
