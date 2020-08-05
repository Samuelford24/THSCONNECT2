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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
                if (error == null) {
                    list = new ArrayList<>();
                    final HRStudentClassesAdapter recycler = new HRStudentClassesAdapter(list);
                    for (DocumentSnapshot documentSnapshot : value) {
                        String class_id = documentSnapshot.getId();
                        getClassInfo(class_id);
                    }
                    recycler.notifyDataSetChanged();
                } else {
                    UtilMethods.showErrorMessage(getApplicationContext(), "Error", "There was a problem connecting to the database, please try again later!");
                }

            }
        });
    }

    private void getClassInfo(final String class_id) {
        FirebaseFirestore.getInstance().collection("Classes").document(class_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                System.out.println(task.getResult());

                if (task.isSuccessful()) {
                    Class_model class_model2 = task.getResult().toObject(Class_model.class);
                    list.add(class_model2);

                } else {
                    FirebaseFirestore.getInstance().collection("Classes").document(class_id).delete();
                }

            }
        });
    }
}
