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

public class HRstudents extends AppCompatActivity {
    RecyclerView rv;


    ArrayList<User> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hrstudents);
        rv = findViewById(R.id.rvHRS);
        final String f = getIntent().getStringExtra("HR");
        final String g = getIntent().getStringExtra("hrCategory");


        list = new ArrayList<>();
        final StudentHRRV recycler = new StudentHRRV(list);
        FirebaseFirestore.getInstance().collection(f).document(g).collection("Students").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error == null) {
                    if (!value.isEmpty()) {
                        for (DocumentSnapshot documentSnapshot : value) {
                            String uid = documentSnapshot.getId();
                            if (UtilMethods.getUserInfo(uid) != null) {
                                list.add(UtilMethods.getUserInfo(uid));
                                recycler.notifyDataSetChanged();
                            }
                        }

                    }
                } else {
                    UtilMethods.showErrorMessage(getApplicationContext(), "Error", "Please check your connection and try again later!");
                }
            }
        });
        RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(HRstudents.this);
        rv.setLayoutManager(layoutmanager);
                rv.setItemAnimator(new DefaultItemAnimator());
                rv.setAdapter(recycler);




    }
}