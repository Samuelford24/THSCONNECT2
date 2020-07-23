package com.samuelford48gmail.thsconnect;

import android.content.DialogInterface;

import androidx.annotation.NonNull;
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

public class HRStudentsClasses extends AppCompatActivity {
    RecyclerView recyclerview;

    ArrayList<Class_model> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hrstudents_classes);
        recyclerview = findViewById(R.id.HRstudentClasses);
        final String uid = getIntent().getStringExtra("uid");


        FirebaseFirestore.getInstance().collection("Users").document(uid).collection("Classes").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    list = new ArrayList<>();
                    final HRStudentClassesAdapter recycler = new HRStudentClassesAdapter(list);
                    for (DocumentSnapshot documentSnapshot : value) {
                        String class_id = documentSnapshot.getId();
                        if (UtilMethods.getClassInfo(class_id) != null) {
                            list.add(UtilMethods.getClassInfo(class_id));
                        }
                    }
                } else {
                    UtilMethods.showErrorMessage(getApplicationContext(), "Error", "There was a problem connecting to the database, please try again later!");
                }

            }
        });
    }
}
