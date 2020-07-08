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

public class HRstudents extends AppCompatActivity {
    RecyclerView rv;
    FirebaseDatabase database;
    DatabaseReference myRef;

    List<ListDataUser> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hrstudents);
        rv = findViewById(R.id.rvHRS);
        final String f = getIntent().getStringExtra("HR");
        final String g = getIntent().getStringExtra("hrCategory");
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(g).child(f);
        System.out.println(myRef);

        list = new ArrayList<>();

        myRef.addChildEventListener(new ChildEventListener() {

            final StudentHRRV recycler = new StudentHRRV(list);

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String s) {

                //list = new ArrayList<>();
                // final adapter_user_remove_class recycler = new adapter_user_remove_class(list);
                //  final RecyclerviewAdapter2 recycler = new RecyclerviewAdapter2(list);
//String postkey2 = dataSnapshot.getKey();

                final String student = dataSnapshot.getValue(String.class);
                //Log.d("home", "class_idf" + class_id);
                System.out.println(student);
                myRef = database.getReference("Users").child(student).child("User_info");
                //  Log.d("home", "myref" + myRef);
                System.out.println(myRef);
                myRef.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //keyList.add(dataSnapshot.getKey());

                        if (dataSnapshot.exists()) {
                            User user = dataSnapshot.getValue(User.class);
                            assert user != null;
                            String name = user.getName();
                            String grade = user.getGrade();
                            String studnetId = user.getStudentID();
                            String uid = user.getUid();

                            ListDataUser l = new ListDataUser(name, grade, studnetId, uid);
                            list.add(l);
                            recycler.notifyDataSetChanged();

                            //intent.putExtra("date_class", listdata.get(position).getDate_class2());
                            // intent.putExtra("teacher", listdata.get(position).getTeacher2());
                            // intent.putExtra("room_number", listdata.get(position).getRnumber2());

                            //String grade = user.getGrade();
                            //String email = user.getEmail();
                            // System.out.println(user);


                            // System.out.println(user_info);

                            // adapter.notifyDataSetChanged();
                        } else {
                            myRef = database.getReference(g).child(f).child(student);
                            System.out.println("if datasnap not exist" + myRef);
                            myRef.removeValue();
                        }
                        // Log.d("home","keylist"+ keyList);

                        //recycler.notifyDataSetChanged();// Toast.makeText(MainActivity.this,""+name,Toast.LENGTH_LONG).show();


                    }


                    @Override
                    public void onCancelled(DatabaseError error) {
                        AlertDialog alertDialog = new AlertDialog.Builder(HRstudents.this).create();
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
                RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(HRstudents.this);
                rv.setLayoutManager(layoutmanager);
                rv.setItemAnimator(new DefaultItemAnimator());
                rv.setAdapter(recycler);


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                //Log.d("home", "ff" +dataSnapshot);
                //   int index = keyList.indexOf(dataSnapshot.getKey());
                // Log.d("home", "indexofkL"+ index
                //);
                //keyList.remove(index);
                // list.remove(index);

                // recycler.notifyDataSetChanged();

                //recycler.notifyDataSetChanged();
                //int index = keyList.indexOf(dataSnapshot.getKey());

                //     list.remove(index);
                //  keyList.remove(index);

                // recycler.notifyDataSetChanged();

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(DatabaseError error) {
                AlertDialog alertDialog = new AlertDialog.Builder(HRstudents.this).create();
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