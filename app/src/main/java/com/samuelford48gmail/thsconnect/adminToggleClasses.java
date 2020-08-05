package com.samuelford48gmail.thsconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class adminToggleClasses extends AppCompatActivity {
    TextView tx;
    Button b1, b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_toggle_classes);
        tx = findViewById(R.id.openClpsed);
        b1 = findViewById(R.id.CloseClasses);
        b2 = findViewById(R.id.OpenClasses);

        final DocumentReference documentReference = FirebaseFirestore.getInstance().collection("Toggle_Classes").document("Classes");
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {


                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    System.out.println(document);

                    System.out.println(document.getId());
                    System.out.println(document.get("classes"));
                    tx.setText(document.get("classes").toString());

                    b1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            documentReference.update("classes", "Classes are closed");
                            tx.setText("Classes are closed");

                        }
                    });
                    b2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            documentReference.update("classes", "Classes are open");
                            tx.setText("Classes are open");
                        }
                    });
                }
            }
        });
    }
}


