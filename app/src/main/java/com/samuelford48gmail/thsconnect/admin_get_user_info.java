package com.samuelford48gmail.thsconnect;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

public class admin_get_user_info extends AppCompatActivity {
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
        myRef = database.getReference("Classes").child(post_key).child("Students");

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


                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                        User user = dataSnapshot.getValue(User.class);
                        assert user != null;
                        String name2 = user.getName();
                        String grade = user.getGrade();
                        String uid = user.getUid();


                        //intent.putExtra("date_class", listdata.get(position).getDate_class2());
                        // intent.putExtra("teacher", listdata.get(position).getTeacher2());
                        // intent.putExtra("room_number", listdata.get(position).getRnumber2());

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
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
              list.get(position);

                Toast.makeText(getApplicationContext(),
                        "t"+position, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void useValue(String yourValue) {

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
