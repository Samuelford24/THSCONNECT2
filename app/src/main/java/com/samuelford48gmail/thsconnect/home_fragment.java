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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firestore.v1.FirestoreGrpc;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class home_fragment extends Fragment /*implements View.OnClickListener */ {
    private static final String TAG = "home_fragment";
    // private Button button;
    //DatabaseReference dref;
    //ListView listview2;
    //ArrayList<String> list=new ArrayList<>();


    private TextView tx;
    ArrayList<Class_model> list;
    private RecyclerView recyclerview;
    ArrayList<String> dates;
    adapter_user_remove_class recycler;

    public home_fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_fragment, container, false);

        tx = view.findViewById(R.id.textView2);


        recyclerview = view.findViewById(R.id.rvieww);
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        dates = new ArrayList<>();
        list = new ArrayList<>();
        list.clear();
        recycler = new adapter_user_remove_class(list, dates);
        FirebaseFirestore.getInstance().collection("Users").document(uid).collection("Classes").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value.isEmpty()) {
                    list.clear();
                    recycler.notifyDataSetChanged();
                }

                if (error == null) {
                    // if (!value.isEmpty()) {
                    list.clear();
                    for (final DocumentSnapshot documentSnapshot : value) {
                        dates = (ArrayList<String>) documentSnapshot.get("dates");
                        recycler.setUserdates(dates);
                        System.out.println(dates.size());
                        Log.w(TAG, documentSnapshot.getId());

                        FirebaseFirestore.getInstance().collection("Classes").document(documentSnapshot.getId()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "Task was successful");
                                    list.add(task.getResult().toObject(Class_model.class));
                                    checkForDateChange(task.getResult().toObject(Class_model.class), documentSnapshot.getId());
                                    recycler.notifyDataSetChanged();
                                } else {
                                    UtilMethods.removeClassFromStudent(FirebaseAuth.getInstance().getCurrentUser().getUid(), documentSnapshot.getId());
                                    Log.d(TAG, "Current data: null");
                                }
                            }
                        });


                    }

                    // } else {
                    // }
                } else {
                    UtilMethods.showErrorMessage(getContext(), "Error", "Please Check your connection and try again later");
                }
            }
        });


//was originally here
        RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(getContext());
        recyclerview.setLayoutManager(layoutmanager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setAdapter(recycler);


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
//return view;
   private void checkForDateChange(Class_model class_model, String id) {
       List<String> classDates = class_model.getDates();
       int datesThatDontMatch = 0;

       for (int i = 0; i < dates.size(); i++) {
           if (classDates.contains(dates.get(i))) {

           } else {

               String date = dates.get(i);
               recycler.setUserdates(dates);
               dates.remove(i);
               Map<String, Object> map = new HashMap<>();
               map.put("dates", dates);
               FirebaseFirestore.getInstance().collection("Users").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).collection("Classes").document(id).update(map);

               AlertDialog.Builder builder;
               builder = new AlertDialog.Builder(getContext());
               //builder.setIcon(R.drawable.open_browser);
               builder.setTitle("Your class for " + date + " has been removed ");
               builder.setMessage("The date " + date + " selected for " + class_model.getClassname() + " with " + class_model.getTeacher() + " has been removed, please click on the class to edit the dates ");
               builder.setCancelable(false);
               builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       dialog.dismiss();

                   }
               });
               builder.setCancelable(true);
               builder.show();
           }

       }

   }
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
