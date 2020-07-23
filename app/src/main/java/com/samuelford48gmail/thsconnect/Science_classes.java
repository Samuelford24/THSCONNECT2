package com.samuelford48gmail.thsconnect;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.widget.Toast;


import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Science_classes extends AppCompatActivity {




   private List<Listdata> list;
   private RecyclerView recyclerview;
   String class_value = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_science_classes);
        recyclerview = findViewById(R.id.rvieww);

        final String class_type = getIntent().getStringExtra("class_type");
        Toast.makeText(this, class_type, Toast.LENGTH_LONG).show();
        // myRef = database.getReference("Classes").child(key).child("class_info");
        //Query query = myRef.orderByChild("subject").equalTo("Science");
        // System.out.println(query);
        FirebaseFirestore.getInstance().collection("Classes").whereEqualTo("subject", class_type).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Log.i("Error", error.toString());
                }
                list = new ArrayList<>();
                for (DocumentSnapshot documentSnapshot : value) {

                    Class_model new_class = new Class_model(documentSnapshot.get("date_clasname").toString(), documentSnapshot.get("teacher").toString(), documentSnapshot.get("room_number").toString(), documentSnapshot.get("id").toString(), documentSnapshot.get("subject").toString());
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

        });







        // Toast.makeText(com.samuelford48gmail.thsconnect.teacher.MainActivity.this,""+name,Toast.LENGTH_LONG).show();








    }


        }


    //});


