package com.samuelford48gmail.thsconnect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class admin_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_activity);
        ListView lv = (ListView) findViewById(R.id.listview_admin);

        List<String> arrayList = new ArrayList<String>();
        arrayList.add("Create Class");
        arrayList.add("View students in classes");
        arrayList.add("Remove/Add Classes to Users");
        arrayList.add("Delete Class");
        arrayList.add("Toggle Classes");
        arrayList.add("Change Admin Panel Password");
        arrayList.add("Homerooms A-C");
        arrayList.add("Homerooms D-G");
        arrayList.add("HomeroomsH-L");
        arrayList.add("Homerooms M-O");
        arrayList.add("Homerooms P-S");
        arrayList.add("Homerooms T-Z");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                arrayList);

        lv.setAdapter(arrayAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            int i = 0;

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                String item = ((TextView) view).getText().toString();
                String hr;
                if (item.equals("Remove/Add Classes to Users")) {

                    Intent intent = new Intent(admin_activity.this
                            , admin_edit_student_classes.class);
                    startActivity(intent);

                    // Intent intent = new Intent(admin_activity.this, admin_edit_student_classes.class
                    // );
                    //startActivity(intent);
                }
                if (item.equals("Create Class")) {
                    Intent intent = new Intent(admin_activity.this, admin_create_class.class
                    );
                    startActivity(intent);
                }
                if (item.equals("View students in classes")) {
                    Intent intent = new Intent(admin_activity.this, adminClassesWithStudents_subjects.class);
                    startActivity(intent);
                }
                if (item.equals("Delete Class")) {
                    Intent intent = new Intent(admin_activity.this, admin_subjects.class);
                    startActivity(intent);
                }
                if (item.equals("Toggle Classes")) {
                    Intent intent = new Intent(admin_activity.this, adminToggleClasses.class);
                    startActivity(intent);
                }
                if (item.equals("Change Admin Panel Password")) {

                    i++;
                    if (i >= 12) {
                        Intent intent = new Intent(admin_activity.this
                                , adminChangePassword.class);
                        startActivity(intent);
                    }

                }
                if (item.equals("Homerooms A-C")) {
                    hr = "HR1";
                    Intent intent = new Intent(admin_activity.this, Homeroom.class);
                    intent.putExtra("hr", hr);
                    startActivity(intent);
                }
                if (item.equals("Homerooms D-G")) {
                    hr = "HR2";
                    Intent intent = new Intent(admin_activity.this, Homeroom.class);
                    intent.putExtra("hr", hr);
                    startActivity(intent);
                }
                if (item.equals("Homerooms H-L")) {
                    hr = "HR3";
                    Intent intent = new Intent(admin_activity.this, Homeroom.class);
                    intent.putExtra("hr", hr);
                    startActivity(intent);
                }
                if (item.equals("Homerooms M-O")) {
                    hr = "HR4";
                    Intent intent = new Intent(admin_activity.this, Homeroom.class);
                    intent.putExtra("hr", hr);
                    startActivity(intent);
                }
                if (item.equals("Homerooms P-S")) {
                    hr = "HR5";
                    Intent intent = new Intent(admin_activity.this, Homeroom.class);
                    intent.putExtra("hr", hr);
                    startActivity(intent);

                }
                if (item.equals("Homerooms T-Z")) {
                    hr = "HR6";
                    Intent intent = new Intent(admin_activity.this, Homeroom.class);
                    intent.putExtra("hr", hr);
                    startActivity(intent);
                }
            }

        });
    }

}
