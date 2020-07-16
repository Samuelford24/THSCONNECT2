package com.samuelford48gmail.thsconnect;

import android.content.DialogInterface;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Admin_fragment extends Fragment {
    private EditText password;
    private Button submit;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
public Admin_fragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_admin_fragment, container, false);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Password");
        password = view.findViewById(R.id.et_password);
        submit = view.findViewById(R.id.create_class);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String p = password.getText().toString();
                        System.out.println(p);
                        String password_firebase = dataSnapshot.getValue(String.class);
                        System.out.println(dataSnapshot);
                        System.out.println(password_firebase);
                        if(p.equals(password_firebase)) {
                            Intent intent = new Intent(getContext(), admin_activity.class);
                            startActivity(intent);

                        }
                        else{
                            IncorrectPassword();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }


                });
                /*String p = password.getText().toString();
                if (p.equals("1")) {
                    Intent intent = new Intent(getContext(), admin_activity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(view.getContext(), "Wrong Password", Toast.LENGTH_SHORT).show();
                }*/
            }
        });

        /*
        ListView lv = (ListView) view.findViewById(R.id.listview_admin);

        List<String> arrayList = new ArrayList<String>();
        arrayList.add("Users/edit classes");
        arrayList.add("Create User");
        arrayList.add("Create Class");
        arrayList.add("Remove User");


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_list_item_1,
                arrayList );

        lv.setAdapter(arrayAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                String item = ((TextView)view).getText().toString();
                if(item.equals("Users/edit classes")){
                    Intent intent = new Intent(getContext(), com.samuelford48gmail.thsconnect.teacher.MainActivity.class
                    );
                    startActivity(intent);
                }
                if(item.equals("Create User")){
                    Intent intent = new Intent(getContext(), com.samuelford48gmail.thsconnect.teacher.MainActivity.class
                    );
                    startActivity(intent);
                }
                if(item.equals("Create Class")){
                    Intent intent = new Intent(getContext(), com.samuelford48gmail.thsconnect.teacher.MainActivity.class
                    );
                    startActivity(intent);
                }

                if(item.equals("Remove User")){
                    Intent intent = new Intent(getContext(), com.samuelford48gmail.thsconnect.teacher.MainActivity.class
                    );
                    startActivity(intent);
                }
            }

        });

*/
        return view;
}
public void IncorrectPassword(){
    AlertDialog.Builder builder;
    builder = new AlertDialog.Builder(getContext());
    //builder.setIcon(R.drawable.open_browser);
    builder.setTitle("Wrong Password!");

    builder.setNegativeButton("Ok",new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int id) {
            dialog.dismiss();
        }
    });
    builder.setCancelable(true);
    builder.show();
}
}


