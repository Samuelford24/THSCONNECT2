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


import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class Homeroom extends AppCompatActivity {
    ListView rv;

    ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeroom);
        final String hr = getIntent().getStringExtra("hr");
        rv = findViewById(R.id.lvHR);


        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        rv.setAdapter(adapter);
        FirebaseFirestore.getInstance().collection(hr).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error == null) {
                    for (DocumentSnapshot documentSnapshot : value) {
                        list.add(documentSnapshot.getId());
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    UtilMethods.showErrorMessage(getApplicationContext(), "Error", "Please check your connection and try again later!");
                }
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


