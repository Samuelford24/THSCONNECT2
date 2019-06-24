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
        arrayList.add("Users/edit classes");
        arrayList.add("Create User");
        arrayList.add("Create Class");
        arrayList.add("Remove User");
        arrayList.add("Add class to user");
        arrayList.add("View students in classes");


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                arrayList );

        lv.setAdapter(arrayAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                String item = ((TextView)view).getText().toString();
                if(item.equals("Users/edit classes")){
                    Intent intent = new Intent(admin_activity.this, MainActivity.class
                    );
                    startActivity(intent);
                }
                if(item.equals("Create User")){
                    Intent intent = new Intent(admin_activity.this, MainActivity.class
                    );
                    startActivity(intent);
                }
                if(item.equals("Create Class")){
                    Intent intent = new Intent(admin_activity.this, admin_create_class.class
                    );
                    startActivity(intent);
                }


                if(item.equals("Add class to user")){
                    Intent intent = new Intent(admin_activity.this, admin_add_class_to_user.class);
                    startActivity(intent
                    );
                }
                if(item.equals("View students in classes")){
                    Intent intent = new Intent(admin_activity.this, admin_classes_with_students.class);
                    startActivity(intent
                    );
                }
            }

        });
    }

}
