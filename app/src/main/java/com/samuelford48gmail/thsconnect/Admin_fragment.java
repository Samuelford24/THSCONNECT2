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

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Admin_fragment extends Fragment {
    private EditText password;
    private Button submit;

    public Admin_fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_admin_fragment, container, false);
        final DocumentReference documentReference = FirebaseFirestore.getInstance().collection("Password").document("Password");
        password = view.findViewById(R.id.et_password);
        submit = view.findViewById(R.id.create_class);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error == null) {
                            if (value != null) {
                                if (password.getText().toString().equals(value.get("password").toString())) {
                                    Intent intent = new Intent(getContext(), admin_activity.class);
                                    startActivity(intent);
                                } else {
                                    IncorrectPassword();
                                }
                            }
                        } else {
                            UtilMethods.showErrorMessage(getContext(), "Error", "Please check your connection and try again later");
                        }
                    }
                });
            }

        });
        return view;
    }

    public void IncorrectPassword() {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(getContext());
        //builder.setIcon(R.drawable.open_browser);
        builder.setTitle("Wrong Password!");

        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        builder.setCancelable(true);
        builder.show();
    }
}


