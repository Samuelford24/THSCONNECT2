package com.samuelford48gmail.thsconnect;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class admin_edit_student_classes extends AppCompatActivity {
    private Button btn1, btn2, btn3;
    private EditText edt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit_classes);
        btn1 = findViewById(R.id.admin_remove_class);
        btn2 = findViewById(R.id.admin_add_class);
        edt1 = findViewById(R.id.studentName);
        btn3 = findViewById(R.id.back);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String studentid = edt1.getText().toString().trim();
                System.out.println(studentid);
                Context context = view.getContext();
                Intent intent = new Intent(context, adminRemoveClassFromStudent.class);
                intent.putExtra("studentid", studentid);

                context.startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String studentid = edt1.getText().toString().trim();
                Context context = view.getContext();
                Intent intent = new Intent(context, adminAddClassStudent_subject.class);
                intent.putExtra("studentid", studentid);
                context.startActivity(intent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin_edit_student_classes.this, admin_activity.class);
                startActivity(intent);
            }
        });
    }

}
