package com.samuelford48gmail.thsconnect.teacher;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.samuelford48gmail.thsconnect.Class_model;
import com.samuelford48gmail.thsconnect.R;
import com.samuelford48gmail.thsconnect.UtilMethods;
import com.samuelford48gmail.thsconnect.admin_get_user_info;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class edit_class_page extends AppCompatActivity {
    Context context;
    Class_model class_model;
    ListView lv;
    Button updateDates;
    ArrayList<String> userdates;
    private Button remove_class;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_class_page);
        class_model = getIntent().getParcelableExtra("class");
        //  final String date_class2 = getIntent().getStringExtra("date_class");
        //  final String teacher = getIntent().getStringExtra("teacher");
        // final String room_number = getIntent().getStringExtra("room_number");
        // final String post_key = getIntent().getStringExtra("post_key");
        userdates = getIntent().getExtras().getStringArrayList("usersDates");

        lv = findViewById(R.id.dates_lv);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, class_model.getDates());

        lv.setAdapter(adapter);
        //  checkDates();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
             //   System.out.println("Listview item clicked");
                Intent intent = new Intent(adapterView.getContext(), admin_get_user_info.class);
                intent.putExtra("class", class_model);

                intent.putExtra("dateClickedOn",lv.getItemAtPosition(i).toString());
                //inefficient because key is stored in class_model;however, previous code structure requires it and I don't have time to change it
                intent.putExtra("post_key", class_model.getid());
                startActivity(intent);
            }
        });
        updateDates = findViewById(R.id.update_dates);
        TextView display_class_name = findViewById(R.id.date_tv);
        display_class_name.setText(class_model.getClassname());
        TextView display_teacher = findViewById(R.id.teacher_tv);
        display_teacher.setText(class_model.getTeacher());
        TextView display_room_number = findViewById(R.id.rn_tv);
        editText = findViewById(R.id.date_et);
        editText.setText(class_model.getDateString());
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
                            builder = new AlertDialog.Builder(edit_class_page.this);
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
                        builder = new AlertDialog.Builder(edit_class_page.this);
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
           //     String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
               // FirebaseFirestore.getInstance().collection("Classes").document(class_model.getid()).collection("Students").document(uid).delete();
removeClassFromStudents(class_model.getid());


            }
        });
        updateDates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ArrayList<String> dates = new ArrayList<>();

                if (checkDatesandConvertToArray(editText.getText().toString())!=null) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("dateString",editText.getText().toString());
                    map.put("dates", new ArrayList<>(Arrays.asList(checkDatesandConvertToArray(editText.getText().toString()))));

                    FirebaseFirestore.getInstance().collection("Classes").document(class_model.getid()).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            AlertDialog.Builder builder;
                            builder = new AlertDialog.Builder(edit_class_page.this);
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
                  /*  AlertDialog.Builder builder;
                    builder = new AlertDialog.Builder(edit_class_page.this);
                    //builder.setIcon(R.drawable.open_browser);
                    builder.setTitle("You must enter one date");
                    builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();

                        }
                    });
                    builder.setCancelable(true);
                    builder.show();
                }*/
                }

            }

        });
    }

    private String[] checkDatesandConvertToArray(String date) {
        String[] dates = date.split("/");
        for (String s : dates) {
            if (UtilMethods.isDateValid(s) && s.length()<=5) {

            } else {
                AlertDialog.Builder builder;
                builder = new AlertDialog.Builder(edit_class_page.this);
                //builder.setIcon(R.drawable.open_browser);
                builder.setTitle("Date " + s + " is invalid");
                builder.setMessage("Please fix the date and try again. All dates must be separated by a /. An example of a correct entry is 08-09/09-10 ");
                builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                builder.setCancelable(true);
                builder.show();
                return null;
            }
        }
        return dates;
    }
    private void  removeClassFromStudents(final String classId){
        FirebaseFirestore.getInstance().collection("Classes").document(classId).collection("Students").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                for (DocumentSnapshot documentSnapshot:value){
                    if (!value.isEmpty()){
                        FirebaseFirestore.getInstance().collection("Users").document(documentSnapshot.getId()).collection("Classes").document(classId).delete();

                    }
                }
            }
        });
        removeClassFromTeacher(classId);
        deleteClass(classId);
    }

    private void deleteClass(String classId) {
        FirebaseFirestore.getInstance().collection("Classes").document(classId).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                AlertDialog.Builder builder;
                builder = new AlertDialog.Builder(edit_class_page.this);
                //builder.setIcon(R.drawable.open_browser);
                builder.setTitle("Class has successfully been removed");
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

    }

    public void removeClassFromTeacher(String classId) {
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
     //   final String post_key = getIntent().getStringExtra("post_key");
        FirebaseFirestore.getInstance().collection("Users").document(uid).collection("Classes").document(classId).delete();

            }





    private void checkDates() {
        //  int size = class_model.getDates().size();

        for (int i = 0; i < userdates.size(); i++) {

            lv.setItemChecked(class_model.getDates().indexOf(userdates.get(i)), true);
        }
    }
}
