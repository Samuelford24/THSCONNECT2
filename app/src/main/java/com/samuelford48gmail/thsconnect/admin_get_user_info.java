package com.samuelford48gmail.thsconnect;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import java.util.List;

public class admin_get_user_info extends AppCompatActivity {
   // private TextView txt;
   private FirebaseDatabase database;
   private DatabaseReference myRef;

    //  String user_info = "info";
    String studentUID = null;
    RecyclerView rv;
    private List<ListDataUser> list;
    List<String> keyList = new ArrayList<String>();
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_show_students_uid);
        list = new ArrayList<>();
        rv = (RecyclerView) findViewById(R.id.rviewuser);
        //final ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        //lv.setAdapter(adapter);
        final List<String> keyList = new ArrayList<String>();
        //lv.setStackFromBottom(true);
        TextView display_class_name = (TextView) findViewById(R.id.textView3);
        final String post_key = getIntent().getStringExtra("post_key");
        PreferenceManager.getDefaultSharedPreferences(admin_get_user_info.this).edit().putString("classKey", post_key).apply();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Classes").child(post_key).child("Students");

       // System.out.println(post_key);
       // txt = (TextView) findViewById(R.id.textView3);
        display_class_name.setText(post_key);
        myRef.addChildEventListener(new ChildEventListener() {
            final UserRecyclerView recycler = new UserRecyclerView(list);
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String s) {
                studentUID = dataSnapshot.getValue(String.class);
               // name = name.trim().toString();
                myRef = database.getReference("Users").child(studentUID).child("User_info");
          System.out.println(myRef);


                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                        if (dataSnapshot.exists()) {
                            User user = dataSnapshot.getValue(User.class);
                            assert user != null;
                            String name = user.getName();
                            String grade = user.getGrade();
                            String studnetId = user.getStudentID();
                            String uid = user.getUid();
                            keyList.add(uid);
                            ListDataUser l = new ListDataUser(name, grade, studnetId, uid);
                            list.add(l);
                            recycler.notifyDataSetChanged();

                            //intent.putExtra("date_class", listdata.get(position).getDate_class2());
                            // intent.putExtra("teacher", listdata.get(position).getTeacher2());
                            // intent.putExtra("room_number", listdata.get(position).getRnumber2());

                            //String grade = user.getGrade();
                            //String email = user.getEmail();
                            // System.out.println(user);



                            // System.out.println(user_info);

                            // adapter.notifyDataSetChanged();
                        }

                        else {
                            myRef = database.getReference("Classes").child(post_key).child("Students").child(studentUID);
                            // System.out.println("if datasnap not exist" + myRef);
                            myRef.removeValue();
                        }
                        // UserRecyclerView recycler = new UserRecyclerView(list);
                        RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(admin_get_user_info.this);
                        rv.setLayoutManager(layoutmanager);
                        rv.setItemAnimator(new DefaultItemAnimator());
                        rv.setAdapter(recycler);

                    }




                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
//adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                int index = keyList.indexOf(dataSnapshot.getKey());
                // Log.d("home", "indexofkL"+ index
                //);
                keyList.remove(index);
                list.remove(index);

                // recycler.notifyDataSetChanged();

                recycler.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        /*lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
              list.get(position);

                Toast.makeText(getApplicationContext(),
                        "t"+position, Toast.LENGTH_SHORT).show();
            }
        });*/
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
