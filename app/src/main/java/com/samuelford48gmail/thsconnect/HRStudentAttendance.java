package com.samuelford48gmail.thsconnect;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class HRStudentAttendance extends AppCompatActivity {

    ListView listview2;
    ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hrstudent_attendance);
        String uid = getIntent().getStringExtra("uid");
        listview2 = findViewById(R.id.Attendance);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listview2.setAdapter(adapter);
        listview2.setStackFromBottom(true);


        FirebaseFirestore.getInstance().collection("Users").document(uid).collection("Attendance").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    if (!value.isEmpty()) {
                        for (DocumentSnapshot documentSnapshot : value) {
                            list.add(documentSnapshot.get("attendance").toString());
                        }
                    } else {

                    }
                } else {
                    AlertDialog.Builder builder;
                    builder = new AlertDialog.Builder(HRStudentAttendance.this);

                    //builder.setIcon(R.drawable.open_browser);
                    builder.setTitle("      No attendance");
                    builder.setMessage("There isn't any attendance information for this user");
                    builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                            finish();
                        }
                    });
                    builder.setCancelable(true);
                    builder.show();

                    // UtilMethods.showErrorMessage(getApplicationContext(), "Error", "There was a problem checking the attendance. Please check your connection and try again.");
                }
            }
        });

    }
}
