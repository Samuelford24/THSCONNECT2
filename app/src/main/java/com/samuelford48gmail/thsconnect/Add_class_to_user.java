package com.samuelford48gmail.thsconnect;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Add_class_to_user extends AppCompatActivity {
    private Button add_class;
    private FirebaseDatabase database;
    private DatabaseReference myRef, myRef2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class_to_user);
        final String date_class2 = getIntent().getStringExtra("date_class");
        final String teacher = getIntent().getStringExtra("teacher");
        final String room_number = getIntent().getStringExtra("room_number");
        final String post_key = getIntent().getStringExtra("post_key");
        database = FirebaseDatabase.getInstance();
        myRef2 = database.getReference("Toggle_Classes");

        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue(String.class).equals("Classes Closed")) {
                    // ClassesClosed();
                    AlertDialog.Builder builder;
                    builder = new AlertDialog.Builder(Add_class_to_user.this);
                    //builder.setIcon(R.drawable.open_browser);
                    builder.setTitle("      Classes Closed");
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
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });


        myRef = database.getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Classes");
        //String name = getIntent().getExtra("date_class");
        //String city = getIntent().getExtra("City");
        //System.out.println(value);
        TextView display_class_name = findViewById(R.id.date_tv);
        display_class_name.setText(date_class2);
        TextView display_teacher = findViewById(R.id.teacher_tv);
        display_teacher.setText(teacher);
        TextView display_room_number = findViewById(R.id.rn_tv);
        display_room_number.setText(room_number);
        add_class = findViewById(R.id.add_class_2);
        add_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Class_model new_class_to_user_uid = new Class_model(date_class2, teacher, room_number, null);
                myRef.child(post_key).setValue(post_key);
                add_student_to_class();
                // Class_model new_class_to_user_uid = new Class_model(date_class2, teacher, room_number, );
                //myRef.child(new_class_to_user_uid.).removeValue();

                // Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void add_student_to_class() {
        final String post_key = getIntent().getStringExtra("post_key");
        String key = FirebaseAuth.getInstance().getCurrentUser().getUid();
       // String key2 = myRef.push().getKey();
        myRef2 = database.getReference().child("Classes").child(post_key).child("Students").child(key);
        myRef2.setValue(key).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    AlertDialog.Builder builder;
                    builder = new AlertDialog.Builder(Add_class_to_user.this);
                    //builder.setIcon(R.drawable.open_browser);
                    builder.setTitle("      Class Added");
                    builder.setNegativeButton("Ok",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                            finish();
                        }
                    });
                    builder.setCancelable(true);
                    builder.show();
                } else {
                    AlertDialog.Builder builder;
                    builder = new AlertDialog.Builder(Add_class_to_user.this);
                    //builder.setIcon(R.drawable.open_browser);
                    builder.setTitle("Error Adding Class!");
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


        //System.out.println(myRef);
       // myRef = database.getReference("Users").child(key);
        // System.out.println("hello")
      /*  myRef.addChildEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    User user_info = dataSnapshot1.getValue(User.class);
                    String name = new_class.getDate_clasname();
                    String teacherofclass = new_class.getTeacher();
                    String roomnumberofclass = new_class.getRoom_number();
                    String class_key = new_class.getUid();
                    Listdata listdata = new Listdata(nameofclass, teacherofclass, roomnumberofclass, class_key);
                    //String name = userdetails.getName();
                    //String email = userdetails.getEmail();
                    //String address = userdetails.getAddress();
                    listdata.setDate_class(nameofclass);
                    listdata.setTeacher(teacherofclass);
                    listdata.setRnumber(roomnumberofclass);
                    list.add(listdata);
                    // Toast.makeText(MainActivity.this,""+name,Toast.LENGTH_LONG).show();

                }

            }
            public void onCancelled(DatabaseError databaseError){
                System.out.println("Hello");
            }
        });
*/
    }
}