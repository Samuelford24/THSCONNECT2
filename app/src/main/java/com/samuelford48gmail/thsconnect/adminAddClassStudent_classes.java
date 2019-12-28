package com.samuelford48gmail.thsconnect;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class adminAddClassStudent_classes extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private List<Listdata> list;
    private RecyclerView recyclerview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_science);
        recyclerview = (RecyclerView) findViewById(R.id.rview_admin_science);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Classes");
        final String class_type = getIntent().getStringExtra("class_type");
        myRef = database.getReference("Classes");

        Query query = myRef.orderByChild("class_info/subject").equalTo(class_type);
        //  Query query = myRef.child("Class_info").orderByChild("subject").equalTo("Math");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                // StringBuffer stringbuffer = new StringBuffer();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Class_model new_class = dataSnapshot1.child("class_info").getValue(Class_model.class);
                    String nameofclass = new_class.getDate_clasname();
                    String teacherofclass = new_class.getTeacher();
                    String roomnumberofclass = new_class.getRoom_number();
                    String class_key = new_class.getUid();
                    Listdata listdata = new Listdata(nameofclass, teacherofclass, roomnumberofclass, class_key);
                    //String name = userdetails.getName();
                    //String email = userdetails.getEmail();
                    //String address = userdetails.getAddress();
                    listdata.setDate_class(nameofclass);
                    listdata.setTeacher(teacherofclass);
                    listdata.setRnumber(roomnumberofclass);
                    list.add(listdata);
                    // Toast.makeText(MainActivity.this,""+name,Toast.LENGTH_LONG).show();

                }

                adapter_show_students recycler = new adapter_show_students(list);
                RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(adminAddClassStudent_classes.this);
                recyclerview.setLayoutManager(layoutmanager);
                recyclerview.setItemAnimator(new DefaultItemAnimator());
                recyclerview.setAdapter(recycler);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                AlertDialog alertDialog = new AlertDialog.Builder(adminAddClassStudent_classes.this).create();
                alertDialog.setTitle("Error");
                alertDialog.setMessage("Check your connection! If, problem persists please email svhsdev@vigoschools.org!");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
                // Failed to read value
                //  Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }
//});


}