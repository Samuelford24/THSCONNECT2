package com.samuelford48gmail.thsconnect;

import android.content.DialogInterface;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Classes extends AppCompatActivity {


    private ArrayList<Class_model> list;
    private RecyclerView recyclerview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_classes);
        recyclerview = findViewById(R.id.rvieww);

        final String class_type = getIntent().getStringExtra("class_type");

        FirebaseFirestore.getInstance().collection("Classes").whereEqualTo("subject", class_type).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error == null) {
                    if (!value.isEmpty()) {
                        for (DocumentSnapshot documentSnapshot : value) {
                            if (UtilMethods.getClassInfo(documentSnapshot.getId()) != null) {
                                list.add(UtilMethods.getClassInfo(documentSnapshot.getId()));
                            }
                        }
                    }
                } else {
                    UtilMethods.showErrorMessage(getApplicationContext(), "Error", "Check your connection and try again later!");
                }
            }
        });

        //  Query query = myRef.child("Class_info").orderByChild("subject").equalTo("Math");

        RecyclerviewAdapter2 recycler = new RecyclerviewAdapter2(list);
        RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(Classes.this);
        recyclerview.setLayoutManager(layoutmanager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setAdapter(recycler);

    }


}
    //});


