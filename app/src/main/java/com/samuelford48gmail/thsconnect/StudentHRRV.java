package com.samuelford48gmail.thsconnect;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class StudentHRRV extends RecyclerView.Adapter<StudentHRRV.MyHolder> {
    ArrayList<User> users;

    ArrayList<String> classNames = new ArrayList<>();
    ArrayList<String> absentList = new ArrayList<>();

    public StudentHRRV(ArrayList<User> users, ArrayList<String> classNames, ArrayList<String> absentList) {
        this.users = users;
        this.classNames = classNames;
        this.absentList = absentList;
    }

    public void setAbsentList(ArrayList<String> absentList) {
        this.absentList = absentList;
    }


    public StudentHRRV(ArrayList<User> users) {
        this.users = users;
    }

    public void setClassNames(ArrayList<String> classNames) {
        this.classNames = classNames;
    }

    @Override
    public StudentHRRV.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_usermodel, parent, false);

        StudentHRRV.MyHolder myHolder = new StudentHRRV.MyHolder(view);
        return myHolder;
    }

    public void onBindViewHolder(StudentHRRV.MyHolder holder, final int position) {

        final User data = users.get(position);
        holder.name.setText(data.getName());
        holder.grade.setText(data.getGrade());
        holder.id.setText(data.getStudentID());
        if (absentList.get(position) != null) {
            holder.attendance.setText(absentList.get(position));

        } else {
            holder.attendance.setText("");

        }
        if (classNames.get(position) != null) {
            holder.classname.setText(classNames.get(position));

        } else {
            holder.classname.setText("No class");

        }
        //System.out.println(data.getDate_class2());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final Context context = view.getContext();
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                androidx.appcompat.app.AlertDialog.Builder builder;
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
        System.out.println(users.size());
        if (users == null) {
            return 0;
        } else {
            return users.size();
        }
    }


    class MyHolder extends RecyclerView.ViewHolder {
        TextView name, grade, id, attendance, classname;

        public MyHolder(View itemView) {
            super(itemView);
            attendance = itemView.findViewById(R.id.attendance);
            classname = itemView.findViewById(R.id.className);
            name = itemView.findViewById(R.id.studentName);
            grade = itemView.findViewById(R.id.grade);
            id = itemView.findViewById(R.id.studentID);

        }
    }

}
