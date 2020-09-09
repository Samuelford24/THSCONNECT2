package com.samuelford48gmail.thsconnect;

import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class adapter_user_remove_class extends RecyclerView.Adapter<adapter_user_remove_class.MyHolder> {

    ArrayList<Class_model> listdata;
    ArrayList<String> userdates;

    public adapter_user_remove_class(ArrayList<Class_model> listdata, ArrayList<String> userdates) {
        this.listdata = listdata;
        this.userdates = userdates;
    }

    public adapter_user_remove_class(ArrayList<Class_model> listdata) {
        this.listdata = listdata;
    }

    public void setUserdates(ArrayList<String> userdates) {
        this.userdates = userdates;
    }

    @Override
    public adapter_user_remove_class.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_class_model, parent, false);

        adapter_user_remove_class.MyHolder myHolder = new adapter_user_remove_class.MyHolder(view);
        return myHolder;
    }


    public void onBindViewHolder(adapter_user_remove_class.MyHolder holder, final int position) {

        final Class_model data = listdata.get(position);
        System.out.println(data.getid());
        System.out.println(data.getTeacher());
        System.out.println(data.getSubject());
        System.out.println(data.getRoom_number());
        holder.className.setText(data.getClassname());
        holder.teacher.setText(data.getTeacher());
        holder.rnumber.setText(data.getRoom_number());
        if (userdates.size() > 0) {
            holder.dates.setText(UtilMethods.convertToString(userdates));
        } else {
            holder.dates.setText("Error");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                //   Log.i("OnBindViewHolder",listdata.get(position).getid());
                Context context = view.getContext();

                Intent intent = new Intent(context, user_remove_class.class);
                intent.putExtra("class", data);
                intent.putExtra("usersDates", userdates);
                //  intent.putExtra("date_class", listdata.get(position).getClassname());
                // intent.putExtra("teacher", listdata.get(position).getTeacher());
                //intent.putExtra("room_number", listdata.get(position).getRoom_number());
                //intent.putExtra("post_key", listdata.get(position).getid());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {

        if (listdata == null) {
            return 0;
        }

        return listdata.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {
        TextView className, teacher, rnumber, dates;

        public MyHolder(View itemView) {
            super(itemView);
            className = itemView.findViewById(R.id.date_class_name);
            teacher = itemView.findViewById(R.id.teacher);
            rnumber = itemView.findViewById(R.id.room_number);
            dates = itemView.findViewById(R.id.dates);

        }
    }

}



