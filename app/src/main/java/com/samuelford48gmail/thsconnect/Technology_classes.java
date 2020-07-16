package com.samuelford48gmail.thsconnect;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
/*
public class Technology_classes extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private List<Listdata> list;
    private RecyclerView recyclerview;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technology_classes);
        recyclerview = (RecyclerView) findViewById(R.id.rvieww);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Technology");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                // StringBuffer stringbuffer = new StringBuffer();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Class_model new_class = dataSnapshot1.getValue(Class_model.class);
                    String nameofclass = new_class.getDate_clasname();
                    String teacherofclass = new_class.getTeacher();
                    String roomnumberofclass = new_class.getRoom_number();
                    String class_key = new_class.getUid();
                    Listdata listdata = new Listdata(nameofclass, teacherofclass, roomnumberofclass, class_key);
                    //String name = userdetails.getName();
                    //String email = userdetails.getEmail();
                    //String address = userdetails.getAddress();
                    listdata.setDate_class(nameofclass);
                    listdata.setTeacher(teacherofclass);
                    listdata.setRnumber(roomnumberofclass);
                    list.add(listdata);
                    // Toast.makeText(com.samuelford48gmail.thsconnect.teacher.MainActivity.this,""+name,Toast.LENGTH_LONG).show();

                }

                RecyclerviewAdapter2 recycler = new RecyclerviewAdapter2(list);
                RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(Technology_classes.this);
                recyclerview.setLayoutManager(layoutmanager);
                recyclerview.setItemAnimator(new DefaultItemAnimator());
                recyclerview.setAdapter(recycler);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                AlertDialog alertDialog = new AlertDialog.Builder(Technology_classes.this).create();
                alertDialog.setTitle("Error");
                alertDialog.setMessage("Check your connection! If, problem persists please email svhsdev@vigoschools.org!");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
                // Failed to read value
                //  Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }
    //});


}*/