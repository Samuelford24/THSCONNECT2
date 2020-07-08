package com.samuelford48gmail.thsconnect;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class admin_show_subjects extends AppCompatActivity implements View.OnClickListener {

    private Button btn, btn2, btn3, btn4, btn5, btn6;
    private String class_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_show_subjects);
        btn = findViewById(R.id.Science);
        btn2 = findViewById(R.id.mat);
        btn3 = findViewById(R.id.eng);
        btn4 = findViewById(R.id.ss);
        btn5 = findViewById(R.id.oth);
        btn6 = findViewById(R.id.tech);
        btn.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.Science:
                i = new Intent(this, admin_showClasses.class);
                class_name = "Science";
                i.putExtra("class_type", class_name);
                startActivity(i);break;
            case R.id.mat: i = new Intent(this, admin_showClasses.class);
                class_name = "Math";
                i.putExtra("class_type", class_name);
                startActivity(i);break;
            case R.id.ss: i = new Intent(this, admin_showClasses.class);
                class_name = "Social Studies";
                i.putExtra("class_type", class_name);
                startActivity(i);break;
            case R.id.eng: i = new Intent(this, admin_showClasses.class);
                class_name = "English";
                i.putExtra("class_type", class_name);
                startActivity(i);break;
            case R.id.tech: i = new Intent(this,admin_showClasses.class);
                class_name = "Technology";
                i.putExtra("class_type", class_name);startActivity(i);break;
            case R.id.oth: i = new Intent(this, admin_showClasses.class);
                class_name = "Other";
                i.putExtra("class_type", class_name);
                startActivity(i);break;
            default:break;
        }
    }
}

