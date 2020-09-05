package com.samuelford48gmail.thsconnect;

import android.content.DialogInterface;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HR_fragment extends Fragment {
    RecyclerView rv;
    ArrayList<User> list;
    TextView tv;
    String date;
    String className;
    ArrayList<String> classNames = new ArrayList<>();
    StudentHRRV recycler;
    ArrayList<String> absentList = new ArrayList<>();

    public HR_fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_hr_fragment, container, false);
        rv = view.findViewById(R.id.rvHRMAIN);

        tv = view.findViewById(R.id.textHR);
        //  myRef = database.getReference("Users").child().child("HR");
        FirebaseFirestore.getInstance().collection("Users").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error == null) {
                    if (value.exists()) {
                        tv.setVisibility(View.INVISIBLE);
                        getHomeroom(value.get("homeroom").toString());
                    }
                } else {
                    UtilMethods.showErrorMessage(getContext(), "Error", "Please check your connection and try again later!");
                }
            }
        });

        return view;
    }

    private void getHomeroom(final String reference) {
        String hr;
        android.text.format.DateFormat df = new android.text.format.DateFormat();
        date = (String) DateFormat.format("MM-dd", new Date());


        int value = reference.charAt(0);
        //int asciiValue = (int) value;
        System.out.println(value);
        if (value >= 65 && value < 67) {
            hr = "HR1";

        } else if (value <= 70) {
            hr = "HR2";

        } else if (value <= 76) {
            hr = "HR3";

        } else if (value <= 79) {
            hr = "HR4";

        } else if (value <= 83) {
            hr = "HR5";

        } else if (value <= 90) {
            hr = "HR6";

        } else {
            hr = "Incorrect Format";
        }
        list = new ArrayList<>();
        recycler = new StudentHRRV(list);
        FirebaseFirestore.getInstance().collection(hr).document(reference).collection("Students").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error == null) {
                    if (value != null) {

                        tv.setVisibility(View.INVISIBLE);
                        list.clear();
                        for (DocumentSnapshot documentSnapshot : value) {
                            getUserInfo(documentSnapshot.getId());
                            //  recycler.notifyDataSetChanged();
                            getClassForToday(documentSnapshot.getId());
                            getAttendance(documentSnapshot.getId());
                        }
                        recycler.setClassNames(classNames);
                        recycler.setAbsentList(absentList);
                        recycler.notifyDataSetChanged();
                    } else {
                    }
                } else {
                    UtilMethods.showErrorMessage(getContext(), "Error", "Please check your connection and try again later!");
                }
            }
        });

//was originally here
        RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutmanager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(recycler);


    }

    private void getAttendance(String uid) {

        FirebaseFirestore.getInstance().collection("Users").document(uid).collection("Attendance").whereEqualTo("attendance", "Absent from assigned class on: " + date).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value.size() == 1) {
                    absentList.add("Absent");

                } else {
                    absentList.add("Not absent");
                }
            }
        });
    }

    private void getUserInfo(final String uid) {
//if classToday returns true pass in class to array and send to adapter
        FirebaseFirestore.getInstance().collection("Users").document(uid).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value != null && value.exists()) {
                    User user = value.toObject(User.class);
                    //  getClassForToday(uid);
                    // checkAttendance();
                    list.add(user);
                } else {
                    //  FirebaseFirestore.getInstance().collection("Users").document(uid).delete();
                }
            }
        });
    }

    //gets the class id if the student has added a class for the current date
    private void getClassForToday(String uid) {
        FirebaseFirestore.getInstance().collection("Users").document(uid).collection("Classes").whereArrayContains("date", date).limit(1).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error == null) {
                    if (value.size() == 0) {
                        classNames.add("No Class");
                        return;
                    }
                    for (DocumentSnapshot documentSnapshot : value) {
                        String id = documentSnapshot.get("id").toString();
                        getClassName(id);

                    }
                }
            }
        });
    }

    private void getClassName(String classID) {

        FirebaseFirestore.getInstance().collection("Classes").document(classID).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                className = documentSnapshot.get("classname").toString();
                classNames.add(className);
                // recycler.setClassNames(classNames);
            }
        });
    }

}

