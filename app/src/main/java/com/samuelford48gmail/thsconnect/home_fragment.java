package com.samuelford48gmail.thsconnect;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class home_fragment extends Fragment /*implements View.OnClickListener */{
   // private Button button;
    //DatabaseReference dref;
    //ListView listview2;
    //ArrayList<String> list=new ArrayList<>();
    private FirebaseDatabase database;
    private DatabaseReference myRef, myRef2;
    List<String> keyList = new ArrayList<String>();
    private TextView tx;
    List<Class_model> list;
    private RecyclerView recyclerview;
    public home_fragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_fragment, container, false);

        tx = view.findViewById(R.id.textView2);
        //FirebaseUser fbUser = FirebaseAuth.getInstance().getCurrentUser();
        //if(fbUser == null) { Intent intent = new Intent(getContext(), LoginActivity.class);
        // startActivity(intent);}

       // button = (Button) view.findViewById(R.id.button);
       // button.setOnClickListener(this);
       final List<String> keyList = new ArrayList<String>();
        recyclerview = view.findViewById(R.id.rvieww);
        database = FirebaseDatabase.getInstance();
        myRef2 = database.getReference("Users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        myRef = database.getReference("Users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Classes");
        list = new ArrayList<>();

        myRef.addChildEventListener(new ChildEventListener() {

            final adapter_user_remove_class recycler = new adapter_user_remove_class(list);
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String s) {

                //list = new ArrayList<>();
               // final adapter_user_remove_class recycler = new adapter_user_remove_class(list);
              //  final RecyclerviewAdapter2 recycler = new RecyclerviewAdapter2(list);
//String postkey2 = dataSnapshot.getKey();

                final String class_id = dataSnapshot.getValue(String.class);
                //Log.d("home", "class_idf" + class_id);
                myRef = database.getReference("Classes").child(class_id).child("class_info");
                //  Log.d("home", "myref" + myRef);

                myRef.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //keyList.add(dataSnapshot.getKey());
                        if (dataSnapshot.exists()) {
                            Class_model new_class = dataSnapshot.getValue(Class_model.class);
                            assert new_class != null;
                            String nameofclass = new_class.getDate_clasname();
                            String teacherofclass = new_class.getTeacher();
                            String roomnumberofclass = new_class.getRoom_number();
                            String class_key = new_class.getid();
                            Listdata listdata = new Listdata(nameofclass, teacherofclass, roomnumberofclass, class_key);
                            //String name = userdetails.getName();
                            //String email = userdetails.getEmail();
                            //String address = userdetails.getAddress();


                            //Commented out to see if repetitive, never tested
                            //  listdata.setDate_class(nameofclass);
                            //  listdata.setTeacher(teacherofclass);
                            // listdata.setRnumber(roomnumberofclass);
                            // recycler.notifyDataSetChanged();
                            keyList.add(new_class.getid());
                            // Log.d("home","keylist"+ keyList);
                            list.add(new_class);
                            recycler.notifyDataSetChanged();
                            //recycler.notifyDataSetChanged();// Toast.makeText(com.samuelford48gmail.thsconnect.teacher.MainActivity.this,""+name,Toast.LENGTH_LONG).show();
                        }
                        else {myRef = database.getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Classes").child(class_id);
                        myRef.removeValue();}
                    }


                    @Override
                    public void onCancelled(DatabaseError error) {
                        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                        alertDialog.setTitle("Error");
                        alertDialog.setMessage("Check your connection! If, problem persists please email svhsdev@vigoschools.org!");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                    }

                });

//was originally here
        RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(getContext());
           recyclerview.setLayoutManager(layoutmanager);
               recyclerview.setItemAnimator(new DefaultItemAnimator());
               recyclerview.setAdapter(recycler);



            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
}

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                //Log.d("home", "ff" +dataSnapshot);
                int index = keyList.indexOf(dataSnapshot.getKey());
                // Log.d("home", "indexofkL"+ index
                //);
                keyList.remove(index);
                list.remove(index);

                // recycler.notifyDataSetChanged();

                recycler.notifyDataSetChanged();
                //int index = keyList.indexOf(dataSnapshot.getKey());

               //     list.remove(index);
                 //  keyList.remove(index);

               // recycler.notifyDataSetChanged();

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(DatabaseError error) {
                AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                alertDialog.setTitle("Error");
                alertDialog.setMessage("Check your connection! If, problem persists please email svhsdev@vigoschools.org!");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }


        });

        return view;
    }
   /* @Override
    public void onClick(View view) {
        signout();



        //startActivity(new Intent(home_fragment.this, LoginActivity.class));

    }
    public void signout(){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);


    }*/
}
//});



        /*recyclerview = (RecyclerView) view.findViewById(R.id.rview);
        database = FirebaseDatabase.getInstance();
       myRef = database.getReference("Users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Classes");
      // System.out.println(myRef);
    //.child("Classes");
        //myRef = database.getReference("Users").child(uid);
        //Adding data manually creates data type of long and I am trying to return the data as a string which creates and error
        //Manual data was entered under first Uid for samuelford48@gmail.com
        //Need to make data less nested
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
                    Listdata2 listdata = new Listdata2(nameofclass, teacherofclass, roomnumberofclass, class_key);
                    //String name = userdetails.getName();
                    //String email = userdetails.getEmail();
                    //String address = userdetails.getAddress();
                    listdata.setDate_class2(nameofclass);
                    listdata.setTeacher2(teacherofclass);
                    listdata.setRnumber2(roomnumberofclass);
                    list.add(listdata);
                    // Toast.makeText(com.samuelford48gmail.thsconnect.teacher.MainActivity.this,""+name,Toast.LENGTH_LONG).show();
                }
                RecyclerviewAdapter2 recycler = new RecyclerviewAdapter2(list);
                RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(getContext());
                recyclerview.setLayoutManager(layoutmanager);
                recyclerview.setItemAnimator(new DefaultItemAnimator());
                recyclerview.setAdapter(recycler);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                alertDialog.setTitle("Error");
                alertDialog.setMessage("Check your connection! If, problem persists please try signing out and trying again. Also, email svhsdev@vigoschools.org if you are still having problems!");
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
        return view;
    }
    @Override
    public void onClick(View view) {
        signout();
        //startActivity(new Intent(home_fragment.this, LoginActivity.class));
    }
    public void signout(){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
    }
}
*/
