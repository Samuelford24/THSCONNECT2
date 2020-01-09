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

                Intent intent = new Intent(context, HRActions.class);
                intent.putExtra("uid", data.getUid());
                context.startActivity(intent);

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
            name = (TextView) itemView.findViewById(R.id.studentName);
            grade = (TextView) itemView.findViewById(R.id.grade);
            id = (TextView) itemView.findViewById(R.id.studentID);

        }
    }

}
