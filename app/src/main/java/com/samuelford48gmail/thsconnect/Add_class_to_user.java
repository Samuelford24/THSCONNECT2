package com.samuelford48gmail.thsconnect;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Add_class_to_user extends AppCompatActivity {
    private Button add_class;
    Class_model class_model;
    String class_id;
    ListView lv;
    ArrayList<String> dates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class_to_user);

        class_model = getIntent().getExtras().getParcelable("class");
        class_id = class_model.getid();
        lv = findViewById(R.id.dateslv);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, class_model.getDates());
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lv.setAdapter(adapter);
        //  final String date_class2 = getIntent().getStringExtra("date_class");
        // final String teacher = getIntent().getStringExtra("teacher");
        // final String room_number = getIntent().getStringExtra("room_number");
        //   final String post_key = getIntent().getStringExtra("post_key");
        //  Log.i("onCreate", post_key);
        DocumentReference documentReference = FirebaseFirestore.getInstance().collection("Toggle_Classes").document("Classes");
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {


                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        if (document.get("classes").toString().equals("Classes Closed")) {
                            // ClassesClosed();
                            AlertDialog.Builder builder;
                            builder = new AlertDialog.Builder(Add_class_to_user.this);
                            //builder.setIcon(R.drawable.open_browser);
                            builder.setTitle("      Classes cannot be edited at this time");
                            builder.setMessage("Ask a teacher to change your classes");
                            builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                    finish();
                                }
                            });
                            builder.setCancelable(false);
                            builder.show();
                        } else {
                            Log.e("Firebase", "document doesn't exist");
                        }
                    } else {
                        // UtilMethods.showErrorMessage(getApplicationContext(), "Error", "Check your connection and try again");
                    }
                }


            }
        });


        //myRef = database.getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Classes");
        //String name = getIntent().getExtra("date_class");
        //String city = getIntent().getExtra("City");
        //System.out.println(value);
        TextView display_class_name = findViewById(R.id.date_tv);
        display_class_name.setText(class_model.getClassname());
        TextView display_teacher = findViewById(R.id.teacher_tv);
        display_teacher.setText(class_model.getTeacher());
        TextView display_room_number = findViewById(R.id.rn_tv);
        display_room_number.setText(class_model.getRoom_number());
        add_class = findViewById(R.id.add_class_2);
        dates = new ArrayList<>();
        add_class.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int count = lv.getCount();
                SparseBooleanArray sparseBooleanArray = lv.getCheckedItemPositions();
                for (int i = 0; i < count; i++) {
                    if (sparseBooleanArray.get(i)) {
                        dates.add(lv.getItemAtPosition(i).toString());
                    }
                }
                if (dates.size() != 0) {


                    Map<String, Object> map = new HashMap<>();
                    map.put("id", class_model.getid());
                    map.put("dates", dates);
                    FirebaseFirestore.getInstance().collection("Users").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).collection("Classes").document(class_id).set(map);
                    // Class_model new_class_to_user_uid = new Class_model(date_class2, teacher, room_number, null);

                    add_student_to_class();
                    // Class_model new_class_to_user_uid = new Class_model(date_class2, teacher, room_number, );
                    //myRef.child(new_class_to_user_uid.).removeValue();

                    // Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_SHORT).show();
                } else {
                    AlertDialog.Builder builder;
                    builder = new AlertDialog.Builder(Add_class_to_user.this);
                    //builder.setIcon(R.drawable.open_browser);
                    builder.setTitle("You must select one date to be able to add the class");
                    builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();

                        }
                    });
                    builder.setCancelable(true);
                    builder.show();
                }
            }
        });

    }

    public void add_student_to_class() {
        // final String post_key = getIntent().getStringExtra("post_key");
        String key = FirebaseAuth.getInstance().getCurrentUser().getUid();
        // String key2 = myRef.push().getKey();
        Map<String, Object> map2 = new HashMap<>();
        map2.put("uid", key);
        map2.put("dates", dates);
        System.out.println(key);
        System.out.println("Class id" + class_model.getid());
        FirebaseFirestore.getInstance().collection("Classes").document(class_id).collection("Students").document(key).set(map2).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    AlertDialog.Builder builder;
                    builder = new AlertDialog.Builder(Add_class_to_user.this);
                    //builder.setIcon(R.drawable.open_browser);
                    builder.setTitle("      Class Added");
                    builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                            finish();
                        }
                    });
                    builder.setCancelable(true);
                    builder.show();
                } else {
                    AlertDialog.Builder builder;
                    builder = new AlertDialog.Builder(Add_class_to_user.this);
                    //builder.setIcon(R.drawable.open_browser);
                    builder.setTitle("Error Adding Class!");
                    builder.setMessage("Please check your wifi/data connection and try again");
                    builder.setNegativeButton("Ok",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
                    builder.setCancelable(true);
                    builder.show();
                }
            }


        });


        //System.out.println(myRef);
       // myRef = database.getReference("Users").child(key);
        // System.out.println("hello")
      /*  myRef.addChildEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    User user_info = dataSnapshot1.getValue(User.class);
                    String name = new_class.getDate_clasname();
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
                    // Toast.makeText(com.samuelford48gmail.thsconnect.teacher.MainActivity.this,""+name,Toast.LENGTH_LONG).show();

                }

            }
            public void onCancelled(DatabaseError databaseError){
                System.out.println("Hello");
            }
        });
*/
    }
}