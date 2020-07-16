package com.samuelford48gmail.thsconnect;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Science_classes extends AppCompatActivity {



    private FirebaseDatabase database;
   private  DatabaseReference myRef, newmf;
   private List<Listdata> list;
   private RecyclerView recyclerview;
   String class_value = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_science_classes);
        recyclerview = findViewById(R.id.rvieww);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Classes");
final String class_type = getIntent().getStringExtra("class_type");
Toast.makeText(this,class_type,Toast.LENGTH_LONG).show();
        // myRef = database.getReference("Classes").child(key).child("class_info");
        //Query query = myRef.orderByChild("subject").equalTo("Science");
       // System.out.println(query);
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


             //   String id = dataSnapshot.getKey();
              //    System.out.println(id);
                   //// class_value = snapshot.getValue(String.class);

                //System.out.println(class_value);
                // StringBuffer stringbuffer = new StringBuffer();
                //myRef = database.getReference("Classes").child(class_value).child("class_info");
                Query query = myRef.orderByChild("class_info/subject").equalTo("Science");
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        list = new ArrayList<>();
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                            Class_model new_class = dataSnapshot1.getValue(Class_model.class);
                            String nameofclass = new_class.getDate_clasname();
                            String teacherofclass = new_class.getTeacher();
                            String roomnumberofclass = new_class.getRoom_number();
                            String class_key = new_class.getid();
                            Listdata listdata = new Listdata(nameofclass, teacherofclass, roomnumberofclass, class_key);
                            //String name = userdetails.getName();
                            //String email = userdetails.getEmail();
                            //String address = userdetails.getAddress();
                            listdata.setDate_class(nameofclass);
                            listdata.setTeacher(teacherofclass);
                            listdata.setRnumber(roomnumberofclass);
                            list.add(listdata);
                        }
                        RecyclerviewAdapter2 recycler = new RecyclerviewAdapter2(list);
                        RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(Science_classes.this);
                        recyclerview.setLayoutManager(layoutmanager);
                        recyclerview.setItemAnimator(new DefaultItemAnimator());
                        recyclerview.setAdapter(recycler);
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


        // Toast.makeText(com.samuelford48gmail.thsconnect.teacher.MainActivity.this,""+name,Toast.LENGTH_LONG).show();








    }


        }


    //});


