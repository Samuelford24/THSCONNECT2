package com.samuelford48gmail.thsconnect;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class admin_show_students_uid extends AppCompatActivity {
   // private TextView txt;
   private FirebaseDatabase database;
   private DatabaseReference myRef;
   String user_info = "info";
 String name = null;
    ListView lv;
    ArrayList<String> list=new ArrayList<>();
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_show_students_uid);

        lv = (ListView) findViewById(R.id.lv_science_students);
        final ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        lv.setAdapter(adapter);
        lv.setStackFromBottom(true);
        TextView display_class_name = (TextView) findViewById(R.id.textView3);
        final String post_key = getIntent().getStringExtra("post_key");
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Science").child(post_key).child("Students");

       // System.out.println(post_key);
       // txt = (TextView) findViewById(R.id.textView3);
        display_class_name.setText(post_key);
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String s) {
               name = dataSnapshot.getValue(String.class);
               // name = name.trim().toString();
          myRef = database.getReference("Users").child(name).child("User_info");
          System.out.println(myRef);
               // DatabaseReference studentRef = database.getReference("Users").child(name);
        // list.add(getusersinfo());
                //getusersinfo();
                //list.add(user_info);
             /*   myRef.addValueEventListener(new ValueEventListener()) {
                    @Override
                    public void onDataChange (DataSnapshot dataSnapshot){

                        User post = dataSnapshot.getValue(User.class);
                        // ...
                    }
                    @Override
                    public void onCancelled (DatabaseError error){

                    }

                }*/
           //  System.out.println(user_info);
             //getusersinfo();
             //   list.add(user_info);

                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                        User user = dataSnapshot.getValue(User.class);
                        assert user != null;
                        String name2 = user.getName();
                        String grade = user.getGrade();

                        //String grade = user.getGrade();
                        //String email = user.getEmail();
                        // System.out.println(user);
                        user_info = ("Name: " + name2 + "Grade: " + grade);
                        assert user_info != "info";
                        list.add(user_info);
                        // System.out.println(user_info);

adapter.notifyDataSetChanged();
                    }




                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
/*
    public void getusersinfo(){

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                User user = dataSnapshot.getValue(User.class);

                String email = user.getName();
                String grade = user.getGrade();
                assert user != null;
                //String grade = user.getGrade();
                //String email = user.getEmail();
               // System.out.println(user);
           user_info = ("Name " + email + "Grade" + grade);
list.add(user_info);
               // System.out.println(user_info);


            }




            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //System.out.println(user_info);
//return user_info;
    }*/
}
