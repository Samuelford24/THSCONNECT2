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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HRStudentsClasses extends AppCompatActivity {
    RecyclerView recyclerview;
    DatabaseReference myRef, myRef2;
    FirebaseDatabase database;
    List<Listdata> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hrstudents_classes);
        recyclerview = findViewById(R.id.HRstudentClasses);
        final String uid = getIntent().getStringExtra("uid");
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users")
                .child(uid).child("Classes");

        System.out.println(myRef);
        list = new ArrayList<>();

        myRef.addChildEventListener(new ChildEventListener() {

            final HRStudentClassesAdapter recycler = new HRStudentClassesAdapter(list);

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String s) {

                //list = new ArrayList<>();
                // final adapter_user_remove_class recycler = new adapter_user_remove_class(list);
                //  final RecyclerviewAdapter2 recycler = new RecyclerviewAdapter2(list);
//String postkey2 = dataSnapshot.getKey();

                final String class_id = dataSnapshot.getValue(String.class);
                //Log.d("home", "class_idf" + class_id);
                myRef = database.getReference("Classes").child(class_id).child("class_info");
                //  Log.d("home", "myref" + myRef);

                myRef.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //keyList.add(dataSnapshot.getKey());
                        if (dataSnapshot.exists()) {
                            Class_model new_class = dataSnapshot.getValue(Class_model.class);
                            assert new_class != null;
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
                            // recycler.notifyDataSetChanged();

                            // Log.d("home","keylist"+ keyList);
                            list.add(listdata);
                            recycler.notifyDataSetChanged();
                            //recycler.notifyDataSetChanged();// Toast.makeText(com.samuelford48gmail.thsconnect.teacher.MainActivity.this,""+name,Toast.LENGTH_LONG).show();
                        } else {
                            myRef = database.getReference("Users").child(uid).child("Classes").child(class_id);
                            myRef.removeValue();
                        }
                    }


                    @Override
                    public void onCancelled(DatabaseError error) {
                        AlertDialog alertDialog = new AlertDialog.Builder(HRStudentsClasses.this).create();
                        alertDialog.setTitle("Error");
                        alertDialog.setMessage("Check your connection! If, problem persists please email svhsdev@vigoschools.org!");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                    }

                });

//was originally here
                RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(HRStudentsClasses.this);
                recyclerview.setLayoutManager(layoutmanager);
                recyclerview.setItemAnimator(new DefaultItemAnimator());
                recyclerview.setAdapter(recycler);


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(DatabaseError error) {
                AlertDialog alertDialog = new AlertDialog.Builder(HRStudentsClasses.this).create();
                alertDialog.setTitle("Error");
                alertDialog.setMessage("Check your connection! If, problem persists please email svhsdev@vigoschools.org!");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }


        });
    }
}
