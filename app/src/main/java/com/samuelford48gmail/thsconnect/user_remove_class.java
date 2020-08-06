package com.samuelford48gmail.thsconnect;

import android.content.Context;
import android.content.DialogInterface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class user_remove_class extends AppCompatActivity {
    private Button remove_class;
    Context context;
    Class_model class_model;
    ListView lv;
    Button updateDates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_remove_class);
        class_model = getIntent().getParcelableExtra("class");
        //  final String date_class2 = getIntent().getStringExtra("date_class");
        //  final String teacher = getIntent().getStringExtra("teacher");
        // final String room_number = getIntent().getStringExtra("room_number");
        // final String post_key = getIntent().getStringExtra("post_key");
        lv = findViewById(R.id.dates_lv);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, class_model.getDates());
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        lv.setAdapter(adapter);
        checkAllDates();
        updateDates = findViewById(R.id.update_dates);
        TextView display_class_name = findViewById(R.id.date_tv);
        display_class_name.setText(class_model.getClassname());
        TextView display_teacher = findViewById(R.id.teacher_tv);
        display_teacher.setText(class_model.getTeacher());
        TextView display_room_number = findViewById(R.id.rn_tv);
        display_room_number.setText(class_model.getRoom_number());

        context = getApplicationContext();
        //myRef = database.getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Classes");


        DocumentReference documentReference = FirebaseFirestore.getInstance().collection("Toggle_Classes").document("Classes");
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {


                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        if (document.get("classes").toString().equals("Classes are closed")) {
                            // ClassesClosed();
                            AlertDialog.Builder builder;
                            builder = new AlertDialog.Builder(user_remove_class.this);
                            //builder.setIcon(R.drawable.open_browser);
                            builder.setTitle("Classes cannot be edited at this time");
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

                        }
                    } else {
                        // UtilMethods.showErrorMessage(context, "Error", "Check your connection and try again");
                        AlertDialog.Builder builder;
                        builder = new AlertDialog.Builder(user_remove_class.this);
                        //builder.setIcon(R.drawable.open_browser);
                        builder.setTitle("      Error");
                        builder.setMessage("Check your connection and try again");
                        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                                finish();
                            }
                        });
                        builder.setCancelable(true);
                        builder.show();
                    }
                }


            }
        });

        remove_class = findViewById(R.id.add_class_2);
        //  final Query query = myRef.orderByChild("classes").equalTo(post_key);
        //System.out.println(query);
        remove_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                FirebaseFirestore.getInstance().collection("Classes").document(class_model.getid()).collection("Students").document(uid).delete();

                remove_class_from_student();

            }
        });
        updateDates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> dates = new ArrayList<>();

                int count = lv.getCount();
                SparseBooleanArray sparseBooleanArray = lv.getCheckedItemPositions();
                for (int i = 0; i < count; i++) {
                    if (sparseBooleanArray.get(i)) {
                        dates.add(lv.getItemAtPosition(i).toString());

                    }
                }
                if (dates.size() != 0) {
                    Map<String, Object> map = new HashMap<>();

                    map.put("dates", dates);
                    FirebaseFirestore.getInstance().collection("Users").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).collection("Classes").document(class_model.getid()).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            AlertDialog.Builder builder;
                            builder = new AlertDialog.Builder(user_remove_class.this);
                            //builder.setIcon(R.drawable.open_browser);
                            builder.setTitle("The date(s) for this class has successfully been updated");
                            builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                    finish();
                                }
                            });
                            builder.setCancelable(true);
                            builder.show();
                        }
                    });
                    // Class_model new_class_to_user_uid = new Class_model(date_class2, teacher, room_number, null);
                } else {
                    AlertDialog.Builder builder;
                    builder = new AlertDialog.Builder(user_remove_class.this);
                    //builder.setIcon(R.drawable.open_browser);
                    builder.setTitle("You must select one date");
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

    public void remove_class_from_student() {
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        final String post_key = getIntent().getStringExtra("post_key");
        FirebaseFirestore.getInstance().collection("Users").document(uid).collection("Classes").document(post_key).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    AlertDialog.Builder builder;
                    builder = new AlertDialog.Builder(user_remove_class.this);
                    //builder.setIcon(R.drawable.open_browser);
                    builder.setTitle("      Class Removed");
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
                    builder = new AlertDialog.Builder(user_remove_class.this);
                    //builder.setIcon(R.drawable.open_browser);
                    builder.setTitle("Error Removing Class!");
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
    }

    private void checkAllDates() {
        int size = class_model.getDates().size();
        for (int i = 0; i < size; i++) {
            lv.setItemChecked(i, true);
        }
    }
}
