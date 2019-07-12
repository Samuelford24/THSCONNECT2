package com.samuelford48gmail.thsconnect;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class admin_remove_class extends AppCompatActivity {
    private RecyclerView recyclerview;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private List<Listdata> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_remove_class);
        final String student_name = getIntent().getStringExtra("studentname");
        System.out.println(student_name);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");
        recyclerview = (RecyclerView) findViewById(R.id.rview);

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                final adapter_user_remove_class recycler = new adapter_user_remove_class(list);

                //   String id = dataSnapshot.getKey();
                //    System.out.println(id);
                //// class_value = snapshot.getValue(String.class);

                //System.out.println(class_value);
                // StringBuffer stringbuffer = new StringBuffer();
                //myRef = database.getReference("Classes").child(class_value).child("class_info");
                Query query = myRef.orderByChild("User_info/name").equalTo(student_name);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        list = new ArrayList<>();
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            System.out.println(dataSnapshot1);
//String class_id = dataSnapshot.child("Classes").getKey();
                            for (DataSnapshot dataSnapshot2 : dataSnapshot1.child("Classes").getChildren()){
                                System.out.println(dataSnapshot2);
                                String class_id = dataSnapshot2.getValue(String.class);
                                System.out.println(class_id);
                                myRef = database.getReference("Classes").child(class_id).child("class_info");
                                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        Class_model new_class = dataSnapshot.getValue(Class_model.class);
                                        String nameofclass = new_class.getDate_clasname();
                                        String teacherofclass = new_class.getTeacher();
                                        String roomnumberofclass = new_class.getRoom_number();
                                        String class_key = new_class.getUid();
                                        Listdata listdata = new Listdata(nameofclass, teacherofclass, roomnumberofclass, class_key);
                                        listdata.setDate_class(nameofclass);
                                        listdata.setTeacher(teacherofclass);
                                        listdata.setRnumber(roomnumberofclass);
                                        list.add(listdata);
                                        recycler.notifyDataSetChanged();

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                                RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(admin_remove_class.this);
                                recyclerview.setLayoutManager(layoutmanager);
                                recyclerview.setItemAnimator(new DefaultItemAnimator());
                                recyclerview.setAdapter(recycler);
                            }

                        }

//String class_id = dataSnapshot.child("Classes").getKey();
                      //  String class_id = dataSnapshot.child("Classes").getValue(String.class);

                        // System.out.println(class_id);
                          /*  Class_model new_class = dataSnapshot1.child("Classes").getValue(Class_model.class);
                            String nameofclass = new_class.getDate_clasname();
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
                            list.add(listdata);*/


                      //  adapter_user_remove_class recycler = new adapter_user_remove_class(list);
                     /*   RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(admin_remove_class.this);
                        recyclerview.setLayoutManager(layoutmanager);
                        recyclerview.setItemAnimator(new DefaultItemAnimator());
                        recyclerview.setAdapter(recycler);*/
                    }



                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
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
    public void getusersuid(){

    }
}


