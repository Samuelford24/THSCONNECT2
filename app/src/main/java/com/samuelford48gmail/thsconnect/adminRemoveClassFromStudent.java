package com.samuelford48gmail.thsconnect;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class adminRemoveClassFromStudent extends AppCompatActivity {
    private RecyclerView recyclerview;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private List<Listdata> list;
    List<String> keyList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_remove_class);
        final String studentid = getIntent().getStringExtra("studentid");
        System.out.println("studentID" + studentid);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");
        recyclerview = (RecyclerView) findViewById(R.id.rvieww);
        list = new ArrayList<>();
        //  Query query = myRef.orderByChild("User_info/studentID").equalTo(studentID);
        myRef.addChildEventListener(new ChildEventListener() {
            final adapter_user_remove_class recycler = new adapter_user_remove_class(list);
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String student = (String) dataSnapshot.child("User_info/studentID").getValue();
                System.out.println("Student" + student);

                if (studentid.equals(student)) {
                   studentFound();
                    //studentNotFound(studentid);


                    String user_uid = (String) dataSnapshot.child("User_info/uid").getValue();
                    SharedPreferences mySharedPreferences = getApplicationContext().getSharedPreferences("user_uid", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = mySharedPreferences.edit();
                    editor.putString("Uid", user_uid);
                    editor.apply();
                    Log.d("admin", "Got uid" + user_uid);
                    //  for (DataSnapshot dataSnapshot1 : (String)dataSnapshot.child("Classes").getKey()) {
                    Log.d("admin", "ref" + myRef);
                    myRef = database.getReference("Users").child(user_uid).child("Classes");
                    myRef.addChildEventListener(new ChildEventListener() {
                        //  final adapter_user_remove_class recycler = new adapter_user_remove_class(list);
                        final admin_adapter_delete_class recycler = new admin_adapter_delete_class(list);

                        @Override
                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String s) {
                            //list = new ArrayList<>();
                            // final adapter_user_remove_class recycler = new adapter_user_remove_class(list);
                            //  final RecyclerviewAdapter2 recycler = new RecyclerviewAdapter2(list);
//String postkey2 = dataSnapshot.getKey();

                            final String class_id = dataSnapshot.getValue(String.class);
                            Log.d("admin", "class_id" + class_id);
                            myRef = database.getReference("Classes").child(class_id).child("class_info");
                            Log.d("admin", "myref with class_id" + myRef);

                            myRef.addValueEventListener(new ValueEventListener() {

                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    //keyList.add(dataSnapshot.getKey());
                                    // if (dataSnapshot.exists()) {
                                    Class_model new_class = dataSnapshot.getValue(Class_model.class);
                                    assert new_class != null;
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
                                    keyList.add(listdata.getUid());
                                    // recycler.notifyDataSetChanged();
                                    //  keyList.add(listdata.getUid());
                                    //  Log.d("home","keylist"+ keyList);
                                    list.add(listdata);
                                    recycler.notifyDataSetChanged();
                                    //recycler.notifyDataSetChanged();// Toast.makeText(MainActivity.this,""+name,Toast.LENGTH_LONG).show();
                                    //  }
                                    //  else {myRef = database.getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Classes").child(class_id);
                                    //    myRef.removeValue();}
                                }


                                @Override
                                public void onCancelled(DatabaseError error) {
                                    AlertDialog alertDialog = new AlertDialog.Builder(adminRemoveClassFromStudent.this).create();
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
                            RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(adminRemoveClassFromStudent.this);
                            recyclerview.setLayoutManager(layoutmanager);
                            recyclerview.setItemAnimator(new DefaultItemAnimator());
                            recyclerview.setAdapter(recycler);


                        }

                        @Override
                        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        }

                        @Override
                        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                            int index = keyList.indexOf(dataSnapshot.getKey());

                            keyList.remove(index);
                            list.remove(index);

                            recycler.notifyDataSetChanged();

                        }

                        @Override
                        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            AlertDialog alertDialog = new AlertDialog.Builder(adminRemoveClassFromStudent.this).create();
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
// Map<String, String> class_list = (Map<String, String>) dataSnapshot.child("Classes").getValue();
                    //Log.d("admin", "classes" + class_list);
                    //}
                }
                    RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(adminRemoveClassFromStudent.this);
                    recyclerview.setLayoutManager(layoutmanager);
                    recyclerview.setItemAnimator(new DefaultItemAnimator());
                    recyclerview.setAdapter(recycler);
               // }//closes for loop
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
            public void onCancelled(@NonNull DatabaseError databaseError) {

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


