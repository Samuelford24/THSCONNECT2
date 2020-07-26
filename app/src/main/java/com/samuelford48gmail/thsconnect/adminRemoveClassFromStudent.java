package com.samuelford48gmail.thsconnect;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class adminRemoveClassFromStudent extends AppCompatActivity {
    private RecyclerView recyclerview;

    private List<Class_model> list;
    List<String> keyList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_remove_class);
        final String studentid = getIntent().getStringExtra("studentid");
        System.out.println("studentID" + studentid);

        recyclerview = findViewById(R.id.rvieww);
        list = new ArrayList<>();
        //  Query query = myRef.orderByChild("User_info/studentID").equalTo(studentID);
        final adapter_user_remove_class recycler = new adapter_user_remove_class(list);
        FirebaseFirestore.getInstance().collection("Users").whereEqualTo("studentID", studentid).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error == null) {
                    if (!value.isEmpty()) {
                        for (DocumentSnapshot documentSnapshot : value) {
                            if (documentSnapshot.getId().equals(studentid)) {
                                getUsersClasses(documentSnapshot.getId());
                                break;
                            }
                        }
                    } else {
                        studentNotFound(studentid);
                    }
                }
            }
        });


//was originally here
        RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(adminRemoveClassFromStudent.this);
        recyclerview.setLayoutManager(layoutmanager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setAdapter(recycler);


    }


    private void getUsersClasses(String uid) {
        studentFound();
        FirebaseFirestore.getInstance().collection("Users").document(uid).collection("Classes").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                for (DocumentSnapshot documentSnapshot : value) {
                    if (UtilMethods.getClassInfo(documentSnapshot.getId()) != null) {
                        list.add(UtilMethods.getClassInfo(documentSnapshot.getId()));
                    }
                }
            }
        });
    }
    public void studentFound(){
        Log.d("adminAddClassStudent","student Found");
        final AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(adminRemoveClassFromStudent.this);
        //builder.setIcon(R.drawable.open_browser);
        builder.setTitle("Student Found");
        builder.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });

        builder.setCancelable(true);
        builder.show();
    }
    public void studentNotFound(String studentid){
        Log.d("adminAddClassStudent","student not found");
        final AlertDialog.Builder builder2;
        builder2 = new AlertDialog.Builder(adminRemoveClassFromStudent.this);
        //builder.setIcon(R.drawable.open_browser);
        builder2.setTitle("Student Not Found");
        builder2.setMessage("The studentID " + studentid + " does not match any on file");
        builder2.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(adminRemoveClassFromStudent.this, admin_edit_student_classes.class);
                startActivity(intent);
            }
        });

        builder2.setCancelable(false);
        builder2.show();
    }
}


