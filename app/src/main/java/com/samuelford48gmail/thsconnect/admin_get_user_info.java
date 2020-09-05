package com.samuelford48gmail.thsconnect;

import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.TextView;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class admin_get_user_info extends AppCompatActivity {
   // private TextView txt;


    //  String user_info = "info";
    String studentUID = null;
    RecyclerView rv;
    private List<User> list;
    List<String> keyList = new ArrayList<String>();
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_show_students_uid);
        list = new ArrayList<>();
        rv = findViewById(R.id.rviewuser);
        //final ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        //lv.setAdapter(adapter);
        final List<String> keyList = new ArrayList<String>();
        //lv.setStackFromBottom(true);
        TextView display_class_name = findViewById(R.id.textView3);
        final String post_key = getIntent().getStringExtra("post_key");
        final String dateClickedOn = getIntent().getStringExtra("dateClickedOn");
        //adds to shared preferences
        PreferenceManager.getDefaultSharedPreferences(admin_get_user_info.this).edit().putString("classKey", post_key).apply();


        // System.out.println(post_key);
        // txt = (TextView) findViewById(R.id.textView3);
        display_class_name.setText(post_key);
        final UserRecyclerView recycler = new UserRecyclerView(list);
        FirebaseFirestore.getInstance().collection("Classes").document(post_key).collection("Students").whereArrayContains("dates", dateClickedOn).addSnapshotListener(new EventListener<QuerySnapshot>() {

            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error == null) {
                    for (final DocumentSnapshot documentSnapshot : value) {
                        FirebaseFirestore.getInstance().collection("Users").document(documentSnapshot.getId()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.getResult().exists()) {

                                    list.add(task.getResult().toObject(User.class));
                                    recycler.notifyDataSetChanged();
                                } else {
                                    FirebaseFirestore.getInstance().collection("Users").document(documentSnapshot.getId()).delete();
                                }
                            }
                        });


                    }
                } else {


                    //  UtilMethods.showErrorMessage(getApplicationContext(), "Error", "Please check your connection and try again later");
                }
            }
        });


        //intent.putExtra("date_class", listdata.get(position).getDate_class2());
        // intent.putExtra("teacher", listdata.get(position).getTeacher2());
                            // intent.putExtra("room_number", listdata.get(position).getRnumber2());

                            //String grade = user.getGrade();
                            //String email = user.getEmail();
                            // System.out.println(user);



                            // System.out.println(user_info);

                            // adapter.notifyDataSetChanged();


        // UserRecyclerView recycler = new UserRecyclerView(list);
                        RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(admin_get_user_info.this);
                        rv.setLayoutManager(layoutmanager);
                        rv.setItemAnimator(new DefaultItemAnimator());
                        rv.setAdapter(recycler);

                    }







/*
    public void getusersinfo(){

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                User user = dataSnapshot.getValue(User.class);

                String email = user.getName();
                String grade = user.getGrade();
                assert user != null;
                //String grade = user.getGrade();
                //String email = user.getEmail();
               // System.out.println(user);
           user_info = ("Name " + email + "Grade" + grade);
list.add(user_info);
               // System.out.println(user_info);


            }




            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //System.out.println(user_info);
//return user_info;
    }*/
}
