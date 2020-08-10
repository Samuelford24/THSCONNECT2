package com.samuelford48gmail.thsconnect.teacher;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.samuelford48gmail.thsconnect.Class_model;
import com.samuelford48gmail.thsconnect.R;
import com.samuelford48gmail.thsconnect.UtilMethods;
import com.samuelford48gmail.thsconnect.adapter_user_remove_class;

import java.util.ArrayList;


public class home_fragment2 extends Fragment {
    private static final String TAG = "home_fragment2";
    RecyclerView recyclerView;
    ArrayList<Class_model> list;
    ArrayList<String> dates;
    adapter_home_fragment recyclerAdapter;

    public home_fragment2() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_fragment2, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        String uid = FirebaseAuth.getInstance().getUid();
        list = new ArrayList<>();
        dates = new ArrayList<>();
        list.clear();
        FloatingActionButton floatingActionButton = view.findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getContext(), admin_create_class.class));
            }
        });
        recyclerAdapter = new adapter_home_fragment(list, dates);

        FirebaseFirestore.getInstance().collection("Users").document(uid).collection("Classes").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value.isEmpty()) {
                    list.clear();
                    recyclerAdapter.notifyDataSetChanged();
                }

                if (error == null) {
                    // if (!value.isEmpty()) {
                    list.clear();
                    for (final DocumentSnapshot documentSnapshot : value) {
                        System.out.println(value.size());
                        //    recycler.setUserdates(dates);

                        Log.w(TAG, documentSnapshot.getId());

                        FirebaseFirestore.getInstance().collection("Classes").document(documentSnapshot.getId()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "Task was successful");
                                    list.add(task.getResult().toObject(Class_model.class));
                                    dates = (ArrayList<String>) task.getResult().toObject(Class_model.class).getDates();
                                    recyclerAdapter.setUserdates(dates);
                                    // checkForDateChange(task.getResult().toObject(Class_model.class), documentSnapshot.getId());
                                    recyclerAdapter.notifyDataSetChanged();
                                } else {
                                    UtilMethods.removeClassFromStudent(FirebaseAuth.getInstance().getCurrentUser().getUid(), documentSnapshot.getId());
                                    Log.d(TAG, "Current data: null");
                                }
                            }
                        });


                    }

                    // } else {
                    // }
                } else {
                    UtilMethods.showErrorMessage(getContext(), "Error", "Please Check your connection and try again later");
                }
            }
        });


//was originally here
        RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutmanager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerAdapter);


        return view;
    }
}