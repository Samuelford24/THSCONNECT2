package com.samuelford48gmail.thsconnect;

import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerviewAdapter2 extends RecyclerView.Adapter<RecyclerviewAdapter2.MyHolder> {

    public void setListdata(ArrayList<Class_model> listdata) {
        this.listdata = listdata;
    }

    ArrayList<Class_model> listdata;

    public RecyclerviewAdapter2(ArrayList<Class_model> listdata) {
        this.listdata = listdata;
    }

    @Override
    public RecyclerviewAdapter2.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_class_model, parent, false);

        RecyclerviewAdapter2.MyHolder myHolder = new RecyclerviewAdapter2.MyHolder(view);
        return myHolder;
    }


    public void onBindViewHolder(RecyclerviewAdapter2.MyHolder holder, final int position) {
        final Class_model data = listdata.get(position);
        holder.className.setText(data.getClassname());
        holder.teacher.setText(data.getTeacher());
        holder.roomNumber.setText(data.getRoom_number());
        holder.dates.setText(UtilMethods.convertToString(data.getDates()));
        //System.out.println(data.getDate_class2());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                Context context = view.getContext();
                Intent intent = new Intent(context, Add_class_to_user.class);
                intent.putExtra("class", data);

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
//        System.out.println(listdata.size());
        if (listdata != null) {
            return listdata.size();
        } else {
            return 0;
        }

    }


    class MyHolder extends RecyclerView.ViewHolder {
        TextView className, teacher, roomNumber, dates;

        public MyHolder(View itemView) {
            super(itemView);
            className = itemView.findViewById(R.id.date_class_name);
            teacher = itemView.findViewById(R.id.teacher);
            roomNumber = itemView.findViewById(R.id.room_number);
            dates = itemView.findViewById(R.id.dates);

        }
    }

}


