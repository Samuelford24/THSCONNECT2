package com.samuelford48gmail.thsconnect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
        arrayList.add("Create Class");
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
                    Toast.makeText(admin_activity.this,"Not working yet!", Toast.LENGTH_SHORT).show();

                   // Intent intent = new Intent(admin_activity.this, admin_user_edit_classes.class
                   // );
                    //startActivity(intent);
                }
                if(item.equals("Create Class")){
                    Intent intent = new Intent(admin_activity.this, admin_create_class.class
                    );
                    startActivity(intent);
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
