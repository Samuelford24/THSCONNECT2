package com.samuelford48gmail.thsconnect;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class test_for_science_add_class extends AppCompatActivity {
    private Button add_class;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_for_science_addd_class);
        final String date_class = getIntent().getStringExtra("date_class");
        final String teacher = getIntent().getStringExtra("teacher");
        final String room_number = getIntent().getStringExtra("room_number");
        final String post_key = getIntent().getStringExtra("post_key");
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Classes");
        //String name = getIntent().getExtra("date_class");
        //String city = getIntent().getExtra("City");
        //System.out.println(value);
        TextView display_class_name = (TextView) findViewById(R.id.date_tv2);
        display_class_name.setText(date_class);
        TextView display_teacher = (TextView) findViewById(R.id.teacher_tv2);
        display_teacher.setText(teacher);
        TextView display_room_number = (TextView) findViewById(R.id.rn_tv2);
        display_room_number.setText(room_number);
        add_class = (Button) findViewById(R.id.add_class_2);
        add_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Class_model new_class_to_user_uid = new Class_model(date_class, teacher, room_number, null);
                myRef.push().setValue(new_class_to_user_uid);
                //new...testing
                myRef = database.getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                /*myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // list = new ArrayList<>();
                        // StringBuffer stringbuffer = new StringBuffer();
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            User get_user = dataSnapshot1.getValue(User.class);
                            System.out.println(get_user);
                            String name =  get_user.getName();

                            //System.out.println(name);
                            Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_SHORT).show();

                        }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                AlertDialog alertDialog = new AlertDialog.Builder(test_for_science_add_class.this).create();
                alertDialog.setTitle("Error");
                alertDialog.setMessage("Check your connection! If, problem persists please email svhsdev@vigoschools.org!");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
                // Failed to read value
                //  Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
*/

            }
        });
    }
}




