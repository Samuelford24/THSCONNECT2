package com.samuelford48gmail.thsconnect;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class Homeroom extends AppCompatActivity {
    ListView rv;
    DatabaseReference myref;
    FirebaseDatabase database;
    ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeroom);
        final String hr = getIntent().getStringExtra("hr");
        rv = findViewById(R.id.lvHR);

        database = FirebaseDatabase.getInstance();
        myref = database.getReference(hr);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        rv.setAdapter(adapter);


        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                System.out.println(dataSnapshot);
                Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                System.out.println(map.keySet());
                for (String key : map.keySet()) {
                    list.add(key);
                }


                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        rv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String value = (String) rv.getItemAtPosition(i);
                System.out.println(value);
                Intent intent = new Intent(Homeroom.this, HRstudents.class);
                intent.putExtra("hrCategory", hr);
                intent.putExtra("HR", value);
                startActivity(intent);
            }
        });
    }
}


