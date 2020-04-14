package com.samuelford48gmail.thsconnect;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Classes_fragment extends Fragment implements View.OnClickListener {
    private CardView science_cv, math_cv, social_studies_cv, english_cv, tech_cv, other_cv, music_cv, art_cv;
    String class_name;
    private DatabaseReference myRef;
    private FirebaseDatabase database;

    //List<Classes_fragment> list;
    // private RecyclerView recycle;
    // Button view;
    public Classes_fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_show_class, container, false);
      //  database = FirebaseDatabase.getInstance();
       // myRef = database.getReference("Toggle_Classes");
        //password = (EditText) view.findViewById(R.id.et_password);
        //submit = (Button) view.findViewById(R.id.create_class);


                /*String p = password.getText().toString();
                if (p.equals("1")) {
                    Intent intent = new Intent(getContext(), admin_activity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(view.getContext(), "Wrong Password", Toast.LENGTH_SHORT).show();
                }*/
//view.setClickable(false);

        //find cardview's by ids

        science_cv = (CardView) view.findViewById(R.id.science);
        math_cv = (CardView) view.findViewById(R.id.math);
        social_studies_cv = (CardView) view.findViewById(R.id.social_studies);
        english_cv = (CardView) view.findViewById(R.id.English);
        tech_cv = (CardView) view.findViewById(R.id.tech);
        other_cv= (CardView) view.findViewById(R.id.other);
        music_cv = view.findViewById(R.id.Music);
        art_cv = view.findViewById(R.id.art);
        //set onclicklistener to cardviews
       science_cv.setOnClickListener(this);
       math_cv.setOnClickListener(this);
       social_studies_cv.setOnClickListener(this);
       english_cv.setOnClickListener(this);
       tech_cv.setOnClickListener(this);
        other_cv.setOnClickListener(this);
        music_cv.setOnClickListener(this);
        art_cv.setOnClickListener(this);
        return view;




    }


   /* public void ClassesClosed(){
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.getValue(String.class).equals("f")) {
                    // ClassesClosed();
                    AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                    alertDialog.setTitle("Classes are Closed");
                    alertDialog.setMessage("You can not sign up for classes at this time");
                    alertDialog.setCancelable(false);
                    alertDialog.show();
                } else {


                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


*/

    public void onClick (View v){
        Intent i;
        i = new Intent(getContext(), Classes.class);
        switch (v.getId()) {
            case R.id.science:
                class_name = "Science";
               i.putExtra("class_type", class_name);
                startActivity(i);
                break;
            case R.id.math:
            class_name = "Math";
                i.putExtra("class_type", class_name);
            startActivity(i);break;
            case R.id.social_studies:
                class_name = "Social Studies";
                i.putExtra("class_type", class_name);
            startActivity(i);break;
            case R.id.English:
                class_name = "English";
                i.putExtra("class_type", class_name);
            startActivity(i);break;
            case R.id.tech:
                class_name = "Technology";
                i.putExtra("class_type", class_name);startActivity(i);break;
            case R.id.other:
                class_name = "Other";
                i.putExtra("class_type", class_name);
                startActivity(i);break;
            case R.id.Music:
                class_name = "Music";
                i.putExtra("class_type", class_name);
                startActivity(i);
                break;
            case R.id.art:
                class_name = "Art";
                i.putExtra("class_type", class_name);
                startActivity(i);
                break;

            default:break;
        }
    }


}

