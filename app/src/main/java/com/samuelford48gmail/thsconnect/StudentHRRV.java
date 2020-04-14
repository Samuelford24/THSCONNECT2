package com.samuelford48gmail.thsconnect;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class StudentHRRV extends RecyclerView.Adapter<StudentHRRV.MyHolder> {
    List<ListDataUser> listdata;

    public StudentHRRV(List<ListDataUser> listdata) {
        this.listdata = listdata;
    }

    @Override
    public StudentHRRV.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_usermodel, parent, false);

        StudentHRRV.MyHolder myHolder = new StudentHRRV.MyHolder(view);
        return myHolder;
    }


    public void onBindViewHolder(StudentHRRV.MyHolder holder, final int position) {

        final ListDataUser data = listdata.get(position);
        holder.name.setText(data.getStudentname());
        holder.grade.setText(data.getGrade());
        holder.id.setText(data.getStudnetID());
        //System.out.println(data.getDate_class2());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final Context context = view.getContext();
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                android.support.v7.app.AlertDialog.Builder builder;
                builder = new AlertDialog.Builder(context);
                //builder.setIcon(R.drawable.open_browser);
                builder.setTitle("View Student's Classes or Attendance?");
                builder.setNegativeButton("Classes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String uid = data.getUid();
                        Intent intent = new Intent(context, HRStudentsClasses.class);
                        intent.putExtra("uid", uid);
                        context.startActivity(intent);

                    }


                });
                builder.setPositiveButton("Attendance", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String uid = data.getUid();
                        Intent intent = new Intent(context, HRStudentAttendance.class);
                        intent.putExtra("uid", uid);
                        context.startActivity(intent);
                    }
                });

                builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                builder.setCancelable(true);
                builder.show();

            }
        });
       /* holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                final Context context = view.getContext();
                android.support.v7.app.AlertDialog.Builder builder2;
                //  dateTimeDisplay.setText(date);
                builder2 = new AlertDialog.Builder(context);
                //builder.setIcon(R.drawable.open_browser);
                builder2.setTitle("View Student's Attendance?");
                builder2.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String uid = data.getUid();
                        Intent intent = new Intent(context, HRStudentAttendance.class);
                        intent.putExtra("uid", uid);
                        context.startActivity(intent);
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


                return true;
            }

        });

*/
    }



    @Override
    public int getItemCount() {
        return listdata.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {
        TextView name, grade, id;

        public MyHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.studentName);
            grade = (TextView) itemView.findViewById(R.id.grade);
            id = (TextView) itemView.findViewById(R.id.studentID);

        }
    }

}
