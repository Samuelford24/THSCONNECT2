package com.samuelford48gmail.thsconnect;

import android.content.Context;
import android.content.DialogInterface;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRecyclerView extends RecyclerView.Adapter<UserRecyclerView.MyHolder> {
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;
    List<User> listdata;

    public UserRecyclerView(List<User> listdata) {
        this.listdata = listdata;
    }

    @Override
    public UserRecyclerView.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_usermodel, parent, false);

        UserRecyclerView.MyHolder myHolder = new UserRecyclerView.MyHolder(view);
        return myHolder;
    }


    public void onBindViewHolder(UserRecyclerView.MyHolder holder, final int position) {
        calendar = Calendar.getInstance();
        final User data = listdata.get(position);
        holder.name.setText(data.getName());
        holder.grade.setText(data.getGrade());
        holder.id.setText(data.getStudentID());
        //System.out.println(data.getDate_class2());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final Context context = view.getContext();
                final String ClassKey = PreferenceManager.getDefaultSharedPreferences(context).getString("classKey", "");
                androidx.appcompat.app.AlertDialog.Builder builder;
                builder = new AlertDialog.Builder(context);
                //builder.setIcon(R.drawable.open_browser);
                builder.setTitle("What would you like to do?");
                builder.setMessage("You can remove the student from your class or mark them absent.");
                builder.setNeutralButton("Mark Absent", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialogInterface, int i) {
                        String uid = data.getUid();
                        CollectionReference collectionReference = FirebaseFirestore.getInstance().collection("Users").document(uid).collection("Attendance");
                        String key = collectionReference.getId();
                        collectionReference.add("Absent from assigned class on: " + date).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(context, "Attendance updated successfully", Toast.LENGTH_LONG);
                                    dialogInterface.dismiss();
                                } else {
                                    Toast.makeText(context, "Error, please try again later", Toast.LENGTH_LONG);

                                }
                            }
                        });
                    }
                });

                builder.setPositiveButton("Remove Student", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        String uid = data.getUid();

                        FirebaseFirestore.getInstance().collection("Classes").document(ClassKey).collection("Students").document(uid).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    dialog.dismiss();
                                    Toast.makeText(context, "Student removed", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(context, "Student could not be removed, please try again later", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                        //System.out.println(myref);

                        FirebaseFirestore.getInstance().collection("Users").document(uid).collection("Classes").document(ClassKey).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                } else {
                                }
                            }
                        });
                        //   myref2.removeValue();
                        //   System.out.println(myref2);
                    }


                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                builder.setCancelable(true);
                builder.show();

                /*Intent intent = new Intent(context, Add_class_to_user.class);
                intent.putExtra("studentName", listdata.get(position).getStudentname());
                intent.putExtra("grade", listdata.get(position).getGrade());
                intent.putExtra("studentIDr", listdata.get(position).getStudnetID());
                intent.putExtra("UID", listdata.get(position).getUid());
                context.startActivity(intent);
*/

            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                final Context context = view.getContext();
                androidx.appcompat.app.AlertDialog.Builder builder2;
                dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                date = dateFormat.format(calendar.getTime());
                //  dateTimeDisplay.setText(date);
                builder2 = new AlertDialog.Builder(context);
                //builder.setIcon(R.drawable.open_browser);
                builder2.setTitle("Mark Student absent?");
                builder2.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, int id) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("attendance", "Absent from assigned class on: " + date);
                        String uid = data.getUid();
                        FirebaseFirestore.getInstance().collection("Users").document(uid).collection("Attendance").add(map).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(context, "Attendance updated successfully", Toast.LENGTH_LONG).show();
                                    dialog.dismiss();
                                } else {
                                    Toast.makeText(context, "Error, please try again later", Toast.LENGTH_LONG).show();
                                    dialog.dismiss();
                                }
                            }
                        });

                        //System.out.println(myref);

                    }


                });
                builder2.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                builder2.setCancelable(true);
                builder2.show();

                /*Intent intent = new Intent(context, Add_class_to_user.class);
                intent.putExtra("studentName", listdata.get(position).getStudentname());
                intent.putExtra("grade", listdata.get(position).getGrade());
                intent.putExtra("studentIDr", listdata.get(position).getStudnetID());
                intent.putExtra("UID", listdata.get(position).getUid());
                context.startActivity(intent);
*/

                return true;
            }

        });

    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {
        TextView name, grade, id;

        public MyHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.studentName);
            grade = itemView.findViewById(R.id.grade);
            id = itemView.findViewById(R.id.studentID);

        }
    }

}
