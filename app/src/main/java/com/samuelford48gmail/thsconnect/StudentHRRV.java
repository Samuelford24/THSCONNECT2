package com.samuelford48gmail.thsconnect;

import android.content.Context;
import android.content.DialogInterface;
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
                Context context = view.getContext();
                final String ClassKey = PreferenceManager.getDefaultSharedPreferences(context).getString("classKey", "");
                android.support.v7.app.AlertDialog.Builder builder;
                builder = new AlertDialog.Builder(context);
                //builder.setIcon(R.drawable.open_browser);
                builder.setTitle("Remove Student from Class?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String uid = data.getUid();
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myref = database.getReference("Classes").child(ClassKey).child("Students").child(uid);
                        //System.out.println(myref);
                        myref.removeValue();
                        DatabaseReference myref2 = database.getReference("Users").child(uid).child("Classes").child(ClassKey);
                        myref2.removeValue();
                        System.out.println(myref2);
                    }


                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
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
