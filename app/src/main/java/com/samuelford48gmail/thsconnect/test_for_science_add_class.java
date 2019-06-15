package com.samuelford48gmail.thsconnect;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
            final String room_number  = getIntent().getStringExtra("room_number");
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


                    Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_SHORT).show();

                }
            });
        }


}

