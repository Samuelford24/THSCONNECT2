package com.samuelford48gmail.thsconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Classes extends AppCompatActivity {


    private ArrayList<Class_model> list;
    private RecyclerView recyclerview;
    Class_model class_model;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_classes);
        recyclerview = findViewById(R.id.rvieww);

        final String class_type = getIntent().getStringExtra("class_type");
        list = new ArrayList<>();
        final RecyclerviewAdapter2 recycler = new RecyclerviewAdapter2(list);
        System.out.println(class_type);

        FirebaseFirestore.getInstance().collection("Classes").whereEqualTo("subject", class_type).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    //will always return data
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        System.out.println(document.get("id"));
                        class_model = document.toObject(Class_model.class);
                        System.out.println("id" + class_model.getid());
                        System.out.println(class_model.getid());
                        System.out.println(class_model.getClassname());
                        System.out.println(class_model.getRoom_number());
                        System.out.println(class_model.getSubject());
                        System.out.println(class_model.getTeacher());
                        if (class_model.getClassname() != null) {
                            list.add(class_model);
                            recycler.notifyDataSetChanged();
                        } else {
                            UtilMethods.removeClass(document.getId());
                        }
                    }
//recycler.setListdata(list);
                } else {
                    Log.d("Classes", "Error getting documents: ", task.getException());
                    UtilMethods.showErrorMessage(getApplicationContext(), "Error", "Check your connection and try again later!");
                }

                //  Query query = myRef.child("Class_info").orderByChild("subject").equalTo("Math");
            }

        });
        RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(Classes.this);
        recyclerview.setLayoutManager(layoutmanager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setAdapter(recycler);


    }


}
    //});


