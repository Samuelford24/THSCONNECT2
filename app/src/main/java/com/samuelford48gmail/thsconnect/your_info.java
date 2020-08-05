package com.samuelford48gmail.thsconnect;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class your_info extends AppCompatActivity {
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_info);

        System.out.println(FirebaseAuth.getInstance().getUid());
        FirebaseFirestore.getInstance().collection("Users").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    user = task.getResult().toObject(User.class);

                    TextView user_name = findViewById(R.id.name);
                    user_name.setText("Name: " + user.getName());
                    TextView user_grade = findViewById(R.id.grade);
                    user_grade.setText("Grade: " + user.grade);
                    TextView user_email = findViewById(R.id.email);
                    user_email.setText("Email: " + user.getEmail());
                    TextView user_id = findViewById(R.id.studentID);
                    user_id.setText("ID: " + user.getStudentID());
                } else {
                    System.out.println("Error");
                }
            }
        });


    }

}
