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
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HR_fragment extends Fragment {
    FirebaseDatabase database;
    DatabaseReference myRef;
    RecyclerView rv;
    List<ListDataUser> list;
    TextView tv;

    public HR_fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_hr_fragment, container, false);
        rv = view.findViewById(R.id.rvHRMAIN);
        database = FirebaseDatabase.getInstance();
        tv = view.findViewById(R.id.textHR);
        myRef = database.getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("HR");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {


                } else {
                    tv.setVisibility(View.INVISIBLE);
                    String s = dataSnapshot.getValue(String.class);
                    System.out.println(s);
                    getHomeroom(s);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }

    private void getHomeroom(final String reference) {

        android.text.format.DateFormat df = new android.text.format.DateFormat();
        String formattedDate = (String) DateFormat.format("MM-dd", new Date());
        System.out.println(formattedDate);
        myRef = database.getReference(reference);
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
                            myRef = database.getReference(reference).child(student);
                            // System.out.println("if datasnap not exist" + myRef);
                            myRef.removeValue();
                        }
                        // Log.d("home","keylist"+ keyList);

                        //recycler.notifyDataSetChanged();// Toast.makeText(MainActivity.this,""+name,Toast.LENGTH_LONG).show();


                    }


                    @Override
                    public void onCancelled(DatabaseError error) {
                        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
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
                RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(getContext());
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
                AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
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
