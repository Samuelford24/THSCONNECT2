package com.samuelford48gmail.thsconnect;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class show_class_fragment extends Fragment implements View.OnClickListener {
private CardView science_cv, math_cv, social_studies_cv, english_cv, tech_cv, other_cv;
    private DatabaseReference myRef ;
    //List<show_class_fragment> list;
   // private RecyclerView recycle;
   // Button view;
    public show_class_fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_show_class, container, false);

        //find cardview's by ids

        science_cv = (CardView) view.findViewById(R.id.science);
        math_cv = (CardView) view.findViewById(R.id.math);
        social_studies_cv = (CardView) view.findViewById(R.id.social_studies);
        english_cv = (CardView) view.findViewById(R.id.English);
        tech_cv = (CardView) view.findViewById(R.id.tech);
        other_cv= (CardView) view.findViewById(R.id.other);
        //set onclicklistener to cardviews
        science_cv.setOnClickListener(this);
        social_studies_cv.setOnClickListener(this);
        math_cv.setOnClickListener(this);
        english_cv.setOnClickListener(this);
        tech_cv.setOnClickListener(this);
        other_cv.setOnClickListener(this);

        return view;




    }
    public void onClick (View v){
        Intent i;
        switch (v.getId()) {
            case R.id.science:
                i = new Intent(getContext(), Science_classes.class);startActivity(i);break;
            case R.id.math: i = new Intent(getContext(), Math_classes.class);startActivity(i);break;
            case R.id.social_studies: i = new Intent(getContext(), Social_studies_classes.class);startActivity(i);break;
            case R.id.English: i = new Intent(getContext(), English_classes.class);startActivity(i);break;
            case R.id.tech: i = new Intent(getContext(), Technology_classes.class);startActivity(i);break;
            case R.id.other: i = new Intent(getContext(), Other_classes.class);startActivity(i);break;
            default:break;
        }
    }


}

